package edu.uade.ida.mocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import edu.uade.ida.mocks.dto.EventoAuditDTO;

@RequestScoped
@Path("/logMonAudit")
@Produces("application/json")
@Consumes("application/json")
public class LogisticaMonitoreoAudit {
	
	@POST
	@Path("/recibir")
	public Response recibirEventoAudit(EventoAuditDTO evento) {
		Logger.getAnonymousLogger().log(Level.INFO, "Recibido: " + evento);
		return Response.ok().build();
	}

}
