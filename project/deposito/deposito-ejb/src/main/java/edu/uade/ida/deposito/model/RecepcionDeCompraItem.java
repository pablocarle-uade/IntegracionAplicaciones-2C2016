package edu.uade.ida.deposito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RecepcionDeCompraItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRecepcionDeCompraItem;
	@ManyToOne
	private Articulo articulo;
	@Column
	private int cantidadEntregada;
	
	public RecepcionDeCompraItem() {
		super();
	}
	
	public int getIdRecepcionDeCompraItem() {
		return idRecepcionDeCompraItem;
	}
	public void setIdRecepcionDeCompraItem(int idRecepcionDeCompraItem) {
		this.idRecepcionDeCompraItem = idRecepcionDeCompraItem;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public int getCantidadEntregada() {
		return cantidadEntregada;
	}
	public void setCantidadEntregada(int cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}
}
