package edu.uade.ida.mocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: EntregaArticulosMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/EntregaArticulos"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jboss/exported/jms/queue/EntregaArticulos")
public class EntregaArticulosMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public EntregaArticulosMDB() {
    	super();
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
			Logger.getAnonymousLogger().log(Level.INFO, "Recibida entrega de articulos a despacho. Mensaje: [" + ((TextMessage) message).getText() + "]");
		} catch (JMSException e) {
			Logger.getAnonymousLogger().log(Level.WARNING, "error en gettext de mensaje jms");
			e.printStackTrace();
		}
    }
}
