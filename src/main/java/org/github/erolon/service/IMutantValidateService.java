package org.github.erolon.service;

import org.github.erolon.model.DNASequence;

public interface IMutantValidateService {

	/*
	 * Servicio para validar que la secuencia valida corresponda a un DNA mutante
	 */
	public boolean verifyMutation(DNASequence dnaSequence);
	
}
