package edu.uade.ida.deposito.controller;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.rest.GenericResponseDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

@Path("/solicitudCompra")
public class SolicitudCompraController {
	
	@Inject
	private Logger log;
	
	@Inject
	private SolicitudArticulosServiceLocal as;
	
	@POST
	@Path("/procesarSolicitud")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericResponseDTO procesarSolicitudDeCompra() {
		log.info("Procesar solicitud de compra de artículos ");
		// TODO: Response alternative scenarios, error, etc
		return new GenericResponseDTO();
	}

}
