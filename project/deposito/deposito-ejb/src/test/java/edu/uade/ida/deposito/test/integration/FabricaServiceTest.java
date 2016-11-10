package edu.uade.ida.deposito.test.integration;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.model.TipoDeArticulo;
import edu.uade.ida.deposito.service.ArticulosServiceLocal;
import edu.uade.ida.deposito.service.integration.FabricaServiceLocal;

@RunWith(Arquillian.class)
public class FabricaServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
		archive.addPackage("com.google.gson");
		archive.addPackages(true, "edu.uade.ida.deposito");
		return archive
				;
	}
	
	private static ArticuloDTO[] articulosDefault = new ArticuloDTO[]{
			new ArticuloDTO(-1, "1351953", "Aire Acondicionado Split WBC 12B-13B 2645 F/C", "4 modos de operación diferentes: Frío, deshumidificación, ventilación y calor", "Whirlpool", new BigDecimal("4633"), "url", "Argentina", TipoDeArticulo.Electro.toString(), 20, null),
			new ArticuloDTO(-1, "3122", "Musculosa Pale", "Musculosa Basement con Breteles", "Basement", new BigDecimal("79"), "url", "Brasil", TipoDeArticulo.Moda.toString(), 30, null),
			new ArticuloDTO(-1, "1857363", "Mesa para TV 21\" wengue", "Mesa para TV", "Mica", new BigDecimal("409"), "url", "Argentina", TipoDeArticulo.Mueble.toString(), 40, null),
			new ArticuloDTO(-1, "1858018", "Coche Paraguas Gris", "Coche Paraguas", "Love", new BigDecimal("529"), "url", "China", TipoDeArticulo.Niños.toString(), 50, null)
	};
	
	@Inject
	private UserTransaction tr;
	
	@Inject
	private FabricaServiceLocal fsl;
	
	@Inject
	private ArticulosServiceLocal asl;
	
	@Inject
	private EntityManager em;
	
	@Before
	public void setUp() throws Exception { }

	@Test
	public void testCrearSolicitudCompra() {
		ArticuloDTO articulo = crearArticuloTest();
		assertNotNull(articulo);
		try {
			tr.begin();
			em.joinTransaction();
			SolicitudCompraDTO dto = fsl.crearSolicitudCompra(articulo, 5);
			tr.commit();
			assertNotNull(dto);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	private ArticuloDTO crearArticuloTest() {
		try {
			tr.begin();
			em.joinTransaction();
			asl.crearArticulo(articulosDefault[0]);
			tr.commit();
			return articulosDefault[0];
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
			return null;
		}
	}

}
