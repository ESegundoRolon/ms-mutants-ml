package org.github.erolon.errors;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.github.erolon.dto.GenericResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ErrorHandler {
	
	private static final String UNKNOWN_ERROR_MESSAGE = "Ha ocurrido un error inesperado";
	private static final String BAD_REQUEST_MESSAGE = "El body recibido es invalido, se espera un array de Strings";
	@Value("${ms.configuration.dateFormat}")
	private String dateFormat;
	
	public ResponseEntity<GenericResponse> handleValidationError(BindingResult bindingResult){
		
		GenericResponse response = new GenericResponse();
		response.setMessage(BAD_REQUEST_MESSAGE);
		response.setField(bindingResult.getFieldError().getDefaultMessage());
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		response.setTimestamp(format.format(new Date()));
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<GenericResponse> handleInvalidDNASequenceError(String message){
		
		GenericResponse response = new GenericResponse();
		response.setMessage(message);
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		response.setTimestamp(format.format(new Date()));
		
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	
	public ResponseEntity<GenericResponse> handleGenericError(){
		
		GenericResponse response = new GenericResponse();
		response.setMessage(UNKNOWN_ERROR_MESSAGE);
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		response.setTimestamp(format.format(new Date()));
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<GenericResponse> handleNonMutantError(String message){
		
		GenericResponse response = new GenericResponse();
		response.setMessage(message);
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		response.setTimestamp(format.format(new Date()));
		
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

	}


}
