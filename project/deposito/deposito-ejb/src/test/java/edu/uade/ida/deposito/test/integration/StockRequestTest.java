package edu.uade.ida.deposito.test.integration;

import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.inject.Inject;

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
public class StockRequestTest {
	
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
	SolicitudArticulosMDB mdb;
	
	@Test
	public void testSolicitudStockArticuloNoExiste() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSolicitudStockArticuloExiste() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSolicitudStockCantidadCero() {
		
	}
	
	@Test
	public void testSolicitudStockCantidadNegativa() {
		
	}
}
