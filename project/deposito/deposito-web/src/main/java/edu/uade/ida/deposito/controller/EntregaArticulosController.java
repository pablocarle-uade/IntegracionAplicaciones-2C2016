package edu.uade.ida.deposito.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.uade.ida.deposito.dto.ProcesarEntregaArticuloRequestDTO;

@Path("/entregaDeArticulos")
public class EntregaArticulosController {
	
	@Inject
	private Logger log;
	
	@POST
	@Path("/procesarEntrega")
	@Produces("application/json")
	@Consumes("application/json")
	public ProcesarEntregaArticulosResponseDTO procesarEntregaDeArticulos(List<ProcesarEntregaArticuloRequestDTO> request) {
		log.info("Procesar entrega de artículos ");
		return new ProcesarEntregaArticulosResponseDTO();
	}
	
	class ProcesarEntregaArticulosResponseDTO implements Serializable {
		private static final long serialVersionUID = -4085454396383153752L;
		
		private String status = "OK";

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}

}
