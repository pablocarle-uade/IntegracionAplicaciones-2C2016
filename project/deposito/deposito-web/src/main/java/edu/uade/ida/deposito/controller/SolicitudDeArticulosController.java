package edu.uade.ida.deposito.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

@Path("/solicitudDeArticulos")
public class SolicitudDeArticulosController {

	@Inject
	private Logger log;
	
	@Inject
	private SolicitudArticulosServiceLocal sas;
	
	@GET
	@Path("/pendientes")
	@Produces("application/json")
	@Consumes("application/json")
	public List<SolicitudArticuloDTO> getSolicitudesPendientes() {		
		return this.sas.getSolicitudesStockPendientes();
	}
	
}
