package edu.uade.ida.deposito.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.uade.ida.deposito.model.Articulo;

/**
 * TODO Lo creo ahora para completar el SolicitudArticulosMDB
 * despues reemplazar.
 * 
 * @author pabloc
 *
 */
@ApplicationScoped
public class ArticuloRepository {

	@Inject
	private EntityManager em;
	
	public ArticuloRepository() {
		super();
	}
	
	public Articulo getPorId(String id) {
		return em.find(Articulo.class, Integer.valueOf(id));
	}

	public Articulo getPorCodigo(String codigo) {
		Query q = em.createQuery("from Articulo where codigo = :codigo");
		q.setParameter("codigo", codigo);
		return (Articulo) q.getSingleResult();
	}
}
