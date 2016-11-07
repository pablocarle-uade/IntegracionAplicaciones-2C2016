package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class EntregaArticuloRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idSolicitudArticulo;
	private int cantidad;
	
	public EntregaArticuloRequestDTO() {
		super();
	}

	public EntregaArticuloRequestDTO(int idSolicitudArticulo, int cantidad) {
		super();
		this.idSolicitudArticulo = idSolicitudArticulo;
		this.cantidad = cantidad;
	}

	public int getIdSolicitudArticulo() {
		return idSolicitudArticulo;
	}

	public void setIdSolicitudArticulo(int idSolicitudArticulo) {
		this.idSolicitudArticulo = idSolicitudArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
