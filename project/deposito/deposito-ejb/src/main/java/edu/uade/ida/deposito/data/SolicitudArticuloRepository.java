package edu.uade.ida.deposito.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SolicitudArticuloRepository {

	@Inject
	private EntityManager em;
	
	public SolicitudArticuloRepository() {
		super();
	}

}
