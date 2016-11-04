package edu.uade.ida.deposito.util.config;

import java.io.InputStream;
import java.util.Properties;

import com.google.gson.Gson;

public class ConfigHolder {
	
	public enum ConfigType {
		DESPACHO, PORTAL, LOGISTICA;
	}

	private static Gson gson = new Gson();
	
	private static Properties despachosConfig;
	private static Properties portalesConfig;
	private static Properties logisticaConfig;

	static {
		InputStream isDespachos = ConfigHolder.class.getClassLoader().getResourceAsStream("despacho.json");
		InputStream isPortales = ConfigHolder.class.getClassLoader().getResourceAsStream("portales.json");
		InputStream isLogistica = ConfigHolder.class.getClassLoader().getResourceAsStream("logistica.json");
		
	}
	
	public ConfigHolder() {
		super();
	}

	public String getRawConfig(ConfigType type, String configName) {
		switch (type) {
		case DESPACHO:
			return despachosConfig.getProperty(configName);
		case LOGISTICA:
			return logisticaConfig.getProperty(configName);
		case PORTAL:
			return portalesConfig.getProperty(configName);
		default:
			throw new RuntimeException("No se reconoce tipo " + type);
		}
	}
	
	public String[] getServers(ConfigType type) {
		return null; //TODO 
	}
}
