package edu.uade.ida.deposito.service.integration.core;

import java.util.Properties;
import java.util.logging.Level;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uade.ida.deposito.service.LoggerLocal;

@Singleton
public class JMSClient {
	
	@Inject
	private LoggerLocal log;
	 
    public void invoke(JMSClientConfiguration config) throws Exception {
        // Context will be crated from configuration
    	Context namingContext = null;
        JMSContext context = null; 
        try {        	
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            env.put(Context.PROVIDER_URL, config.getProviderUrl());
            env.put("jboss.naming.client.connect.timeout", String.valueOf(15 * 1000));
            
            env.put(Context.SECURITY_PRINCIPAL, config.getUser());
            env.put(Context.SECURITY_CREDENTIALS, config.getPassword());
            
            namingContext = new InitialContext(env); 
            // Create connection factory from init context
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup("jms/RemoteConnectionFactory");
            log.info(this, "Got ConnectionFactory");
            // Lookup destination
            Destination destination = (Destination) namingContext.lookup(config.getDestination());
            log.info(this, "Got JMS Endpoint " + config.getDestination());
             // Create the JMS context
            context = connectionFactory.createContext(config.getUser(), config.getPassword());
            // Send message
            context.createProducer().send(destination, config.getMessage());
            log.info(this, "Sent message " + config.getMessage());
        } catch (Exception e) {
        	log.log(this, Level.WARNING, "JMSClient invoke failed: " + e.getMessage(), e);
        	e.printStackTrace();
            // throw e;
        } finally {
            if (namingContext != null) { namingContext.close(); }
            if (context != null) { context.close(); }
        }
    }

}
