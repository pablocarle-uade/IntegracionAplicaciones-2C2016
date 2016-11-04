package edu.uade.ida.deposito.service;

import java.util.List;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SearchArticulosDTO;

public interface ArticulosServiceLocal {
	
	/**
	 * Buscar artículos en el sistema
	 * 
	 * @param searchArticulosDTO Parámetros de búsqueda
	 * @return lista de artículos que cumplen los filtros especificados
	 */
	List<ArticuloDTO> buscarArticulos(SearchArticulosDTO searchArticulosDTO);
	
	/**
	 * Crear artículo en el sistema
	 * 
	 * @param articuloDTO Los datos del nuevo artículo
	 * @return
	 */
	ArticuloDTO crearArticulo(ArticuloDTO articuloDTO);
	
	/**
	 * TODO: por ahora, por necesidad para procesar solicitudes de articulos
	 * modificar segun sea necesario
	 * 
	 * @param articulo El articulo
	 * @return
	 */
	int getStockDisponible(ArticuloDTO articulo);
	
	/**
	 * Crear lista de articulos default del sistema
	 * 
	 * @return Cantidad de articulos creados
	 */
	int createArticulosDefault();
	
}
