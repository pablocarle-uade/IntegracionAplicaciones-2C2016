package edu.uade.ida.deposito.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.ProcesarEntregaArticuloRequestDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.EntregaArticulo;
import edu.uade.ida.deposito.model.SolicitudArticulo;
import edu.uade.ida.deposito.repository.ArticuloRepository;
import edu.uade.ida.deposito.repository.SolicitudArticuloRepository;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;
import edu.uade.ida.deposito.service.integration.DespachoServiceLocal;
import edu.uade.ida.deposito.service.integration.FabricaServiceLocal;
import edu.uade.ida.deposito.service.integration.LogisticaMonitoreoServiceLocal;
import edu.uade.ida.deposito.util.DTOUtil;
import edu.uade.ida.deposito.util.NivelAudit;

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
	private DespachoServiceLocal dsl;
	
	@Inject
	private FabricaServiceLocal fabricaService;
	
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
	public List<SolicitudArticuloDTO> getSolicitudesStock(List<Integer> idsSolicitudes) {
		return DTOUtil.getDTOs(sar.getPorIds(idsSolicitudes), SolicitudArticuloDTO.class);
	}

	@Override
	public void procesarEntregasArticulos(List<ProcesarEntregaArticuloRequestDTO> entregas) {
		// Por cada entrega solicitada se conoce idSolicitudArticulo (pendiente), cantidad
		for (ProcesarEntregaArticuloRequestDTO entrega: entregas) {
			try {
				SolicitudArticulo solicitudDeArticuloAEntregar = em.find(SolicitudArticulo.class, entrega.getIdSolicitudArticulo());
				this.createEntregaArticulo(solicitudDeArticuloAEntregar.getDTO(), entrega.getCantidad());
				log.info("Entrega de artículos procesada con éxito a partir de solicitud: " + entrega.getIdSolicitudArticulo());
			} catch (Exception e) {
				log.warning("Error al entregar artículo a partir de solicitud: " + entrega.getIdSolicitudArticulo() + " " + e.getMessage());
				lms.enviarAudit(NivelAudit.ERROR, "Fallo en entrega de articulos a despacho");
				e.printStackTrace();
			}
		}
	}

	@Override
	public SolicitudArticuloDTO createSolicitudArticulo(ArticuloDTO articulo, int cantidad, String idModuloSolicitante) throws Exception {
		if (cantidad <= 0)
			throw new Exception("cantidad no puede ser menor a 0");
		if (idModuloSolicitante == null || idModuloSolicitante.length() == 0)
			throw new Exception("idModuloSolicitante es requerido");
		Articulo articuloSolicitado = ar.getPorCodigo(articulo.getCodArticulo());
		if (articuloSolicitado != null) {
			return createSolicitudArticulo(articuloSolicitado, cantidad, idModuloSolicitante);
		} else {
			throw new Exception("No se encontró artículo solicitado con codigo " + articulo.getCodArticulo());
		}
	}

	private SolicitudArticuloDTO createSolicitudArticulo(Articulo articuloSolicitado, int cantidad, String idModuloSolicitante) throws Exception {
		List<SolicitudArticulo> pendientesModulo = sar.getPendientesPorModuloYArticulo(idModuloSolicitante, articuloSolicitado.getCodArticulo());
		SolicitudArticuloDTO solicitudDeArticuloDTO = new SolicitudArticuloDTO();
		if (pendientesModulo.isEmpty()) {
			SolicitudArticulo sa = new SolicitudArticulo(articuloSolicitado, cantidad, SolicitudArticulo.ESTADO_PENDIENTE, idModuloSolicitante);
			em.persist(sa);
			solicitudDeArticuloDTO.setIdSolicitudArticulo(sa.getIdSolicitudStock());
		} else {
			SolicitudArticulo sa = pendientesModulo.get(0); //Debería ser uno solo
			sa.setCantidad(sa.getCantidad() + cantidad);
			em.merge(sa);
			solicitudDeArticuloDTO = sa.getDTO();
		}
		log.info("Registrada solicitud de artículos desde despacho: " + idModuloSolicitante + "por una cantidad de " + cantidad + " de artículo " + articuloSolicitado.getCodArticulo());
		lms.enviarAudit(NivelAudit.INFO, "Registrada solicitud de artículos de despacho " + idModuloSolicitante + " por " + cantidad + " de artículo " + articuloSolicitado.getCodArticulo());
		return solicitudDeArticuloDTO;
	}

	@Override
	public SolicitudCompraDTO createSolicitudCompra(SolicitudArticuloDTO sa, int cantidad) throws Exception {
		try {
			SolicitudCompraDTO scd = fabricaService.crearSolicitudCompra(sa.getArticulo(), cantidad);
			lms.enviarAudit(NivelAudit.INFO, "Enviada solicitud de compra a fabrica");
			return scd;
		} catch (Exception e) {
			log.log(Level.WARNING, "Error en solicitud a la fabrica", e);
			e.printStackTrace();
			lms.enviarAudit(NivelAudit.ERROR, "Error en envio de solicitud compra a fabrica");
			throw e;
		}
	}

	@Override
	public EntregaArticuloDTO createEntregaArticulo(SolicitudArticuloDTO solicitudDeArticulo, int cantidadEntrega) throws Exception {
		SolicitudArticulo solicitudDeArticuloAEntregar = em.find(SolicitudArticulo.class, solicitudDeArticulo.getIdSolicitudArticulo());
		if (solicitudDeArticuloAEntregar != null) {
			if (verificaStock(solicitudDeArticuloAEntregar.getArticulo(), cantidadEntrega)) {
				synchronized (this) {
					if (verificaStock(solicitudDeArticuloAEntregar.getArticulo(), cantidadEntrega)) {
						EntregaArticuloDTO entregaArticulo = crearEntregaArticulo(solicitudDeArticuloAEntregar, cantidadEntrega);
						// Ya que la entrega de artículo se generó correctamnete, notificamos a los sistemas que correspondan
						notificarEntregaArticulo(entregaArticulo);
						return entregaArticulo;
					} else {
						throw new Exception("No hay stock disponible de artículo " + solicitudDeArticuloAEntregar.getArticulo() + " por " + cantidadEntrega);
					}
				}
			} else {
				throw new Exception("No hay stock disponible de artículo " + solicitudDeArticuloAEntregar.getArticulo() + " por " + cantidadEntrega);
			}
		} else {
			throw new Exception("No se encontró solicitud de artículo con id " + solicitudDeArticulo.getIdSolicitudArticulo());
		}
	}

	private void notificarEntregaArticulo(EntregaArticuloDTO entregaArticulo) {
		log.info("Generada entrega de artículo " + entregaArticulo.getIdEntregaArticulo());
		lms.enviarAudit(NivelAudit.INFO, "Generada entrega de artículo para artículo " + entregaArticulo.getCodArticulo() + " x " + entregaArticulo.getCantidadAsignada());
		dsl.notificarEntregaArticulo(entregaArticulo);
	}

	private EntregaArticuloDTO crearEntregaArticulo(SolicitudArticulo solicitudDeArticulo, int cantidadEntrega) throws Exception {
		EntregaArticulo entregaDeArticulo = new EntregaArticulo(solicitudDeArticulo, cantidadEntrega, solicitudDeArticulo.getIdModuloSolicitante());
		solicitudDeArticulo.getArticulo().setStock(solicitudDeArticulo.getArticulo().getStock() - cantidadEntrega);
		if (solicitudDeArticulo.getArticulo().getStock() < 0)
			throw new Exception("Stock incorrecto");
		solicitudDeArticulo.setEstado(SolicitudArticulo.ESTADO_ENTREGADO);
		em.merge(solicitudDeArticulo);
		em.merge(solicitudDeArticulo.getArticulo());
		em.persist(entregaDeArticulo);
		return entregaDeArticulo.getDTO();
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
