package org.github.erolon.exceptions;

public class InvalidDNASequenceException  extends RuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2327255712682644774L;
	
	public InvalidDNASequenceException() {
		this("Las secuencias de ADN enviadas contienen caracteres invalidos o no forman una matrix NxN");
	}
	public InvalidDNASequenceException(String message){
		super(message);
	}

}
