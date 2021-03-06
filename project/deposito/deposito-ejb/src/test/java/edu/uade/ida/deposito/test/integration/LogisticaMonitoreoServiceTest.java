package edu.uade.ida.deposito.test.integration;

import static org.junit.Assert.fail;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

@RunWith(Arquillian.class)
public class LogisticaMonitoreoServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "test_log_mon.war").addAsWebInfResource(EmptyAsset.INSTANCE,
				"beans.xml")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml")
				.addPackages(true, "edu.uade.ida.deposito")
				.addPackages(true, "com.google.gson")
				.addPackages(true, "org.mockito")
				.addAsResource("despacho.json")
				.addAsResource("logistica.json")
				.addAsResource("portales.json");
		return archive;
	}
	
	private boolean mocksInitialized = false;

	@Before
	public void setUp() throws Exception {
		if (!mocksInitialized) {
			MockitoAnnotations.initMocks(this);
			this.mocksInitialized = true;
		}
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
}
