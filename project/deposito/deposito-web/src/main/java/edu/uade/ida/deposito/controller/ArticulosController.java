package edu.uade.ida.deposito.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.CreateArticuloRequestDTO;
import edu.uade.ida.deposito.dto.ModificacionStockRequestDTO;
import edu.uade.ida.deposito.dto.SearchArticulosDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloRequestDTO;
import edu.uade.ida.deposito.rest.GenericResponseDTO;
import edu.uade.ida.deposito.service.ArticulosServiceLocal;
import edu.uade.ida.deposito.service.LoggerLocal;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

@Path("/")
public class ArticulosController {
	
	@Inject
	private LoggerLocal log;
	
	@Inject
	private ArticulosServiceLocal as;
	
	@Inject
	private SolicitudArticulosServiceLocal sas;
	
	@POST
	@Path("/articulo")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericResponseDTO crearArticulo(CreateArticuloRequestDTO createArticuloRequestDTO) {
		log.info(this, "Se ha solicitado la creación de un nuevo artículo ");
		as.crearArticulo(createArticuloRequestDTO);
		return new GenericResponseDTO();
	}
	
	@PUT
	@Path("/articulos/stock")
	@Produces("application/json")
	@Consumes("application/json")
	public GenericResponseDTO modificarStockArticulos(List<ModificacionStockRequestDTO> modificaciones) {		
		log.info(this, "Se ha solicitado la modificación de stocks de artículos ");
		as.modificarStockDeArticulos(modificaciones);
		return new GenericResponseDTO();
	}
	
	@POST
	@Path("/articulo/search")
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
	@Path("/articulos/test/crearArticulos")
	@Produces("application/json")
	public String createArticulosTest() {
		log.info(this, "create solicitud articulos test (defaults)");
		as.createArticulosDefault();
		return "{data: 'ok'}";
	}	

	@POST
	@Path("/articulos/test/crearSolicitudArticulo")
	@Produces("application/json")
	@Consumes("application/json")
	public String createSolicitudArticuloTest(SolicitudArticuloRequestDTO request) {
		log.info(this, "crear solicitud de articulo test: " + request);
		try {
			SolicitudArticuloDTO sad = sas.createSolicitudArticulo(new ArticuloDTO(String.valueOf(request.getCodArticulo())), request.getCantidad(), request.getIdDespacho());
			log.info(this, "creada solicitud de articulo con id " + sad.getIdSolicitudArticulo());
			return "{data: 'ok', idSolicitudArticulo:" + sad.getIdSolicitudArticulo() + ", requestEntregaArticulo: {"
					+ "idSolicitudArticulo: " + sad.getIdSolicitudArticulo() + ", cantidad: ??}}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{error: " + e.getMessage() + "}";
		}
	}
	
}
