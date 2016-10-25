package edu.uade.ida.deposito.data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.uade.ida.deposito.model.SolicitudArticulo;

@ApplicationScoped
public class SolicitudArticuloRepository {

	@Inject
	private EntityManager em;
	
	public SolicitudArticuloRepository() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<SolicitudArticulo> getPorEstado(String... estados) {
		List<SolicitudArticulo> retList = new ArrayList<>();
		Query q = em.createQuery("from SolicitudArticulo where estado in (:estados)");
		q.setParameter("estados", estados);
		retList.addAll(q.getResultList());
		return retList;
	}
}
