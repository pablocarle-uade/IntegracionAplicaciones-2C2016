package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class ProcesarEntregaArticuloRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idSolicitudArticulo;
	private Integer cantidad;

	public ProcesarEntregaArticuloRequestDTO() {
	}

	public Long getIdSolicitudArticulo() {
		return idSolicitudArticulo;
	}
	public void setIdSolicitudArticulo(Long idSolicitudArticulo) {
		this.idSolicitudArticulo = idSolicitudArticulo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	
}
