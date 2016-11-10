package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class ProcesarEntregaArticuloRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idSolicitudArticulo;
	private Integer cantidad;

	public ProcesarEntregaArticuloRequestDTO() {
	}

	public Integer getIdSolicitudArticulo() {
		return idSolicitudArticulo;
	}
	public void setIdSolicitudArticulo(Integer idSolicitudArticulo) {
		this.idSolicitudArticulo = idSolicitudArticulo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
}
