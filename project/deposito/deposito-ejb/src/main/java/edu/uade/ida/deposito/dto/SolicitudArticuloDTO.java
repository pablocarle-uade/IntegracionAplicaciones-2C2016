package edu.uade.ida.deposito.dto;

import java.io.Serializable;
import java.util.Date;

public class SolicitudArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idSolicitudArticulo;
	private ArticuloDTO articulo;
	private String estado;
	private Date fechaCreacion;
	private int cantidad;
	
	public SolicitudArticuloDTO() {
		super();
	}

	public SolicitudArticuloDTO(int idSolicitudArticulo, ArticuloDTO articulo, String estado, Date fechaCreacion,
			int cantidad) {
		super();
		this.idSolicitudArticulo = idSolicitudArticulo;
		this.articulo = articulo;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return "SolicitudArticuloDTO [idSolicitudArticulo=" + idSolicitudArticulo + ", articulo=" + articulo
				+ ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", cantidad=" + cantidad + "]";
	}

	public int getIdSolicitudArticulo() {
		return idSolicitudArticulo;
	}

	public void setIdSolicitudArticulo(int idSolicitudArticulo) {
		this.idSolicitudArticulo = idSolicitudArticulo;
	}

	public ArticuloDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
