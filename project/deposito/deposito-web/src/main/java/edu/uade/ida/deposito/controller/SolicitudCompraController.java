package edu.uade.ida.deposito.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.ItemSolicitudCompraRequestDTO;
import edu.uade.ida.deposito.rest.GenericResponseDTO;
import edu.uade.ida.deposito.service.LoggerLocal;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

@Path("/solicitudCompra")
public class SolicitudCompraController {
	
	@Inject
	private LoggerLocal log;
	
	@Inject
	private SolicitudArticulosServiceLocal as;
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public GenericResponseDTO createSolicitudDeCompra(List<ItemSolicitudCompraRequestDTO> itemsSolicitudCompra) {
		log.info(this,"Se ha solicitado la generación de solicitudes de compra de artículos");
		this.as.createSolicitudesCompra(itemsSolicitudCompra);
		// TODO: Response alternative scenarios, error, etc
		return new GenericResponseDTO();
	}

}
