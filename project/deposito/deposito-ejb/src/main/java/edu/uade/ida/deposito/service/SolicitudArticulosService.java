package edu.uade.ida.deposito.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.data.ArticuloRepository;
import edu.uade.ida.deposito.data.SolicitudArticuloRepository;
import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.SolicitudArticulo;
import edu.uade.ida.deposito.util.DTOUtil;

/**
 * Session Bean implementation class SolicitudArticulosMgrBean
 */
@Stateless
@LocalBean
public class SolicitudArticulosService implements SolicitudArticulosServiceLocal {
	
	@Inject
	private SolicitudArticuloRepository sar;
	
	@Inject 
	private ArticuloRepository ar;
	
	@Inject
	private EntityManager em;
	
	@SuppressWarnings("unused")
	@Inject
	private Logger log;
	
	
    /**
     * Default constructor. 
     */
    public SolicitudArticulosService() {
    	super();
    }

	@Override
	public List<SolicitudArticuloDTO> getSolicitudesStockPendientes() {
		return DTOUtil.getDTOs(sar.getPorEstado("pendiente", "no_cumplido"), SolicitudArticuloDTO.class);
	}

	@Override
	public void procesarEntregasArticulos(List<EntregaArticuloDTO> entregas) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public SolicitudArticuloDTO createSolicitudArticulo(ArticuloDTO articulo, int cantidad) throws Exception {
		Articulo articuloEnt = ar.getPorCodigo(articulo.getCodArticulo());
		if (articuloEnt != null) {
			SolicitudArticuloDTO sad = new SolicitudArticuloDTO();
			SolicitudArticulo sa = new SolicitudArticulo(articuloEnt, cantidad, SolicitudArticulo.ESTADO_PENDIENTE);
			em.persist(sa);
			sad.setIdSolicitudArticulo(sa.getIdSolicitudStock());
			return sad;
		} else {
			throw new Exception("No se encontro articulo con codigo " + articulo.getCodArticulo());
		}
	}

	@Override
	public SolicitudCompraDTO createSolicitudCompra(SolicitudArticuloDTO sa, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntregaArticuloDTO createEntregaArticulo(SolicitudArticuloDTO sa, int cantidadOverride) {
		// TODO Auto-generated method stub
		return null;
	}
}
