package edu.uade.ida.deposito.service;

import edu.uade.ida.deposito.dto.ArticuloDTO;

public interface SolicitudArticulosServiceLocal {

	public void createSolicitudArticulo(ArticuloDTO articulo, int cantidad);
	
}
