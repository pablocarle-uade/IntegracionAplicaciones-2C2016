package edu.uade.ida.deposito.util.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.inject.Inject;

import com.google.gson.Gson;

@Singleton
public class ConfigHolder {
		
	@Inject
	private Logger log;

	private static Gson gson = new Gson();
	
	private static String idDeposito = "G01";
	private static DespachosConfig despachosConfig;
	private static PortalesConfig portalesConfig;
	private static LogisticaMonitoreoConfig logisticaConfig;

	static {
		InputStream isDespachos = ConfigHolder.class.getClassLoader().getResourceAsStream("despacho.json");
		InputStream isPortales = ConfigHolder.class.getClassLoader().getResourceAsStream("portales.json");
		InputStream isLogistica = ConfigHolder.class.getClassLoader().getResourceAsStream("logistica.json");
		
		despachosConfig = gson.fromJson(new InputStreamReader(isDespachos), DespachosConfig.class);
		portalesConfig = gson.fromJson(new InputStreamReader(isPortales), PortalesConfig.class);
		logisticaConfig = gson.fromJson(new InputStreamReader(isLogistica), LogisticaMonitoreoConfig.class);
	}
	
	public ConfigHolder() {
		super();
	}
	
	public List<JmsEndpointConfig> getAsyncServers(ConfigModulo modulo) {
		List<JmsEndpointConfig> retList = new ArrayList<>();
		switch (modulo) {
		case LOGISTICA:
			retList.addAll(logisticaConfig.getAsyncServers());
			break;
		case DESPACHO:
			retList.addAll(despachosConfig.getAsyncServers());
			break;
		case PORTAL:
			retList.addAll(portalesConfig.getAsyncServers());
			break;
		default:
			throw new RuntimeException("no se reconoce " + modulo);
		
		}
		return retList;
	}
	
	/**
	 * Devuelve el primer endpoint REST que encuentre para el modulo
	 * 
	 * @param modulo El modulo
	 * @return REST Endpoint o cadena vacia
	 */
	public String getRESTEndpointURL(ConfigModulo modulo) {
		//Devuelvo el primero que encuentro
		switch (modulo) {
		case DESPACHO:
			return despachosConfig.getRESTEndpointURL();
		case LOGISTICA:
			return logisticaConfig.getRESTEndpointURL();
		case PORTAL:
			return portalesConfig.getRESTEndpointURL();
		default:
			throw new RuntimeException("No se reconoce " + modulo);
		}
	}

	public LogisticaMonitoreoConfig getLogisticaMonitoreoConfig() {
		return logisticaConfig;
	}

	public void reloadConfig() {
		InputStream isDespachos = ConfigHolder.class.getClassLoader().getResourceAsStream("despacho.json");
		InputStream isPortales = ConfigHolder.class.getClassLoader().getResourceAsStream("portales.json");
		InputStream isLogistica = ConfigHolder.class.getClassLoader().getResourceAsStream("logistica.json");
		
		despachosConfig = gson.fromJson(new InputStreamReader(isDespachos), DespachosConfig.class);
		portalesConfig = gson.fromJson(new InputStreamReader(isPortales), PortalesConfig.class);
		logisticaConfig = gson.fromJson(new InputStreamReader(isLogistica), LogisticaMonitoreoConfig.class);
	}

	/**
	 * Devuelve el rest endpoint (http://host:port/resource) del modulo
	 * y equipo solicitado
	 * 
	 * @param modulo El modulo
	 * @param id El id del equipo
	 * @return rest endpoint o cadena vacia
	 */
	public String getRESTEndpointURL(ConfigModulo modulo, String id) {
		switch (modulo) {
		case DESPACHO:
			return despachosConfig.getRESTEndpointURL(id);
		case LOGISTICA:
			return logisticaConfig.getRESTEndpointURL(id);
		case PORTAL:
			return portalesConfig.getRESTEndpointURL(id);
		default:
			throw new RuntimeException("No se reconoce modulo " + modulo);
		}
	}

	public String getIdDeposito() {
		return idDeposito;
	}
	
	public void reloadDespachosConfig(String config) {
		log.info("Starting reloadDespachosConfig() for new config");
		despachosConfig = gson.fromJson(config, DespachosConfig.class);
		log.info("Finished reloadDespachosConfig() for new config");
	}
	
	public void reloadPortalesConfig(String config) {
		log.info("Starting reloadPortalesConfig() for new config");
		portalesConfig = gson.fromJson(config, PortalesConfig.class);
		log.info("Finished reloadPortalesConfig() for new config");
	}
	
	public void reloadLogisticaMonitoreoConfig(String config) {
		log.info("Starting reloadLogisticaConfig() for new config");
		logisticaConfig = gson.fromJson(config, LogisticaMonitoreoConfig.class);
		log.info("Finished reloadLogisticaConfig() for new config");
	}

}
