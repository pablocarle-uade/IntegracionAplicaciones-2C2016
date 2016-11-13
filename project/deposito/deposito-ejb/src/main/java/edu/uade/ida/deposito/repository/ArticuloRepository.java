package edu.uade.ida.deposito.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.TipoDeArticulo;
import edu.uade.ida.deposito.util.StringUtils;

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
	
	public Articulo get(Long id) {
		return em.find(Articulo.class, id);
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
	
	@SuppressWarnings("unchecked")
	public List<Articulo> getPorIds(List<Long> ids) {
		List<Articulo> retList = new ArrayList<>();
		Query q = em.createQuery("from Articulo where id in (:ids)");
		q.setParameter("ids", ids);
		retList.addAll(q.getResultList());
		return retList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Articulo> search(String codArticulo, String nombre, String marca, TipoDeArticulo tipo) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		StringBuilder queryBuilder = new StringBuilder("from Articulo where 1 = 1 ");
		if (!StringUtils.isNullOrEmpty(codArticulo)) {
			queryBuilder.append("and codArticulo = :codArticulo ");
			queryParams.put("codArticulo", codArticulo);
		}
		if (!StringUtils.isNullOrEmpty(nombre)) {
			queryBuilder.append("and nombre like :nombre");
			queryParams.put("nombre", "%" + nombre + "%");
		}
		if (!StringUtils.isNullOrEmpty(marca)) {
			queryBuilder.append("and marca like :marca");
			queryParams.put("marca", "%" + marca + "%");
		}
		if (tipo != null) {
			queryBuilder.append("and tipo = :tipo ");
			queryParams.put("tipo", tipo);
		}
		
		Query q = em.createQuery(queryBuilder.toString());
		for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
			q.setParameter(entry.getKey(), entry.getValue());
		}
		return (List<Articulo>) q.getResultList();
	}
	
	public List<Articulo> findAll() {
		return em.createQuery("from " + Articulo.class.getSimpleName() + " a", Articulo.class).getResultList();
	}	
}
