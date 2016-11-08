package edu.uade.ida.deposito.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import edu.uade.ida.deposito.dto.SolicitudCompraDTO;
import edu.uade.ida.deposito.util.HasDTO;

@Entity
public class SolicitudCompra implements HasDTO<SolicitudCompraDTO> {
	
	public static final transient String ESTADO_SOLICITADO = "solicitado";
	public static final transient String ESTADO_ENTREGADO = "entregado";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSolicitudCompra;
	@Column
	private String estado = ESTADO_SOLICITADO;
	@OneToMany(cascade=CascadeType.ALL)
	private List<SolicitudCompraItem> items;

	public SolicitudCompra() {
		super();
	}
	
	public int getIdSolicitudCompra() {
		return idSolicitudCompra;
	}
	public void setIdSolicitudCompra(int idSolicitudCompra) {
		this.idSolicitudCompra = idSolicitudCompra;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<SolicitudCompraItem> getItems() {
		return items;
	}
	public void setItems(List<SolicitudCompraItem> items) {
		this.items = items;
	}

	public Articulo getArticulo(int codArticulo) {
		for (SolicitudCompraItem item : items) {
			if (item.getArticulo().getCodArticulo().equals(String.valueOf(codArticulo)))
				return item.getArticulo();
		}
		return null;
	}

	@Override
	public SolicitudCompraDTO getDTO() {
		// TODO Auto-generated method stub
		return null;
	}
}
