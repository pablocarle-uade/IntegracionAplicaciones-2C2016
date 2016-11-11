package edu.uade.ida.deposito.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

/**
 * Servlet implementation class EntregaArticulosWizard
 */
public class EntregaArticulosWizard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitudArticulosServiceLocal sas;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntregaArticulosWizard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener solicitudes pendientes de entrega comprendidas en el param idsSolicitudesOrigen
		List<SolicitudArticuloDTO> solicitudesPendientesDeEntrega = getSolicitudesPendientesDeEntrega(request, response);
		// Cargar la UI que permita editar la entrega a generar y procesarla
		HttpSession session = request.getSession(true);
		session.setAttribute("solicitudesPendientes", solicitudesPendientesDeEntrega);
		request.setAttribute("solicitudesPendientes", solicitudesPendientesDeEntrega);
		request.getRequestDispatcher("/jsp/entregaArticulosWizard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private List<SolicitudArticuloDTO> getSolicitudesPendientesDeEntrega(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SolicitudArticuloDTO> solicitudesPendientesDeEntrega = new LinkedList<SolicitudArticuloDTO>();
		String[] idsSolicitudesOrigenValues = request.getParameter("idsSolicitudesOrigen").split(",");
		List<Integer> idsSolicitudesOrigen = this.getIdsSolicitudesOrigenDeEntrega(idsSolicitudesOrigenValues);
		if (!idsSolicitudesOrigen.isEmpty()) {
			solicitudesPendientesDeEntrega = sas.getSolicitudesStockPendientesEnConjunto(idsSolicitudesOrigen);
			Collections.sort(solicitudesPendientesDeEntrega, new SolicitudArticuloComparator());
		}
		return solicitudesPendientesDeEntrega;
	}
	
	private List<Integer> getIdsSolicitudesOrigenDeEntrega(String[] idsSolicitudesOrigenValues) {
		List<Integer> idsSolicitudesOrigen = new LinkedList<Integer>();
		for (String idSolicitudOrigenValue : idsSolicitudesOrigenValues) {
			idsSolicitudesOrigen.add(Integer.valueOf(idSolicitudOrigenValue));
		}
		return idsSolicitudesOrigen;
	}
	
	private static class SolicitudArticuloComparator implements Comparator<SolicitudArticuloDTO> {

		@Override
		public int compare(SolicitudArticuloDTO o1, SolicitudArticuloDTO o2) {
			return -1 * o1.getFechaCreacion().compareTo(o2.getFechaCreacion());
		}
	}
}
