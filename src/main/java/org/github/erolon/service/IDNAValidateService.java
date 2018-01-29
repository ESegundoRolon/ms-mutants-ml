package org.github.erolon.service;

import org.github.erolon.exceptions.InvalidDNASequenceException;
import org.github.erolon.model.DNASequence;

public interface IDNAValidateService {

	/**
	 * Metodo para validar si la secuencia de ADN recibida:
	 * No es Numerica
	 * No contiene bases nitrogenadas invalidas
	 * Forman una tabla de NxN
	 * Caso contrario devuelve InvalidDNASequenceException
	 * @throws InvalidDNASequenceException
	 */
	public void isValidOrThrowException(DNASequence dnaSequence );
	
}
