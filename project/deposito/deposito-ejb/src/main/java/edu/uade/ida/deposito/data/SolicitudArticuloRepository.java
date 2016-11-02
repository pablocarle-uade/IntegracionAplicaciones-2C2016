package edu.uade.ida.deposito.data;

import java.util.ArrayList;
import java.util.Arrays;
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
		q.setParameter("estados", Arrays.asList(estados));
		retList.addAll(q.getResultList());
		return retList;
	}
	
	/**
	 * Obtener solicitudes de articulos pendientes por modulo y articulo
	 * 
	 * @param idModulo El id del modulo
	 * @param codArticulo El codigo de articulo
	 * @return Lista de solicitudes de articulos o lista vacia
	 */
	@SuppressWarnings("unchecked")
	public List<SolicitudArticulo> getPendientesModuloArticulo(String idModulo, String codArticulo) {
		List<SolicitudArticulo> retList = new ArrayList<>();
		Query q = em.createQuery("from SolicitudArticulo where estado = '" + SolicitudArticulo.ESTADO_PENDIENTE + "' and idModuloSolicitante = :idModulo and articulo.codArticulo = :codArticulo");
		q.setParameter("idModulo", idModulo);
		q.setParameter("codArticulo", codArticulo);
		retList.addAll(q.getResultList());
		return retList;
	}
}
