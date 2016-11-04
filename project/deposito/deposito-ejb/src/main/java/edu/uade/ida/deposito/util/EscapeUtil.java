package edu.uade.ida.deposito.util;

public final class EscapeUtil {
	
	private EscapeUtil() {
		super();
	}
	
	public static String escapeJsonStringValue(String value) {
		return value.replaceAll("\"", "'"); //FIXME
	}
	
}
