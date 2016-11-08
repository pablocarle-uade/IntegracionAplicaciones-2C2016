package edu.uade.ida.deposito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SolicitudCompraItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSoicitudCompraItem;
	@ManyToOne
	private SolicitudCompra solicitudCompra;
	@ManyToOne
	private Articulo articulo;
	@Column
	private int cantidadSolicitada;
	
	public SolicitudCompraItem() {
		super();
	}
	
	public SolicitudCompraItem(SolicitudCompra solicitudCompra, Articulo articulo,
			int cantidadSolicitada) {
		super();
		this.solicitudCompra = solicitudCompra;
		this.articulo = articulo;
		this.cantidadSolicitada = cantidadSolicitada;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(int cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}
}
