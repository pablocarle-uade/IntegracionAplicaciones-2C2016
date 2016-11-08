package edu.uade.ida.deposito.service.integration;

import java.util.Map;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;

@Local
public interface FabricaServiceLocal {

	public abstract SolicitudCompraDTO crearSolicitudCompra(ArticuloDTO articulo, int cantidadSolicitada) throws Exception;
	
	public abstract SolicitudCompraDTO crearSolicitudCompra(Map<ArticuloDTO, Integer> cantidades) throws Exception;
}
