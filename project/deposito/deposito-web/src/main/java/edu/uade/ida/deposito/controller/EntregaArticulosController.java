package edu.uade.ida.deposito.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.ProcesarEntregaArticuloRequestDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;
import edu.uade.ida.deposito.rest.GenericResponseDTO;

@Path("/entregaDeArticulos")
public class EntregaArticulosController {
	
	@Inject
	private Logger log;
	
	@Inject
	private SolicitudArticulosServiceLocal as;
	
	@POST
	@Path("/procesarEntrega")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericResponseDTO procesarEntregaDeArticulos(List<ProcesarEntregaArticuloRequestDTO> entregas) {
		log.info("Procesar entrega de artículos ");
		this.as.procesarEntregasArticulos(entregas);
		// TODO: Response alternative scenarios, error, etc
		return new GenericResponseDTO();
	}

}
