package com.demo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/fulldemo")
@RequestScoped
public class FullDemo {

    @Inject
    private ArticuloService articuloService;
    
	@GET
	@Path("/demo")
	@Produces({ "text/plain" })
    public String demo() {
		articuloService.getArticulo();
    	return "fulldemo";
    }

}
