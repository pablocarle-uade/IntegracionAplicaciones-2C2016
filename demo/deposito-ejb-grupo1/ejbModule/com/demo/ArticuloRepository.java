package com.demo;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.edu.uade.ia.grupo1.deposito.model.Articulo;

@RequestScoped
public class ArticuloRepository {

	@PersistenceContext(unitName="MyPU") 
    private EntityManager em;

    public Articulo findById(Long id) {
    	return new Articulo("descripcion");
        // return em.find(Articulo.class, id);
    }

}
