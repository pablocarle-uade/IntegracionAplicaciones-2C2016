package com.demo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class ArticuloService {

	@Inject
	private ArticuloRepository articuloRepository;     
	
    public ArticuloService() { }
    
    public String sayHello(String name) {
    	return "Hello " + name;
    }
    
    public void getArticulo() {
    	try {
    		System.out.println("ENCONTRADO ===> " + articuloRepository.findById(1L).descripcion);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		System.out.println("Conectando a: " + ex.getMessage());
    	}
    }

}
