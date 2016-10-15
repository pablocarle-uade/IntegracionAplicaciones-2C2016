package edu.uade.ida.deposito.service;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: SolicitudArticulosMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/ColaSolicitudesArticulos"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/ColaSolicitudesArticulos")
public class SolicitudArticulosMDB implements MessageListener {

	@Inject
	private Logger log;
	
    /**
     * Default constructor. 
     */
    public SolicitudArticulosMDB() {
    	super();
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	if (message instanceof TextMessage) {
    		
    	} else {
    		log.warning("Se recibio un mensaje no TextMessage");
    	}
    }
}
