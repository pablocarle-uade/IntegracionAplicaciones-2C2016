package edu.uade.ida.deposito.model;

public enum TipoDeArticulo {
    Electro("Electro"), Moda("Moda"), Mueble("Mueble"), Niños("Niños");
	
	private String nombre;
	
	private TipoDeArticulo(String nombre) {
		this.nombre = nombre;
	}
	
	public static TipoDeArticulo parse(String nombre) {
		if (nombre == null)
			throw new NullPointerException("nombre no puede ser null");
		if (nombre.equalsIgnoreCase("electro"))
			return TipoDeArticulo.Electro;
		else if (nombre.equalsIgnoreCase("moda"))
			return TipoDeArticulo.Moda;
		else if (nombre.equalsIgnoreCase("mueble"))
			return TipoDeArticulo.Mueble;
		else if (nombre.equalsIgnoreCase("niños"))
			return TipoDeArticulo.Niños;
		else
			throw new RuntimeException("No se reconoce tipo " + nombre);
	}

	@Override
	public String toString() {
		return nombre;
	}
}
