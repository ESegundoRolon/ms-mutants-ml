package org.github.erolon.exceptions;

public class NonMutantException extends RuntimeException{
	
	public NonMutantException() {
		this("Las secuencias de ADN ingresadas no corresponden a un mutante");
	}
	public NonMutantException(String message){
		super(message);
	}

}
