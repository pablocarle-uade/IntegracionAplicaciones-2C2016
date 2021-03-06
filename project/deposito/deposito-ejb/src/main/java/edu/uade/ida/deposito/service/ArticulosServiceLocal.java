package edu.uade.ida.deposito.service;

import java.util.List;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.CreateArticuloRequestDTO;
import edu.uade.ida.deposito.dto.ModificacionStockRequestDTO;
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
	 * @param createArticuloRequestDTO Los datos del nuevo artículo
	 * @return
	 */
	ArticuloDTO crearArticulo(CreateArticuloRequestDTO createArticuloRequestDTO);
	
	/**
	 * Crear lista de articulos default del sistema
	 * 
	 * @return Cantidad de articulos creados
	 */
	int createArticulosDefault();

	void modificarStockDeArticulos(List<ModificacionStockRequestDTO> modificacionesS);

	List<ArticuloDTO> getArticulosPorIds(List<Long> ids);
	
}
