package edu.uade.ida.deposito.test.integration;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import edu.uade.ida.deposito.data.ArticuloRepository;
import edu.uade.ida.deposito.data.SolicitudArticuloRepository;
import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloRequest;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.CaracteristicaArticulo;
import edu.uade.ida.deposito.model.HasDTO;
import edu.uade.ida.deposito.model.SolicitudArticulo;
import edu.uade.ida.deposito.model.TipoDeArticulo;
import edu.uade.ida.deposito.service.SolicitudArticulosMDB;
import edu.uade.ida.deposito.util.ConfigHolder;
import edu.uade.ida.deposito.util.Resources;

@RunWith(Arquillian.class)
public class SolicitudArticuloTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "test2.war")
				.addClasses(SolicitudArticulo.class, SolicitudArticuloRequest.class, SolicitudArticuloDTO.class, SolicitudArticulosMDB.class, Resources.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
		archive.addClass(ArticuloRepository.class);
		archive.addClass(SolicitudArticuloRepository.class);
		archive.addClass(ArticuloDTO.class);
		archive.addClasses(CaracteristicaArticulo.class, TipoDeArticulo.class, Articulo.class);
		archive.addClass(HasDTO.class);
		archive.addClass(ConfigHolder.class);
		archive.addClass(Gson.class);
		archive.addClass(JsonSyntaxException.class);
		return archive
				;
	}
	
	@Inject
	Logger log;
	
	@Inject
	EntityManager em;
	
	@Resource(mappedName = "/queue/ColaSolicitudesArticulos")
	private Queue csa;

	@Resource(mappedName = "/ConnectionFactory")
	private ConnectionFactory factory;
	
	@Test
	public void testSolicitudStockArticuloNoExiste() throws Exception {
		//Prueba de la conexion, ya que no esperamos resultado alguno de interface JMS
		String messageBody = "{'idArticulo': 0, 'cantidad': 5}";
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		MessageProducer producer = session.createProducer(csa);
		TextMessage message = session.createTextMessage();
		message.setText(messageBody);
		producer.send(message, new CompletionListener() {
			
			@Override
			public void onException(Message message, Exception exception) {
				exception.printStackTrace();
				fail(exception.getMessage());
			}
			
			@Override
			public void onCompletion(Message message) {
				SolicitudArticulo sa = em.find(SolicitudArticulo.class, 0);
				assertNull(sa);
			}
		});
		connection.close();
	}
	
	@Test
	public void testSolicitudStockArticuloExiste() throws Exception {
		//Primero creo un articulo y lo guardo en bd
		crearArticuloTest();
		String messageBody = "{'idArticulo': 1, 'cantidad': 5}";
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		MessageProducer producer = session.createProducer(csa);
		TextMessage message = session.createTextMessage();
		message.setText(messageBody);
		producer.send(message, new CompletionListener() {
			
			@Override
			public void onException(Message message, Exception exception) {
				exception.printStackTrace();
				fail(exception.getMessage());
			}
			
			@Override
			public void onCompletion(Message message) {
				SolicitudArticulo sa = em.find(SolicitudArticulo.class, 1);
				assertNotNull(sa);
			}
		});
		connection.close();
	}
	
	private void crearArticuloTest() {
		Articulo a = new Articulo();
		a.setId(1L);
		a.setCodigo("123");
		a.setDescripcion("Articulo de prueba");
		a.setMarca("Marca");
		a.setOrigen("origen");
		a.setPrecio(new BigDecimal(50.0));
		a.setUrlImagen("unaimagen.com");
		em.persist(a);
	}
}
