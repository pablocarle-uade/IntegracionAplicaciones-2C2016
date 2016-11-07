package edu.uade.ida.deposito.controller;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import edu.uade.ida.deposito.util.config.ConfigHolder;


@RequestScoped
@Path("/admin")
@Produces("application/json")
@Consumes("application/json")
public class AdminController {

//	@Inject TODO
	private ConfigHolder config;
	
	@GET
	@Path("/reloadConfig")
	public void reloadConfig() {
		config.reloadConfig();
	}
}
