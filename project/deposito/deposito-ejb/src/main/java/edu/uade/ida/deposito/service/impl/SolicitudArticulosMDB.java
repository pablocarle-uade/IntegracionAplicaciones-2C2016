package edu.uade.ida.deposito.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloRequestDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

/**
 * Message-Driven Bean implementation class for: SolicitudArticulosMDB
 */
//@MessageDriven(
//		activationConfig = { @ActivationConfigProperty(
//				propertyName = "destination", propertyValue = "java:/jms/queue/ColaSolicitudesArticulos"), @ActivationConfigProperty(
//				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
//		}, 
//		mappedName = "java:/jms/queue/ColaSolicitudesArticulos")
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/ColaSolicitudesArticulosDeposito"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jboss/exported/jms/queue/ColaSolicitudesArticulosDeposito")
public class SolicitudArticulosMDB implements MessageListener {

	@Inject
	private Logger log;
	
	@EJB
	private SolicitudArticulosServiceLocal sas;
	
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
				SolicitudArticuloRequestDTO request = gson.fromJson(((TextMessage) message).getText(), SolicitudArticuloRequestDTO.class);
				log.info("Solicitud de " + request.getCantidad() + " unidades de articulo con id " + request.getCodArticulo());
				procesarSolicitudStock(request);
			} catch (JsonSyntaxException | JMSException e) {
				log.log(Level.WARNING, "Error en parse de mensaje solicitud de articulo", e);
				e.printStackTrace();
			}
    	} else {
    		log.warning("Se recibio un mensaje no TextMessage. Message es " + message);
    	}
    }

	public void procesarSolicitudStock(SolicitudArticuloRequestDTO request) {
		try {
			SolicitudArticuloDTO sad = sas.createSolicitudArticulo(new ArticuloDTO(request.getCodArticulo()), request.getCantidad(), request.getIdDespacho());
			//TODO Comunicar a logistica y monitoreo
			log.info("Generada correctamente la solicitud de articulo " + sad);
		} catch (Exception e) {
			e.printStackTrace();
			log.log(Level.WARNING, "Ocurrio un problema al registrar SolicitudDeArticulo: " + e.getMessage(), e);
		}
	}
}
