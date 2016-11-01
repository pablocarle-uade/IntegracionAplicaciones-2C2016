package edu.uade.ida.deposito.service;

import java.util.List;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;

@Local
public interface SolicitudArticulosServiceLocal {
	
	public List<SolicitudArticuloDTO> getSolicitudesStockPendientes();

	/**
	 * Generar entregas de articulos
	 * 
	 * @param entregas
	 */
	public void procesarEntregasArticulos(List<EntregaArticuloDTO> entregas);
	
	/**
	 * @param articulo
	 * @param cantidad
	 * @return
	 */
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
