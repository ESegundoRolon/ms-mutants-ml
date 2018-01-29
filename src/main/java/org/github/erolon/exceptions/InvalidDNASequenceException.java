package org.github.erolon.exceptions;

public class InvalidDNASequenceException  extends RuntimeException{
	

	public InvalidDNASequenceException() {
		this("Las secuencias de ADN enviadas contienen caracteres invalidos o no forman una matrix NxN");
	}
	public InvalidDNASequenceException(String message){
		super(message);
	}

}
