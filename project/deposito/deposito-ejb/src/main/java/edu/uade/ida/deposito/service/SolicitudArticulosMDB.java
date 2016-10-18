package edu.uade.ida.deposito.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import edu.uade.ida.deposito.data.ArticuloRepository;
import edu.uade.ida.deposito.dto.SolicitudArticuloRequest;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.SolicitudArticulo;

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
	
	@Inject
	private EntityManager em;
	
	@Inject
	private ArticuloRepository ar;
	
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
    	log.info("Recibido mensaje solicitud de stock");
    	if (message instanceof TextMessage) {
    		Gson gson = new Gson();
    		try {
				SolicitudArticuloRequest request = gson.fromJson(((TextMessage) message).getText(), SolicitudArticuloRequest.class);
				log.info("Solicitud de " + request.getCantidad() + " unidades de articulo con id " + request.getIdArticulo());
				procesarSolicitudStock(request);
			} catch (JsonSyntaxException | JMSException e) {
				log.log(Level.WARNING, "Error en parse de mensaje solicitud de articulo", e);
				e.printStackTrace();
			}
    	} else {
    		log.warning("Se recibio un mensaje no TextMessage. Message es " + message);
    	}
    }

	public void procesarSolicitudStock(SolicitudArticuloRequest request) {
		Articulo articulo = ar.getPorId(String.valueOf(request.getIdArticulo()));
		if (articulo == null) {
			//No se crea solicitud de articulo
			log.warning("No se encontro articulo solicitado con id " + request.getIdArticulo());
		} else {
			em.persist(new SolicitudArticulo(articulo, request.getCantidad(), SolicitudArticulo.ESTADO_PENDIENTE));
			log.info("Guardada solicitud de articulo");
		}
	}
}
