package edu.uade.ida.deposito.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Articulo
 * 
 * @author pcarle
 *
 */
@Entity
public class Articulo {
	
	@Id
	private String codigo;
	private String descripcion;
	private String uom;
	private TipoArticulo tipo;
	private int stockCompra;
	private int stockDisponible;
	
	public Articulo() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion + ", uom=" + uom + "]";
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public TipoArticulo getTipo() {
		return tipo;
	}
	public void setTipo(TipoArticulo tipo) {
		this.tipo = tipo;
	}
	public int getStockCompra() {
		return stockCompra;
	}
	public void setStockCompra(int stockCompra) {
		this.stockCompra = stockCompra;
	}
	public int getStockDisponible() {
		return stockDisponible;
	}
	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
}
