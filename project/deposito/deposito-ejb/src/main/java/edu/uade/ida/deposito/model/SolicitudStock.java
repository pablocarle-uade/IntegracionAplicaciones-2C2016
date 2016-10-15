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

import edu.uade.ida.deposito.dto.SolicitudStockDTO;

@Entity
public class SolicitudStock implements HasDTO<SolicitudStockDTO> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSolicitudStock;
	@OneToOne
	private EstadoSolicitudStock estado;
	@Column
	private Date fechaCreacion = Calendar.getInstance().getTime();
	@ManyToOne
	private Articulo articulo;
	
	public SolicitudStock() {
		super();
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

	@Override
	public SolicitudStockDTO getDTO() {
		//TODO
		return new SolicitudStockDTO();
	}
}
