package edu.uade.ida.deposito.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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
public class GenerarEntregaArticulos {

	@Inject
	private SolicitudArticulosServiceLocal bean;
	
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
		// Obtener los articulos pendientes de entrega
		getArticulosPendienteEntrega(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Procesar entregas seleccionadas
		procesarEntregas(request, response);
	}
	
	private void procesarEntregas(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * TODO
		 * Obtener lista de solicitudes que se procesan
		 * */
		
	}
	
	private void getArticulosPendienteEntrega(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SolicitudArticuloDTO> solicitudesPendientes = bean.getSolicitudesStockPendientes();
		Collections.sort(solicitudesPendientes, new SolicitudArticuloComparator());
		HttpSession session = request.getSession(true);
		session.setAttribute("solicitudesPendientes", solicitudesPendientes);
		request.setAttribute("solicitudesPendientes", solicitudesPendientes);
		request.getRequestDispatcher("/jsp/entregaArticulos.jsp").forward(request, response);
	}
	
	private static class SolicitudArticuloComparator implements Comparator<SolicitudArticuloDTO> {

		@Override
		public int compare(SolicitudArticuloDTO o1, SolicitudArticuloDTO o2) {
			return -1 * o1.getFechaCreacion().compareTo(o2.getFechaCreacion());
		}
	}
}
