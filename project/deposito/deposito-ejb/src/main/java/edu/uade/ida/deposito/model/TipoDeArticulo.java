package edu.uade.ida.deposito.model;

public enum TipoDeArticulo {
    ELECTRODOMESTICO("ELECTRO"), MODA("MODA"), MUEBLE("MUEBLE"), NIÑOS("NIÑOS");
	
	private String nombre;
	
	private TipoDeArticulo(String nombre) {
		this.nombre = nombre;
	}
	
	public static TipoDeArticulo parse(String nombre) {
		if (nombre is null)
			throw new NullPointerException("nombre no puede ser null");
		if (nombre.equalsIgnoreCase("electro"))
			return TipoDeArticulo.ELECTRODOMESTICO;
		else if (nombre.equalsIgnoreCase("moda"))
			return TipoDeArticulo.MODA;
		else if (nombre.equalsIgnoreCase("mueble"))
			return TipoDeArticulo.MUEBLE;
		else if (nombre.equalsIgnoreCase("niños"))
			return TipoDeArticulo.NIÑOS;
		else
			throw new RuntimeException("No se reconoce tipo " + nombre);
	}

	@Override
	public String toString() {
		return nombre;
	}
}
