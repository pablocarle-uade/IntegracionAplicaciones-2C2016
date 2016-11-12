package edu.uade.ida.deposito.service;

import java.util.logging.Level;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 * Session Bean implementation class Logger
 */
@Singleton
@LocalBean
public class Logger implements LoggerLocal {

	@Inject
	private java.util.logging.Logger log;
	
    /**
     * Default constructor. 
     */
    public Logger() {
    	super();
    }

	@Override
	public void log(Object source, Level level, String message, Throwable t) {
		log.log(level, "DEPOSITO_APP: " + source.getClass().getSimpleName() + " - " + message,t);
	}

	@Override
	public void log(Object source, Level level, String message) {
		log.log(level, "DEPOSITO_APP: " + source.getClass().getSimpleName() + " - " + message);
	}

	@Override
	public void warn(Object source, String message) {
		log(source, Level.WARNING, message);
	}

	@Override
	public void info(Object source, String message) {
		log(source, Level.INFO, message);
	}

	@Override
	public void debug(Object source, String message) {
		log(source, Level.FINE, message);
	}
}
