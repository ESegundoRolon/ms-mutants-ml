package org.github.erolon.service;

import org.github.erolon.model.DNASequence;

public interface IMutantDetectorService {

	/*
	 * Servicio para validar que la secuencia valida corresponda a un DNA mutante
	 */
	public DNASequence verifyMutation(DNASequence dnaSequence);
	
}
