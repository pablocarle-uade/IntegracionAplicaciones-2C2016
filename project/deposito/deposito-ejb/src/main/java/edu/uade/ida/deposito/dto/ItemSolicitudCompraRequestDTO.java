package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class ItemSolicitudCompraRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idSolicitudArticulo;
	private Integer cantidad;

	public ItemSolicitudCompraRequestDTO() {
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
