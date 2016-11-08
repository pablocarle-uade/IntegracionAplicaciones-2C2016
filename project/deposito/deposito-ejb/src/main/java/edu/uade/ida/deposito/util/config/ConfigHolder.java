package edu.uade.ida.deposito.util.config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import com.google.gson.Gson;

@Singleton
public class ConfigHolder {
	
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

}
