package edu.uade.ida.mocks;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: LogisticaMonitoreoAuditMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/eventoAuditoria"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jboss/exported/jms/queue/eventoAuditoria")
public class LogisticaMonitoreoAuditMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public LogisticaMonitoreoAuditMDB() {
    	super();
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	Logger.getAnonymousLogger().log(Level.INFO, "Recibido mensaje auditoria JMS [" + message + "]");
    }
}
