package edu.uade.ida.deposito.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.service.LoggerLocal;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

@Path("/solicitudArticulo")
public class SolicitudArticuloController {

	@Inject
	private LoggerLocal log;
	
	@Inject
	private SolicitudArticulosServiceLocal sas;
	
	@GET
	@Path("/pendientes")
	@Produces("application/json")
	@Consumes("application/json")
	public List<SolicitudArticuloDTO> getSolicitudesPendientes() {
		log.info(this, "llamada a obtener solicitudes pendientes");
		return this.sas.getSolicitudesStockPendientes();
	}
	
}
