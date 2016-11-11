package edu.uade.ida.deposito.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class CreateArticuloRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codArticulo;
	private String nombre;
	private String descripcion;
	private String marca;
	private BigDecimal precio;
	private String foto;
	private String origen;
	private String tipo;
	private Integer stock;
	private Map<String, String> datosExtra;
	
	public CreateArticuloRequestDTO() {
	}

	public CreateArticuloRequestDTO(String codArticulo, String nombre, String descripcion, String marca, BigDecimal precio,
			String foto, String origen, String tipo, Integer stock, Map<String, String> datosExtra) {
		this.codArticulo = codArticulo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.precio = precio;
		this.foto = foto;
		this.origen = origen;
		this.tipo = tipo;
		this.setStock(stock);
		this.datosExtra = datosExtra;
	}

	public CreateArticuloRequestDTO(String codArticulo) {
		this.codArticulo = codArticulo;
	}

	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
