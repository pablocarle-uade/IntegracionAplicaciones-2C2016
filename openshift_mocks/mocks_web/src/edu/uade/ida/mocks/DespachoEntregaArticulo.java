package edu.uade.ida.mocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.uade.ida.mocks.dto.EntregaArticuloDTO;

@RequestScoped
@Path("/despacho")
@Produces("application/json")
@Consumes("application/json")
public class DespachoEntregaArticulo {

	@POST
	@Path("/entregaArticulos")
	public Response entregaArticulos(EntregaArticuloDTO dto) {
		Logger.getAnonymousLogger().log(Level.INFO, "Recibida entrega de articulos: " + dto);
		return Response.ok().build();
	}
}
