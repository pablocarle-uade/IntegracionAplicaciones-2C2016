package edu.uade.ida.deposito.service.impl;

import java.util.logging.Level;

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
import edu.uade.ida.deposito.service.LoggerLocal;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;
import edu.uade.ida.deposito.service.integration.LogisticaMonitoreoServiceLocal;
import edu.uade.ida.deposito.util.NivelAudit;
import edu.uade.ida.deposito.util.config.ConfigHolder;

/**
 * Message-Driven Bean implementation class for: SolicitudArticulosMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/ColaSolicitudesArticulos"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jboss/exported/jms/queue/ColaSolicitudesArticulos")
public class SolicitudArticulosMDB implements MessageListener {

	@Inject
	private LoggerLocal log;
	
	@Inject
	private ConfigHolder config;
	
	@EJB
	private SolicitudArticulosServiceLocal sas;
	
	@Inject
	private LogisticaMonitoreoServiceLocal lms;
	
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
    	log.info(this, "Recibido mensaje solicitud de artículos");
    	if (message instanceof TextMessage) {
    		Gson gson = new Gson();
    		try {
    			String messageContent = ((TextMessage) message).getText();
    			log.info(this, "Recibido mensaje: " + messageContent);
				SolicitudArticuloRequestDTO request = gson.fromJson(messageContent, SolicitudArticuloRequestDTO.class);
				log.info(this, "Solicitud de " + request.getCantidad() + " unidades de artículo con id " + request.getCodArticulo());
				procesarSolicitudStock(request);
			} catch (JsonSyntaxException | JMSException e) {
				log.log(this, Level.WARNING, "Error en parse de mensaje solicitud de artículo", e);
				e.printStackTrace();
			}
    	} else {
    		log.warn(this, "Se recibió un mensaje no TextMessage. Message es: " + message);
    	}
    }

	public void procesarSolicitudStock(SolicitudArticuloRequestDTO request) {
		try {
			SolicitudArticuloDTO sad = sas.createSolicitudArticulo(new ArticuloDTO(request.getCodArticulo()), request.getCantidad(), request.getIdDespacho());
			lms.enviarAudit(NivelAudit.INFO, "Generada solicitud de artículo " + sad + "en depósito: " + config.getIdDeposito());
			log.info(this, "Generada correctamente la solicitud de artículo " + sad);
		} catch (Exception e) {
			e.printStackTrace();
			log.log(this, Level.WARNING, "Ocurrió un problema al registrar SolicitudDeArticulo: " + e.getMessage(), e);
		}
	}
}
