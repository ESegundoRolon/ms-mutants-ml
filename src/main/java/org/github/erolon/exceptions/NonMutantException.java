package org.github.erolon.exceptions;

public class NonMutantException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -324135011054584032L;
	
	public NonMutantException() {
		this("Las secuencias de ADN ingresadas no corresponden a un mutante");
	}
	public NonMutantException(String message){
		super(message);
	}

}
