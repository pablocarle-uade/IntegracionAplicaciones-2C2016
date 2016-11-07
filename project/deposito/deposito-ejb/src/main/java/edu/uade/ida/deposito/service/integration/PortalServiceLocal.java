package edu.uade.ida.deposito.service.integration;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;

@Local
public interface PortalServiceLocal {
	
	void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo);

}
