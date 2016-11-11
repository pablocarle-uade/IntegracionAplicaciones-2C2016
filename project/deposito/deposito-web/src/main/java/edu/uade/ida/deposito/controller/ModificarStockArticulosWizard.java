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

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.service.ArticulosServiceLocal;

/**
 * Servlet implementation class EntregaArticulosWizard
 */
public class ModificarStockArticulosWizard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ArticulosServiceLocal as;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarStockArticulosWizard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtener solicitudes pendientes de entrega comprendidas en el param idsSolicitudesOrigen
		List<ArticuloDTO> articulosModificacionStock = getArticulosModificacionStock(request, response);
		// Cargar la UI que permita editar la entrega a generar y procesarla
		HttpSession session = request.getSession(true);
		session.setAttribute("articulosModificacionStock", articulosModificacionStock);
		request.setAttribute("articulosModificacionStock", articulosModificacionStock);
		request.getRequestDispatcher("/jsp/modificarStockArticulosWizard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private List<ArticuloDTO> getArticulosModificacionStock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticuloDTO> articulosModificacionStock = new LinkedList<ArticuloDTO>();
		String[] idsArticulosModificacionStockValues = request.getParameter("idsarticulosModificacionStock").split(",");
		List<Long> idsArticulosModificacionStock = this.getIdsArticulosModificacionStock(idsArticulosModificacionStockValues);
		if (!idsArticulosModificacionStock.isEmpty()) {
			articulosModificacionStock = as.getArticulosPorIds(idsArticulosModificacionStock);
		}
		return articulosModificacionStock;
	}
	
	private List<Long> getIdsArticulosModificacionStock(String[] idsArticulosModificacionStockValues) {
		List<Long> idsArticulosModificacionStock = new LinkedList<Long>();
		for (String idArticulo : idsArticulosModificacionStockValues) {
			idsArticulosModificacionStock.add(Long.valueOf(idArticulo));
		}
		return idsArticulosModificacionStock;
	}
	
}
