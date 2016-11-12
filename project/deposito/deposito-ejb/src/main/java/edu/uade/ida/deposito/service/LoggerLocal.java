package edu.uade.ida.deposito.service;

import java.util.logging.Level;

import javax.ejb.Local;

@Local
public interface LoggerLocal {

	public abstract void log(Object source, Level level, String message, Throwable t);
	
	public abstract void log(Object source, Level level, String message);
	
	public abstract void warn(Object source, String message);
	
	public abstract void info(Object source, String message);
	
	public abstract void debug(Object source, String message);
	
}
