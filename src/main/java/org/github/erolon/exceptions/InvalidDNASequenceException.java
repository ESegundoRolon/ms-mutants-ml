package org.github.erolon.exceptions;

public class InvalidDNASequenceException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidDNASequenceException() {
		this("Las secuencias de ADN ingresadas son invalidas");
	}
	public InvalidDNASequenceException(String message){
		super(message);
	}

}
