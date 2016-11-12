package edu.uade.ida.deposito.service.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.CompletionListener;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;

import com.google.gson.Gson;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.RecepcionCompraDTO;
import edu.uade.ida.deposito.dto.RecepcionCompraItemDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.SolicitudCompra;
import edu.uade.ida.deposito.model.SolicitudCompraItem;
import edu.uade.ida.deposito.service.LoggerLocal;
import edu.uade.ida.deposito.util.config.ConfigHolder;

/**
 * Session Bean implementation class FabricaService
 */
@Stateless
@LocalBean
public class FabricaService implements FabricaServiceLocal {

	@Inject
	private LoggerLocal log;

	@SuppressWarnings("unused")
	@Inject
	private ConfigHolder config;
	
	@Inject
	private EntityManager em;
	
	@Resource(mappedName = "java:/jboss/exported/jms/queue/RecepcionCompraQueue")
	private Queue csa;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
	
    /**
     * Default constructor. 
     */
    public FabricaService() {
    	super();
    }

	@Override
	public SolicitudCompraDTO crearSolicitudCompra(ArticuloDTO articulo, int cantidadSolicitada) throws Exception {
		Map<ArticuloDTO, Integer> map = new HashMap<>();
		map.put(articulo, cantidadSolicitada);
		return crearSolicitudCompra(map);
	}

	@Override
	public SolicitudCompraDTO crearSolicitudCompra(Map<ArticuloDTO, Integer> cantidades) throws Exception {
		log.info(this, "Solicitud de compra");
		
		SolicitudCompra sc = new SolicitudCompra();
		List<SolicitudCompraItem> items = new ArrayList<>();
		Articulo art = null;
		SolicitudCompraItem item = null;
		for (Map.Entry<ArticuloDTO, Integer> entry : cantidades.entrySet()) {
			art = em.find(Articulo.class, entry.getKey().getId());
			if (art == null)
				throw new Exception("No se encontro articulo con id " + entry.getKey().getId());
			item = new SolicitudCompraItem(sc, art, entry.getValue());
			items.add(item);
		}
		sc.setItems(items);
		em.persist(sc);
		invocarRecepcionarCompra(sc);
		
		return sc.getDTO();
	}

	/**
	 * Simula la fabrica invocando la queue de recepcion de compras
	 * 
	 * @param sc La solicitud de compra original
	 */
	private void invocarRecepcionarCompra(SolicitudCompra sc) {
		//Invocar JMS
		RecepcionCompraDTO dto = generarRecepcionCompra100(sc);
		String json = new Gson().toJson(dto);
		
		Connection conn = null;
		try {
			conn = factory.createConnection();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			conn.start();
			MessageProducer producer = session.createProducer(csa);
			TextMessage tm = session.createTextMessage();
			tm.setText(json);
			producer.send(tm, new CompletionListener() {
				
				@Override
				public void onException(Message arg0, Exception arg1) {
					log.log(this, Level.WARNING, "Error en delivery de jms a fabrica", arg1);
				}
				
				@Override
				public void onCompletion(Message arg0) {
					log.info(this, "JMS entregado correctamente a fabrica");
				}
			});
		} catch (JMSException e) {
			log.log(this, Level.WARNING, "Error al simular fabrica enviando recepcion de compra", e);
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
		}
	}

	private RecepcionCompraDTO generarRecepcionCompra100(SolicitudCompra sc) {
		RecepcionCompraDTO rcd = new RecepcionCompraDTO();
		rcd.setIdRecepcionCompra(-1);
		rcd.setIdSolicitudCompra(sc.getIdSolicitudCompra());
		List<RecepcionCompraItemDTO> items = new ArrayList<>();
		for (SolicitudCompraItem itemEnt : sc.getItems()) {
			//En todos los casos se entrega 100%
			items.add(new RecepcionCompraItemDTO(Integer.valueOf(itemEnt.getArticulo().getCodArticulo()), itemEnt.getCantidadSolicitada()));
		}
		rcd.setItems(items);
		return rcd;
	}
}
