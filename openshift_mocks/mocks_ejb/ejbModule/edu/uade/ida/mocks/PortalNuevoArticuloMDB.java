package edu.uade.ida.mocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: PortalNuevoArticuloMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/PortalNuevoArticulo"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jboss/exported/jms/queue/PortalNuevoArticulo")
public class PortalNuevoArticuloMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public PortalNuevoArticuloMDB() {
    	super();
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	Logger.getAnonymousLogger().log(Level.INFO, "Recibida notificacion nuevo articulo en portal. Mensaje: [" + message + "]");
    }
}
