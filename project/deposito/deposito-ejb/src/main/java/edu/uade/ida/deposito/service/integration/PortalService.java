package edu.uade.ida.deposito.service.integration;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;

@Stateless
@LocalBean
public class PortalService implements PortalServiceLocal, PortalServiceRemote {

	@Inject
	private Logger log;
	
	@Override
	public void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		log.info("Notificando a Portales sobre nuevo artículo...");		
	}	

}
