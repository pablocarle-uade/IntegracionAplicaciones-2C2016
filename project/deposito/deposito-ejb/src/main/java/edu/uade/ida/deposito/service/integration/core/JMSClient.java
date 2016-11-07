package edu.uade.ida.deposito.service.integration.core;

import javax.inject.Inject;

import java.util.Properties;
import java.util.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;

// @Singleton
public class JMSClient { // TODO: Check maven dependencies from example if this client fails
	
	@Inject
	private Logger log;
	 
    // "http-remoting://192.168.0.43:8080" "15000"
    public void invoke(JMSClientConfiguration config) throws Exception {
        // Context will be crated from configuration
    	Context namingContext = null;
        JMSContext context = null; 
        try {        	
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            env.put(Context.PROVIDER_URL, config.getProviderUrl());
            env.put("jboss.naming.client.connect.timeout", String.valueOf(30 * 1000));
            namingContext = new InitialContext(env); 
            // namingContext = new InitialContext(); 
            // Create connection factory from init context
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup("jms/RemoteConnectionFactory");
            // ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup("java:jboss/exported/jms/RemoteConnectionFactory");
            log.info("Got ConnectionFactory");
            // Lookup destination
            Destination destination = (Destination) namingContext.lookup(config.getDestination());
            log.info("Got JMS Endpoint " + config.getDestination());
             // Create the JMS context
            context = connectionFactory.createContext(config.getUser(), config.getPassword());
            // Send message
            context.createProducer().send(destination, config.getMessage());
            log.info("Sent message " + config.getMessage());
        } catch (Exception e) {
        	log.info("JMSClient invoke failed: " + e.getMessage());
        	e.printStackTrace();
            throw e;
        } finally {
            if (namingContext != null) { namingContext.close(); }
            if (context != null) { context.close(); }
        }
    }

}
