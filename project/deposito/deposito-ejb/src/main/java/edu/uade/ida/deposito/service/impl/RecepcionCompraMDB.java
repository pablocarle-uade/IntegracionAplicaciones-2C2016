package edu.uade.ida.deposito.service.impl;

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

import edu.uade.ida.deposito.dto.RecepcionCompraDTO;
import edu.uade.ida.deposito.dto.RecepcionCompraItemDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.SolicitudCompra;

/**
 * Message-Driven Bean implementation class for: RecepcionCompraMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/RecepcionCompraQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jboss/exported/jms/queue/RecepcionCompraQueue")
public class RecepcionCompraMDB implements MessageListener {

	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public RecepcionCompraMDB() {
    	super();
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	if (message instanceof TextMessage) {
    		try {
				String json = ((TextMessage) message).getText();
				procesarRecepcionCompra(json);
			} catch (JMSException e) {
				log.log(Level.WARNING, "Error obteniendo contenido del mensaje", e);
				e.printStackTrace();
			}
    	} else {
    		log.log(Level.WARNING, "Mensaje debe ser TextMessage pero es: " + message);
    	}
    }

	private void procesarRecepcionCompra(String json) {
		RecepcionCompraDTO rcd = new Gson().fromJson(json, RecepcionCompraDTO.class);
		SolicitudCompra sc = em.find(SolicitudCompra.class, rcd.getIdSolicitudCompra());
		if (sc == null) {
			log.log(Level.WARNING, "No se encontro la solicitud de compra con id " + rcd.getIdSolicitudCompra());
			return;
		} else if (sc.getEstado().equals(SolicitudCompra.ESTADO_ENTREGADO)) {
			log.log(Level.WARNING, "La solicitud de compra ya esta entregada");
			return;
		}
		sc.setEstado(SolicitudCompra.ESTADO_ENTREGADO);
		actualizarStocks(rcd, sc);
		em.merge(sc);
	}

	private void actualizarStocks(RecepcionCompraDTO rcd, SolicitudCompra sc) {
		//TODO Generar la solicitud de compra en BD
		log.info("Actualizando stocks por solicitud de compra");
		Articulo articulo = null;
		
		for (RecepcionCompraItemDTO item : rcd.getItems()) {
			articulo = sc.getArticulo(item.getCodArticulo());
			if (articulo != null) {
				articulo.setStock(articulo.getStock() + item.getCantidadEntregada());
				em.merge(articulo);
			} else {
				log.warning("No se encontro el articulo con codigo " + item.getCodArticulo() + " en solicitud de compra " + sc.getIdSolicitudCompra());
			}
		}
		log.info("Fin actualizacion stocks por solicitud de compra");
	}
}
