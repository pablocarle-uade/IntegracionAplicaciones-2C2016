package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class SolicitudArticuloRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idDespacho;
	private String codArticulo;
	private int cantidad;
	
	public SolicitudArticuloRequestDTO() {
		super();
	}

	public SolicitudArticuloRequestDTO(String idDespacho, String codArticulo, int cantidad) {
		super();
		this.idDespacho = idDespacho;
		this.codArticulo = codArticulo;
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}

	public String getIdDespacho() {
		return idDespacho;
	}

	public void setIdDespacho(String idDespacho) {
		this.idDespacho = idDespacho;
	}
}
