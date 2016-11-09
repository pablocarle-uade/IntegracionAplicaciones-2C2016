package edu.uade.ida.deposito.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import edu.uade.ida.deposito.dto.RecepcionCompraDTO;
import edu.uade.ida.deposito.util.HasDTO;

@Entity
public class RecepcionDeCompra implements HasDTO<RecepcionCompraDTO> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRecepcionDeCompra;
	@OneToOne
	private SolicitudCompra solicitudCompra;
	@OneToMany(cascade=CascadeType.ALL)
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

	@Override
	public RecepcionCompraDTO getDTO() {
		// TODO Auto-generated method stub
		return null;
	}
}
