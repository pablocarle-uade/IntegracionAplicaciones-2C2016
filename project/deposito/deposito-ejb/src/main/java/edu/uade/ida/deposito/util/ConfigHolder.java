package edu.uade.ida.deposito.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHolder {
	
	public enum ConfigType {
		DESPACHO, PORTAL, LOGISTICA;
	}
	
	private static Properties despachosConfig;
	private static Properties portalesConfig;
	private static Properties logisticaConfig;

	static {
		InputStream isDespachos = ConfigHolder.class.getClassLoader().getResourceAsStream("despachos.properties");
		InputStream isPortales = ConfigHolder.class.getClassLoader().getResourceAsStream("portales.properties");
		InputStream isLogistica = ConfigHolder.class.getClassLoader().getResourceAsStream("logistica.properties");
		
		despachosConfig = new Properties();
		portalesConfig = new Properties();
		logisticaConfig = new Properties();
		
		try {
			despachosConfig.load(isDespachos);
			portalesConfig.load(isPortales);
			logisticaConfig.load(isLogistica);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
