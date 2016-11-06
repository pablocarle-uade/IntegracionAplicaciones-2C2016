package edu.uade.ida.deposito.service.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.EntregaArticulo;
import edu.uade.ida.deposito.model.SolicitudArticulo;
import edu.uade.ida.deposito.repository.ArticuloRepository;
import edu.uade.ida.deposito.repository.SolicitudArticuloRepository;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;
import edu.uade.ida.deposito.service.integration.LogisticaMonitoreoServiceLocal;
import edu.uade.ida.deposito.service.integration.NivelAudit;
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
	
	@Inject
	private LogisticaMonitoreoServiceLocal lms;
	
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
		return DTOUtil.getDTOs(sar.getPorEstado(SolicitudArticulo.ESTADO_PENDIENTE, SolicitudArticulo.ESTADO_NO_CUMPLIDO), SolicitudArticuloDTO.class);
	}

	@Override
	public void procesarEntregasArticulos(List<EntregaArticuloDTO> entregas) {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public SolicitudArticuloDTO createSolicitudArticulo(ArticuloDTO articulo, int cantidad, String idModuloSolicitante) throws Exception {
		if (cantidad <= 0)
			throw new Exception("cantidad no puede ser menor a 0");
		if (idModuloSolicitante == null || idModuloSolicitante.length() == 0)
			throw new Exception("idModuloSolicitante es requerido");
		Articulo articuloEnt = ar.getPorCodigo(articulo.getCodArticulo());
		if (articuloEnt != null) {
			return createSolicitudArticulo(articuloEnt, cantidad, idModuloSolicitante);
			
		} else {
			throw new Exception("No se encontro articulo con codigo " + articulo.getCodArticulo());
		}
	}

	private SolicitudArticuloDTO createSolicitudArticulo(Articulo articuloEnt, int cantidad, String idModuloSolicitante) throws Exception {
		List<SolicitudArticulo> pendientesModulo = sar.getPendientesPorModuloYArticulo(idModuloSolicitante, articuloEnt.getCodArticulo());
		SolicitudArticuloDTO sad = new SolicitudArticuloDTO();
		if (pendientesModulo.isEmpty()) {
			SolicitudArticulo sa = new SolicitudArticulo(articuloEnt, cantidad, SolicitudArticulo.ESTADO_PENDIENTE, idModuloSolicitante);
			em.persist(sa);
			sad.setIdSolicitudArticulo(sa.getIdSolicitudStock());
		} else {
			SolicitudArticulo sa = pendientesModulo.get(0); //Deberia ser uno solo ya fue
			sa.setCantidad(sa.getCantidad() + cantidad);
			em.merge(sa);
			sad = sa.getDTO();
		}
		log.info("Registrada solicitud de articulos de despacho " + idModuloSolicitante + "por " + cantidad + " de articulo " + articuloEnt.getCodArticulo());
		lms.enviarAudit(NivelAudit.INFO, "Registrada solicitud de articulos de despacho " + idModuloSolicitante + " por " + cantidad + " de articulo " + articuloEnt.getCodArticulo());
		return sad;
	}

	@Override
	public SolicitudCompraDTO createSolicitudCompra(SolicitudArticuloDTO sa, int cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntregaArticuloDTO createEntregaArticulo(SolicitudArticuloDTO sa, int cantidadEntrega) throws Exception {
		SolicitudArticulo saEnt = em.find(SolicitudArticulo.class, sa.getIdSolicitudArticulo());
		if (saEnt != null) {
			if (verificaStock(saEnt.getArticulo(), cantidadEntrega)) {
				synchronized (this) {
					if (verificaStock(saEnt.getArticulo(), cantidadEntrega)) {
						EntregaArticuloDTO entregaArticulo = crearEntregaArticulo(saEnt, cantidadEntrega);
						//Ya que la entrega de articulo se genero correctamnete, notificamos a los sistemas que correspondan
						notificarEntregaArticulo(entregaArticulo);
						return entregaArticulo;
					} else {
						throw new Exception("No hay stock disponible"); //TODO Mensaje excepcion
					}
				}
			} else {
				throw new Exception("No hay stock disponible"); //TODO Mensaje excepcion
			}
		} else {
			throw new Exception("No se encontro solicitud de articulo con id " + sa.getIdSolicitudArticulo());
		}
	}

	private void notificarEntregaArticulo(EntregaArticuloDTO entregaArticulo) {
		// TODO Auto-generated method stub
		
	}

	private EntregaArticuloDTO crearEntregaArticulo(SolicitudArticulo sa, int cantidadEntrega) throws Exception {
		EntregaArticulo ea = new EntregaArticulo(sa, cantidadEntrega);
		sa.getArticulo().setStock(sa.getArticulo().getStock() - cantidadEntrega);
		if (sa.getArticulo().getStock() < 0)
			throw new Exception("Stock incorrecto");
		em.merge(sa.getArticulo());
		em.persist(ea);
		return ea.getDTO();
	}

	/**
	 * Verificar existencia de stock disponible para cumplir con entrega solicitada
	 * 
	 * @param articulo El articulo
	 * @param cantidadEntrega La cantidad
	 * @return
	 */
	private boolean verificaStock(Articulo articulo, int cantidadEntrega) {
		return (articulo.getStock() - cantidadEntrega) >= 0;
	}
}
