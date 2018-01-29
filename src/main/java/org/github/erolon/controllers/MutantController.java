package org.github.erolon.controllers;

import javax.validation.Valid;

import org.github.erolon.dto.DNASequenceRequest;
import org.github.erolon.errors.ErrorHandler;
import org.github.erolon.exceptions.InvalidDNASequenceException;
import org.github.erolon.exceptions.NonMutantException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.service.IDNAValidateService;
import org.github.erolon.service.IMutantDetectorService;
import org.github.erolon.service.IStatsService;
import org.github.erolon.service.Impl.MutantDetectorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MutantController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MutantController.class);
	
	@Autowired
	IDNAValidateService mutantValidationService;
	
	@Autowired
	IMutantDetectorService mutantService;
	
	@Autowired
	IStatsService statsService;
	
	@Autowired
	ErrorHandler errorHandler;

	  /**
	   *Endpoint que recibe secuencia de ADN y devuelve si es mutante o no
	   *
	   */
	@RequestMapping(value = "mutant", method = RequestMethod.POST)
	public Object mutantDetector(@RequestBody @Valid DNASequenceRequest request, BindingResult result) throws BindException {
		if(result.hasErrors())
			return errorHandler.handleValidationError(result);
		LOGGER.info("Comienza servicio de verificacion de mutantes");
		DNASequence dnaSequence = new DNASequence(request);
		
		try{
			mutantValidationService.isValidOrThrowException(dnaSequence);
			return new ResponseEntity<>( mutantService.verifyMutation(dnaSequence) , HttpStatus.OK );
		}catch(InvalidDNASequenceException idsex){
			return errorHandler.handleInvalidDNASequenceError(idsex.getMessage());
		}catch(NonMutantException nmex){
			return errorHandler.handleNonMutantError(nmex.getMessage());
		}catch(Exception ex){
			return errorHandler.handleGenericError();
		}		
	}
	
	  /**
	   *Endpoint obtener estadisticas de las secuencias de ADN guardadas
	   *
	   */
	@RequestMapping(value = "stats", method = RequestMethod.GET)
	public Object stats() {
		try {
			return new ResponseEntity<>( statsService.getStats() , HttpStatus.OK );
		}catch(Exception ex){
			return errorHandler.handleGenericError();
		}
	}
	
	  /**
	   *Endpoint para healtcheck de Google Cloud para App Engine
	   *
	   */
	  @RequestMapping("/_ah/health")
	  public String healthy() {
	    // Message body required though ignored
	    return " OK ";
	  }
}
