package edu.uade.ida.deposito.controller;

import java.io.IOException;
import java.util.Arrays;
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

import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosServiceLocal;

/**
 * Servlet implementation class GenerarEntregaArticulos
 */
public class GenerarEntregaArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitudArticulosServiceLocal sas;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerarEntregaArticulos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener los articulos pendientes de entrega y cargar la UI que permita generar la entrega a procesar
		getArticulosPendienteEntrega(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private void getArticulosPendienteEntrega(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener solicitudes para generar la entrega
		String[] idsSolicitudesOrigenValues = request.getParameter("idsSolicitudesOrigen").split(",");
		List<Integer> idsSolicitudesOrigen = this.getIdsSolicitudesOrigenDeEntrega(idsSolicitudesOrigenValues);
		List<SolicitudArticuloDTO> solicitudesPendientesOrigenDeEntrega = new LinkedList<SolicitudArticuloDTO>();
		if (!idsSolicitudesOrigen.isEmpty()) {
			solicitudesPendientesOrigenDeEntrega = sas.getSolicitudesStock(idsSolicitudesOrigen);
			Collections.sort(solicitudesPendientesOrigenDeEntrega, new SolicitudArticuloComparator());
		}
		// Cargar la UI que permita editar la entrega a generar y procesarla
		HttpSession session = request.getSession(true);
		session.setAttribute("solicitudesPendientes", solicitudesPendientesOrigenDeEntrega);
		request.setAttribute("solicitudesPendientes", solicitudesPendientesOrigenDeEntrega);
		request.getRequestDispatcher("/jsp/generarEntregaArticulos.jsp").forward(request, response);
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
