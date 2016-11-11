package edu.uade.ida.deposito.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.CreateSolicitudCompraRequestDTO;
import edu.uade.ida.deposito.rest.GenericResponseDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

@Path("/solicitudCompra")
public class SolicitudCompraController {
	
	@Inject
	private Logger log;
	
	@Inject
	private SolicitudArticulosServiceLocal as;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public GenericResponseDTO createSolicitudDeCompra(List<CreateSolicitudCompraRequestDTO> solicitudes) {
		log.info("Se ha solicitado la generación de solicitudes de compra de artículos");
		this.as.createSolicitudesCompra(solicitudes);
		// TODO: Response alternative scenarios, error, etc
		return new GenericResponseDTO();
	}

}
