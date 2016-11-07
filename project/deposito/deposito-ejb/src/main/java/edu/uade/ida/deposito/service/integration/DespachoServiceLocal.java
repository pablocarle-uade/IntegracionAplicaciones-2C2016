package edu.uade.ida.deposito.service.integration;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;

@Local
public interface DespachoServiceLocal {

	void notificarEntregaArticulo(EntregaArticuloDTO entregaArticulo);
	
	void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo);
	
}
