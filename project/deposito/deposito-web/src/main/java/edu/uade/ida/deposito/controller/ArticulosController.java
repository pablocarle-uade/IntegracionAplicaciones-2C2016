package edu.uade.ida.deposito.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloRequestDTO;
import edu.uade.ida.deposito.dto.SearchArticulosDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloRequestDTO;
import edu.uade.ida.deposito.service.ArticulosServiceLocal;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

@Path("/articulos")
public class ArticulosController {
	
	@Inject
	private Logger log;
	
	@Inject
	private ArticulosServiceLocal as;
	
	@Inject
	private SolicitudArticulosServiceLocal sas;
	
	@POST
	@Path("/search")
	@Produces("application/json")
	@Consumes("application/json")
	public List<ArticuloDTO> search(SearchArticulosDTO searchArticulosDTO) {		
		return as.buscarArticulos(searchArticulosDTO);
	}
	
	/**
	 * Create test data methods
	 *
	 */
	
	@POST
	@Path("/test/crearArticulos")
	@Produces("application/json")
	public String createArticulosTest() {
		log.info("create solicitud articulos test (defaults)");
		as.createArticulosDefault();
		return "{data: 'ok'}";
	}	

	@POST
	@Path("/test/crearSolicitudArticulo")
	@Produces("application/json")
	@Consumes("application/json")
	public String createSolicitudArticuloTest(SolicitudArticuloRequestDTO request) {
		log.info("crear solicitud de articulo test: " + request);
		try {
			SolicitudArticuloDTO sad = sas.createSolicitudArticulo(new ArticuloDTO(String.valueOf(request.getCodArticulo())), request.getCantidad(), request.getIdDespacho());
			log.info("creada solicitud de articulo con id " + sad.getIdSolicitudArticulo());
			return "{data: 'ok', idSolicitudArticulo:" + sad.getIdSolicitudArticulo() + ", requestEntregaArticulo: {"
					+ "idSolicitudArticulo: " + sad.getIdSolicitudArticulo() + ", cantidad: ??}}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{error: " + e.getMessage() + "}";
		}
	}
	
	@POST
	@Path("/test/entregaArticulo")
	@Produces("application/json")
	@Consumes("application/json")
	public String createEntregaArticuloTest(EntregaArticuloRequestDTO request) {
		SolicitudArticuloDTO sad = new SolicitudArticuloDTO();
		sad.setIdSolicitudArticulo(request.getIdSolicitudArticulo());
		try {
			EntregaArticuloDTO ead = sas.createEntregaArticulo(sad, request.getCantidad());
			return "{entregaArticulo: '" + ead.getIdEntregaArticulo() + ", generada ok'}";
		} catch (Exception e) {
			log.log(Level.WARNING, "Error en test entrega articulo", e);
			e.printStackTrace();
			return "{error: '" + e.getMessage() + ". Ver log.'}";
		}
	}
}
