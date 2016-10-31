package edu.uade.ida.deposito.service;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;

public interface SolicitudArticulosServiceLocal {

	public SolicitudArticuloDTO createSolicitudArticulo(ArticuloDTO articulo, int cantidad);
	
	/**
	 * TODO Definir argumentos
	 * 
	 * @param art
	 * @param cantidad
	 * @return
	 */
	public SolicitudCompraDTO createSolicitudCompra(SolicitudArticuloDTO sa, int cantidad);
	
	/**
	 * TODO Definir argumentos
	 * 
	 * @return
	 */
	public EntregaArticuloDTO createEntregaArticulo(SolicitudArticuloDTO sa, int cantidadOverride);
	
}
