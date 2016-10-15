package edu.uade.ida.deposito.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;

@Entity
public class SolicitudArticulo implements HasDTO<SolicitudArticuloDTO> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSolicitudStock;
	@OneToOne
	private EstadoSolicitudStock estado;
	@Column
	private Date fechaCreacion = Calendar.getInstance().getTime();
	@ManyToOne
	private Articulo articulo;
	@Column
	private int cantidad;
	
	public SolicitudArticulo() {
		super();
	}
	
	public SolicitudArticulo(EstadoSolicitudStock estado, Articulo articulo, int cantidad) {
		super();
		this.estado = estado;
		this.articulo = articulo;
	}

	public int getIdSolicitudStock() {
		return idSolicitudStock;
	}

	public EstadoSolicitudStock getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitudStock estado) {
		this.estado = estado;
	}

	public void setIdSolicitudStock(int idSolicitudStock) {
		this.idSolicitudStock = idSolicitudStock;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public SolicitudArticuloDTO getDTO() {
		//TODO
		return new SolicitudArticuloDTO();
	}
}
