package edu.uade.ida.deposito.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;

/**
 * Servicio para administracion de solicitudes de articulos
 * (generar entregas de articulos, generar solicitudes de compras
 * basadas en solicitudes de articulos)
 * 
 * TODO Ver de pasar la creacion de solicitudes de articulos tambien
 * 
 * @author pabloc
 *
 */
@Stateless
public class SolicitudArticulosService implements SolicitudArticulosServiceLocal {

	@Inject
	private Logger log;
	
	@Inject
	private EntityManager em;
	
	public SolicitudArticulosService() {
		super();
	}

	@Override
	public SolicitudArticuloDTO createSolicitudArticulo(ArticuloDTO articulo, int cantidad) {
		
		return null;
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
