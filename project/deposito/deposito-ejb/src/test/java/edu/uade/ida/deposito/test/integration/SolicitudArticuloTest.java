package edu.uade.ida.deposito.test.integration;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueRequestor;
import javax.jms.QueueSession;
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

import edu.uade.ida.deposito.data.ArticuloRepository;
import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloRequest;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.CaracteristicaArticulo;
import edu.uade.ida.deposito.model.HasDTO;
import edu.uade.ida.deposito.model.SolicitudArticulo;
import edu.uade.ida.deposito.model.TipoDeArticulo;
import edu.uade.ida.deposito.service.SolicitudArticulosMDB;
import edu.uade.ida.deposito.util.Resources;

@RunWith(Arquillian.class)
public class SolicitudArticuloTest {
	
	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(SolicitudArticulo.class, SolicitudArticuloRequest.class, SolicitudArticuloDTO.class, SolicitudArticulosMDB.class, Resources.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
		archive.addClass(ArticuloRepository.class);
		archive.addClass(ArticuloDTO.class);
		archive.addClasses(CaracteristicaArticulo.class, TipoDeArticulo.class, Articulo.class);
		archive.addClass(HasDTO.class);
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
		String messageBody = "{'idArticulo': 0, 'cantidad': 5}";
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueRequestor requestor = new QueueRequestor((QueueSession) session, csa);
		connection.start();
		Message request = session.createTextMessage(messageBody);
		Message response = requestor.request(request);
		assertEquals("Should have responded with same message but got " + ((TextMessage) response).getText(), messageBody, ((TextMessage) response).getText());
	}
	
	@Test
	public void testSolicitudStockArticuloExiste() throws Exception {
		//Primero creo un articulo y lo guardo en bd
		crearArticuloTest();
		String messageBody = "{'idArticulo': 1, 'cantidad': 5}";
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueRequestor requestor = new QueueRequestor((QueueSession) session, csa);
		connection.start();
		Message request = session.createTextMessage(messageBody);
		Message response = requestor.request(request);
		
	}
	
	@Test
	public void testSolicitudStockCantidadCero() {
		//El articulo existe
		crearArticuloTest();
	}
	
	@Test
	public void testSolicitudStockCantidadNegativa() {
		crearArticuloTest();
		//El articulo existe
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
