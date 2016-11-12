package edu.uade.ida.deposito.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.CreateEntregaArticuloRequestDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;
import edu.uade.ida.deposito.rest.GenericResponseDTO;

@Path("/entregasArticulo")
public class EntregaArticuloController {
	
	@Inject
	private Logger log;
	
	@Inject
	private SolicitudArticulosServiceLocal as;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public GenericResponseDTO createEntregas(List<CreateEntregaArticuloRequestDTO> entregas) {
		log.info("Se ha solicitado la generación de entregas de artículos ");
		this.as.createEntregasArticulos(entregas);
		return new GenericResponseDTO();
	}

}