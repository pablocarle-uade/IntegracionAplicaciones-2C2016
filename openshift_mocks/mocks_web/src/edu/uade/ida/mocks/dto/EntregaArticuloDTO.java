package edu.uade.ida.mocks.dto;

import java.io.Serializable;

public class EntregaArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idDeposito;
	private int codArticulo;
	private int cantidad;

	public EntregaArticuloDTO() {
		super();
	}
	
	public EntregaArticuloDTO(String idDeposito, int codArticulo, int cantidad) {
		super();
		this.idDeposito = idDeposito;
		this.codArticulo = codArticulo;
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "EntregaArticuloDTO [idDeposito=" + idDeposito + ", codArticulo=" + codArticulo + ", cantidad="
				+ cantidad + "]";
	}
	public String getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(String idDeposito) {
		this.idDeposito = idDeposito;
	}
	public int getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
