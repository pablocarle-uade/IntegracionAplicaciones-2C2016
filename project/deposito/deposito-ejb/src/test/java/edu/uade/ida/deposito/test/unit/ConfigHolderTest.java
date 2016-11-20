package edu.uade.ida.deposito.test.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.uade.ida.deposito.util.config.ConfigHolder;
import edu.uade.ida.deposito.util.config.ConfigModulo;
import edu.uade.ida.deposito.util.config.JmsEndpointConfig;
import edu.uade.ida.deposito.util.config.LogisticaMonitoreoConfig;

public class ConfigHolderTest {

	private ConfigHolder config;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		config = new ConfigHolder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAsyncServers() {
		List<JmsEndpointConfig> configsDespacho = config.getAsyncServers(ConfigModulo.DESPACHO);
		assertTrue(configsDespacho != null && !configsDespacho.isEmpty());
		List<JmsEndpointConfig> configsPortales = config.getAsyncServers(ConfigModulo.PORTAL);
		assertTrue(configsPortales != null && !configsPortales.isEmpty());
		List<JmsEndpointConfig> configsLogistica = config.getAsyncServers(ConfigModulo.LOGISTICA);
		assertTrue(configsLogistica != null && !configsLogistica.isEmpty());
	}

	@Test
	public void testGetRESTEndpointURLConfigModulo() {
		String restLogistica = config.getRESTEndpointURL(ConfigModulo.LOGISTICA);
		assertTrue(restLogistica != null && restLogistica.length() > 0);
		String restDespacho = config.getRESTEndpointURL(ConfigModulo.DESPACHO);
		assertTrue(restDespacho != null && restDespacho.length() > 0);
		String restPortal = config.getRESTEndpointURL(ConfigModulo.PORTAL);
		assertTrue(restPortal == null || restPortal.length() == 0);
	}
	
	@Test
	public void testGetAsyncServers2() {
		List<JmsEndpointConfig> configs = config.getAsyncServers(ConfigModulo.DESPACHO);
		assertTrue(configs != null && !configs.isEmpty());
	}

	@Test
	public void testGetLogisticaMonitoreoConfig() {
		LogisticaMonitoreoConfig conf = config.getLogisticaMonitoreoConfig();
		assertNotNull(conf);
	}
	
	@Test
	public void testReadJmsSettings() {
		List<JmsEndpointConfig> c = config.getAsyncServers(ConfigModulo.PORTAL);
		assertTrue(c != null && !c.isEmpty());
		JmsEndpointConfig jec = c.get(0);
		assertTrue(jec.getJmsQueue() != null && jec.getJmsQueue().length() > 0);
		assertTrue(jec.getJmsTopic() == null || jec.getJmsTopic().length() == 0);
		assertTrue(jec.getPassword() != null && jec.getPassword().length() > 0);
		assertTrue(jec.getUser() != null && jec.getUser().length() > 0);
		assertTrue(jec.getProviderUrl() != null && jec.getProviderUrl().length() > 0);
	}
	
	@Test
	public void testGetRestEndpoint() {
		String[] moduleIds = new String[]{"G05", "G08"};
		String rest1 = config.getRESTEndpointURL(ConfigModulo.DESPACHO, moduleIds[0]);
		assertTrue(rest1 != null && rest1.length() > 0);
		String rest2 = config.getRESTEndpointURL(ConfigModulo.DESPACHO, moduleIds[1]);
		assertTrue(rest2 != null && rest2.length() > 0);
	}
}
