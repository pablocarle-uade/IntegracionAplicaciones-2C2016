package edu.uade.ida.deposito.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
import edu.uade.ida.deposito.model.RecepcionDeCompra;
import edu.uade.ida.deposito.model.RecepcionDeCompraItem;
import edu.uade.ida.deposito.model.SolicitudCompra;
import edu.uade.ida.deposito.service.LoggerLocal;

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
	private LoggerLocal log;
	
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
				log.log(this, Level.WARNING, "Error obteniendo contenido del mensaje", e);
				e.printStackTrace();
			}
    	} else {
    		log.log(this, Level.WARNING, "Mensaje debe ser TextMessage pero es: " + message);
    	}
    }

	private void procesarRecepcionCompra(String json) {
		RecepcionCompraDTO rcd = new Gson().fromJson(json, RecepcionCompraDTO.class);
		SolicitudCompra sc = em.find(SolicitudCompra.class, rcd.getIdSolicitudCompra());
		if (sc == null) {
			log.log(this, Level.WARNING, "No se encontro la solicitud de compra con id " + rcd.getIdSolicitudCompra());
			return;
		} else if (sc.getEstado().equals(SolicitudCompra.ESTADO_ENTREGADO)) {
			log.log(this, Level.WARNING, "La solicitud de compra ya esta entregada");
			return;
		}
		sc.setEstado(SolicitudCompra.ESTADO_ENTREGADO);
		actualizarStocks(rcd, sc);
		em.merge(sc);
	}

	private void actualizarStocks(RecepcionCompraDTO rcd, SolicitudCompra sc) {
		log.info(this, "Actualizando stocks por solicitud de compra");
		Articulo articulo = null;
		RecepcionDeCompra recepcionCompraEntity = new RecepcionDeCompra();
		List<RecepcionDeCompraItem> items = new ArrayList<>(rcd.getItems().size());
		recepcionCompraEntity.setSolicitudCompra(sc);
		recepcionCompraEntity.setItems(items);
		
		RecepcionDeCompraItem itemEntity = null;
		for (RecepcionCompraItemDTO item : rcd.getItems()) {
			articulo = sc.getArticulo(item.getCodArticulo());
			if (articulo != null) {
				itemEntity = new RecepcionDeCompraItem();
				itemEntity.setArticulo(articulo);
				itemEntity.setCantidadEntregada(item.getCantidadEntregada());
				articulo.setStock(articulo.getStock() + item.getCantidadEntregada());
				em.merge(articulo);
				items.add(itemEntity);
			} else {
				log.warn(this, "No se encontro el articulo con codigo " + item.getCodArticulo() + " en solicitud de compra " + sc.getIdSolicitudCompra());
			}
		}
		em.persist(recepcionCompraEntity);
		log.info(this, "Fin actualizacion stocks por solicitud de compra");
	}
}
