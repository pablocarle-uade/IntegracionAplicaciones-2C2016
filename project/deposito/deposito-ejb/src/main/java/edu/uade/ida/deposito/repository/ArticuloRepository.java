package edu.uade.ida.deposito.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.uade.ida.deposito.model.Articulo;

/**
 *	Algunas queries utiles de Articulos
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

	/**
	 * Buscar articulo por codArticulo
	 * 
	 * @param codigo codArticulo a buscar
	 * @return
	 */
	public Articulo getPorCodigo(String codigo) {
		Query q = em.createQuery("from Articulo where codArticulo = :codigo");
		q.setParameter("codigo", codigo);
		return (Articulo) q.getSingleResult();
	}
	
	public List<Articulo> findAll() {
		return em.createQuery("from " + Articulo.class.getSimpleName() + " a", Articulo.class).getResultList();
	}	
}
