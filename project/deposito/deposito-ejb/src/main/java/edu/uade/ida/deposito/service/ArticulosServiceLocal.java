package edu.uade.ida.deposito.service;

import edu.uade.ida.deposito.dto.ArticuloDTO;

public interface ArticulosServiceLocal {
	
	ArticuloDTO crearArticulo(ArticuloDTO articuloDTO);
	
	/**
	 * TODO: por ahora, por necesidad para procesar solicitudes de articulos
	 * modificar segun sea necesario
	 * 
	 * @param articulo El articulo
	 * @return
	 */
	int getStockDisponible(ArticuloDTO articulo);
	
}
