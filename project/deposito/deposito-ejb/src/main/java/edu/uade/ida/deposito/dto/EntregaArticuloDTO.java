package edu.uade.ida.deposito.dto;

import java.io.Serializable;

/**
 * DTO de EntregaArticulo. Representa stock asignado a enviarse a un despacho
 * La relacion con el despacho se obtiene de la solicitud de articulo
 * 
 * @author pabloc
 *
 */
public class EntregaArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idEntregaArticulo;
	private int idArticulo;
	private int cantidadAsignada;
	private int codArticulo;
	private int idSolicitudArticulo;
	private String idModuloSolicitante;
	
	public EntregaArticuloDTO() {
		super();
	}

	public EntregaArticuloDTO(int idEntregaArticulo, int idArticulo, int cantidadAsignada, int codArticulo, int idSolicitudArticulo, String idModuloSolicitante) {
		super();
		this.idEntregaArticulo = idEntregaArticulo;
		this.idArticulo = idArticulo;
		this.cantidadAsignada = cantidadAsignada;
		this.codArticulo = codArticulo;
		this.idSolicitudArticulo = idSolicitudArticulo;
		this.idModuloSolicitante = idModuloSolicitante;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getCantidadAsignada() {
		return cantidadAsignada;
	}

	public void setCantidadAsignada(int cantidadAsignada) {
		this.cantidadAsignada = cantidadAsignada;
	}

	public int getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}

	public int getIdSolicitudArticulo() {
		return idSolicitudArticulo;
	}

	public void setIdSolicitudArticulo(int idSolicitudArticulo) {
		this.idSolicitudArticulo = idSolicitudArticulo;
	}

	public int getIdEntregaArticulo() {
		return idEntregaArticulo;
	}

	public void setIdEntregaArticulo(int idEntregaArticulo) {
		this.idEntregaArticulo = idEntregaArticulo;
	}

	public String getIdModuloSolicitante() {
		return idModuloSolicitante;
	}

	public void setIdModuloSolicitante(String idModuloSolicitante) {
		this.idModuloSolicitante = idModuloSolicitante;
	}
}
