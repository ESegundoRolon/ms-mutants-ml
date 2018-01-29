package org.github.erolon.service;

import org.github.erolon.model.DNASequence;


public interface IMutantDetectorService {

	/**
	 * Servicio que verifica y setea propiedad mutante en objeto recibido
	 * Persiste el objeto y si no es mutante devuelve excepcion
	 * @param dnaSequence
	 * @throws NonMutanException
	 * @return
	 */
	public DNASequence verifyMutation(DNASequence dnaSequence);
	
}
