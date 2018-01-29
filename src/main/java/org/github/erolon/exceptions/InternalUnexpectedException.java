package org.github.erolon.exceptions;

public class InternalUnexpectedException extends RuntimeException{
	
	public InternalUnexpectedException() {
		this("Ha ocurrido un error inesperado");
	}
	public InternalUnexpectedException(String message){
		super(message);
	}

}
