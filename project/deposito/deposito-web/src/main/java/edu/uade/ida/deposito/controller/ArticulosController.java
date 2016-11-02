package edu.uade.ida.deposito.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.data.SolicitudArticuloRequest;
import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
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
	
	class Demo {
		String one = "First name";
		String two = "Last name";
		String three = "Position";
	    String four = "Office";
	    String five = "start_date";
	    private String six = "400000";
	    
		public String getOne() {
			return one;
		}
		public void setOne(String one) {
			this.one = one;
		}
		public String getTwo() {
			return two;
		}
		public void setTwo(String two) {
			this.two = two;
		}
		public String getThree() {
			return three;
		}
		public void setThree(String three) {
			this.three = three;
		}
		public String getFour() {
			return four;
		}
		public void setFour(String four) {
			this.four = four;
		}
		public String getFive() {
			return five;
		}
		public void setFive(String five) {
			this.five = five;
		}
		public String getSix() {
			return six;
		}
		public void setSix(String six) {
			this.six = six;
		}
	}
	
	@GET
	@Path("/jsonDemo")
	@Produces("application/json")
	public List<Demo> jsonDemo() {
		return Arrays.asList(new Demo(), new Demo(), new Demo(), new Demo());
	}
	
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
	public String createSolicitudArticuloTest(SolicitudArticuloRequest request) {
		log.info("crear solicitud de articulo test: " + request);
		try {
			SolicitudArticuloDTO sad = sas.createSolicitudArticulo(new ArticuloDTO(String.valueOf(request.getCodArticulo())), request.getCantidad());
			log.info("creada solicitud de articulo con id " + sad.getIdSolicitudArticulo());
			return "{data: 'ok'}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{error: " + e.getMessage() + "}";
		}
	}
}
