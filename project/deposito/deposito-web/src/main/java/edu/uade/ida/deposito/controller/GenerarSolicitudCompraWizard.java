package edu.uade.ida.deposito.controller;

import java.io.IOException;
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
 * Servlet implementation class GenerarSolicitudDeCompraWizard
 */
public class GenerarSolicitudCompraWizard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitudArticulosServiceLocal sas;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerarSolicitudCompraWizard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener solicitudes pendientes de entrega comprendidas en el param idsSolicitudesOrigen
		List<SolicitudArticuloDTO> solicitudesPendientesDeEntrega = new LinkedList<SolicitudArticuloDTO>();
		// Cargar la UI que permita editar la entrega a generar y procesarla
		HttpSession session = request.getSession(true);
		session.setAttribute("solicitudesPendientes", solicitudesPendientesDeEntrega);
		request.setAttribute("solicitudesPendientes", solicitudesPendientesDeEntrega);
		request.getRequestDispatcher("/jsp/generarSolicitudCompra.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}