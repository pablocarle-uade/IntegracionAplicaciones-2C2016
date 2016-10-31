package edu.uade.ida.deposito.controller;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.service.ArticulosServiceLocal;

@Path("/articulos")
public class ArticulosController {
	
	@Inject
	private ArticulosServiceLocal as;
	
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
	@Path("/createAll")
	@Produces("application/json")
	public String createAll() {
		
		//TODO Crear los articulos por default (los de la planilla)
		return "";
	}

}
