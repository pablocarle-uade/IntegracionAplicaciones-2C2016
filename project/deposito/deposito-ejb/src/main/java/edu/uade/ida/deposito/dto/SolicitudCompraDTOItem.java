package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class SolicitudCompraDTOItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codArticulo;
	private int cantidadSolicitada;
	
	public SolicitudCompraDTOItem(int codArticulo, int cantidadSolicitada) {
		super();
		this.codArticulo = codArticulo;
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public int getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
}
