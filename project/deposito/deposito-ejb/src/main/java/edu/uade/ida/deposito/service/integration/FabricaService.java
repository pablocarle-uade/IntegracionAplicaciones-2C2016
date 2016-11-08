package edu.uade.ida.deposito.service.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTOItem;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.SolicitudCompra;
import edu.uade.ida.deposito.model.SolicitudCompraItem;
import edu.uade.ida.deposito.util.config.ConfigHolder;

/**
 * Session Bean implementation class FabricaService
 */
@Stateless
@LocalBean
public class FabricaService implements FabricaServiceLocal {

	@Inject
	private Logger log;

	@SuppressWarnings("unused")
	@Inject
	private ConfigHolder config;
	
	@Inject
	private EntityManager em;
	
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
		log.info("Solicitud de compra");
		
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
		invocarRecepcionarCompra(sc);
		return sc.getDTO();
	}

	private void invocarRecepcionarCompra(SolicitudCompra sc) {
		// TODO Auto-generated method stub
		
	}
}
