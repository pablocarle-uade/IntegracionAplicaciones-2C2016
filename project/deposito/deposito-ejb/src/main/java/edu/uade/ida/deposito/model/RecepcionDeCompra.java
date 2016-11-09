package edu.uade.ida.deposito.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RecepcionDeCompra {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRecepcionDeCompra;
	@OneToOne
	private SolicitudCompra solicitudCompra;
	@OneToMany
	private List<RecepcionDeCompraItem> items;
	
	public RecepcionDeCompra() {
		super();
	}

	public int getIdRecepcionDeCompra() {
		return idRecepcionDeCompra;
	}

	public void setIdRecepcionDeCompra(int idRecepcionDeCompra) {
		this.idRecepcionDeCompra = idRecepcionDeCompra;
	}

	public SolicitudCompra getSolicitudCompra() {
		return solicitudCompra;
	}

	public void setSolicitudCompra(SolicitudCompra solicitudCompra) {
		this.solicitudCompra = solicitudCompra;
	}

	public List<RecepcionDeCompraItem> getItems() {
		return items;
	}

	public void setItems(List<RecepcionDeCompraItem> items) {
		this.items = items;
	}
}
