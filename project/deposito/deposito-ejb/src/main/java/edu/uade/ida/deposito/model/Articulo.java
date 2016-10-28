package edu.uade.ida.deposito.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import edu.uade.ida.deposito.dto.ArticuloDTO;

@Entity
public class Articulo implements HasDTO<ArticuloDTO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codArticulo;
	private String descripcion;
	private String marca;
	private BigDecimal precio;
	private String urlImagen;
	private String origen;
	private TipoDeArticulo tipo;
    @ElementCollection
    @MapKeyColumn(name="nombre")
    @Column(name="valor")
    @CollectionTable(name="articulo_datosExtra", joinColumns=@JoinColumn(name="datoExtra_id"))
	private Map<String, String> datosExtra = new HashMap<String, String>();

	// TODO uom, stockCompra, stockDisponible

	// Características específicas según tipo artículo (cargar data)
	// MODA => color, talle
	// MUEBLE => material
	// ELECTRODOMESTICO => ficha técnica (modelo, tipo, capacidad, dimensiones, etc)
	// NIÑOS => edadRecomendada

	public Articulo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public TipoDeArticulo getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeArticulo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Articulo [codigo=" + codArticulo + ", descripcion=" + descripcion + "]";
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codArticulo == null) ? 0 : codArticulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		if (codArticulo == null) {
			if (other.codArticulo != null)
				return false;
		} else if (!codArticulo.equals(other.codArticulo))
			return false;
		return true;
	}

	@Override
	public ArticuloDTO getDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getDatosExtra() {
		return datosExtra;
	}

	public void setDatosExtra(Map<String, String> datosExtra) {
		this.datosExtra = datosExtra;
	}
}
