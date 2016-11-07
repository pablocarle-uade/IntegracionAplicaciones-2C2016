package edu.uade.ida.deposito.service.integration;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.EntregaArticuloDTO;

@Local
public interface DespachoServiceLocal {

	public abstract void notificarEntregaArticulo(EntregaArticuloDTO entregaArticulo); 
	
}
