package edu.uade.ida.deposito.service.integration;

import javax.ejb.Remote;

import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;

@Remote
public interface PortalServiceRemote {
	
	void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo);

}
