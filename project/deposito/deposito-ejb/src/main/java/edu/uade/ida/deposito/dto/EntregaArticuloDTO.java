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

	private int idArticulo;
	private int cantidadAsignada;
	private int codArticulo;
	private int idSolicitudArticulo;
	
	public EntregaArticuloDTO() {
		super();
	}

	public EntregaArticuloDTO(int idArticulo, int cantidadAsignada, int codArticulo, int idSolicitudArticulo) {
		super();
		this.idArticulo = idArticulo;
		this.cantidadAsignada = cantidadAsignada;
		this.codArticulo = codArticulo;
		this.idSolicitudArticulo = idSolicitudArticulo;
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
}
