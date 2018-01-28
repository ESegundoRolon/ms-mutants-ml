package org.github.erolon.service;

import org.github.erolon.model.DNASequence;

public interface IDNAValidateService {

	/*
	 * Servicio para validar que la secuencia DNA corresponda a matriz NxN
	 * con bases nitrogenadas validas
	 */
	public void isValidOrThrowException(DNASequence dnaSequence );
	
}
