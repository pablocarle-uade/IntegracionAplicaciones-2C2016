package edu.uade.ida.deposito.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.service.SolicitudArticulosMgrBeanLocal;

/**
 * Servlet implementation class EntregaArticulos
 */
public class EntregaArticulos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitudArticulosMgrBeanLocal bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EntregaArticulos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener los articulos pendientes de entrega
		getArticulosPendienteEntrega(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Procesar entregas seleccionadas
		
	}

	private void getArticulosPendienteEntrega(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SolicitudArticuloDTO> solicitudesPendientes = bean.getSolicitudesStockPendientes();
		request.setAttribute("solicitudesPendientes", solicitudesPendientes);
		request.getRequestDispatcher("/jsp/entregaArticulos.jsp").forward(request, response);
	}
}
