package org.github.erolon.exceptions;

public class InternalUnexpectedException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1365581618729065986L;
	public InternalUnexpectedException() {
		this("Ha ocurrido un error inesperado");
	}
	public InternalUnexpectedException(String message){
		super(message);
	}

}
