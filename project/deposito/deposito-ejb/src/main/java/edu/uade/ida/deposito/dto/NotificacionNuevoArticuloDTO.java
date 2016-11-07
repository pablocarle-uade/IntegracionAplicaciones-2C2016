package edu.uade.ida.deposito.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class NotificacionNuevoArticuloDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long idDeposito;
	private String codArticulo;
	private String nombre;
	private String descripcion;
	private String marca;
	private BigDecimal precio;
	private String foto;
	private String origen;
	private String tipo;
	private Map<String, String> datosExtra;
	
	public NotificacionNuevoArticuloDTO() {
	}
	
	public NotificacionNuevoArticuloDTO(long idDeposito, String codArticulo, String nombre, String descripcion,
			String marca, BigDecimal precio, String foto, String origen, String tipo, Map<String, String> datosExtra) {
		super();
		this.idDeposito = idDeposito;
		this.codArticulo = codArticulo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.precio = precio;
		this.foto = foto;
		this.origen = origen;
		this.tipo = tipo;
		this.datosExtra = datosExtra;
	}

	public long getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(long idDeposito) {
		this.idDeposito = idDeposito;
	}
	public String getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Map<String, String> getDatosExtra() {
		return datosExtra;
	}
	public void setDatosExtra(Map<String, String> datosExtra) {
		this.datosExtra = datosExtra;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
