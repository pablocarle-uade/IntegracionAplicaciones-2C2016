package edu.uade.ida.deposito.service;

import java.util.List;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.CreateEntregaArticuloRequestDTO;
import edu.uade.ida.deposito.dto.CreateSolicitudCompraRequestDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;

@Local
public interface SolicitudArticulosServiceLocal {
	
	List<SolicitudArticuloDTO> getSolicitudesStockPendientes();
	
	List<SolicitudArticuloDTO> getSolicitudesStock(List<Integer> idsSolicitudes);

	/**
	 * Generar entregas de artículos según los valores especificados
	 * 
	 * @param entregas
	 */
	void createEntregasArticulos(List<CreateEntregaArticuloRequestDTO> entregas);		
	
	/**
	 * @param articulo
	 * @param cantidad
	 * @return
	 * @throws Exception 
	 */
	SolicitudArticuloDTO createSolicitudArticulo(ArticuloDTO articulo, int cantidad, String idModuloSolicitante) throws Exception;
	
	/**
	 * TODO Definir argumentos
	 * 
	 * @param art
	 * @param cantidad
	 * @return
	 * @throws Exception 
	 */
	SolicitudCompraDTO createSolicitudCompra(SolicitudArticuloDTO sa, int cantidad) throws Exception;
	
	void createSolicitudesCompra(List<CreateSolicitudCompraRequestDTO> solicitudes);
	
	/**
	 * Generar entrega de articulos
	 * 
	 * @return
	 * @throws Exception 
	 */
	EntregaArticuloDTO createEntregaArticulo(SolicitudArticuloDTO sa, int cantidadOverride) throws Exception;

	
}
