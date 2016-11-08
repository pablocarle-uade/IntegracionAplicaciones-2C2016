package edu.uade.ida.deposito.service.integration;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.util.config.ConfigHolder;

/**
 * Session Bean implementation class FabricaService
 */
@Stateless
@LocalBean
public class FabricaService implements FabricaServiceLocal {

	@Inject
	private Logger log;

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
		Articulo art = em.find(Articulo.class, articulo.getId());
		if (art == null)
			throw new Exception("No se encontro articulo con id " + articulo.getId());
		SolicitudCompraDTO scd = new SolicitudCompraDTO();
		
		
		
		
		return scd;
	}
}
