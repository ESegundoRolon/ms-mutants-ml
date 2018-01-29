package org.github.erolon.service.Impl;

import org.github.erolon.exceptions.NonMutantException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.repository.IDNASequenceRepository;
import org.github.erolon.service.IMutantDetectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MutantDetectorServiceImpl  implements IMutantDetectorService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MutantDetectorServiceImpl.class);
	
	@Value("${mutant.match.factor}")
	private String numberOfNitrogenBasesRepetition;
	
	@Value("${mutant.secuences.occurences}")
	private String numberOfSequencesOccurence;
	
	@Autowired
	private IDNASequenceRepository dnaSequenceRepository;
	

	@Override
	public DNASequence verifyMutation(DNASequence dnaSequence) {
		dnaSequence.setIsMutant(isMutant(dnaSequence));
		dnaSequenceRepository.save(dnaSequence);
		
		if(!dnaSequence.getIsMutant())
			throw new NonMutantException();
		return dnaSequence;
	}
	
	/**
	 * Servicio para verificar si la secuencia a validar es mutante
	 * Se arma matriz con las secuencias, y se analizan las filas, si es mutante se devuelve respuesta.
	 * Si hay menos de dos sequencias repetidas, se rota y se analizan las filas (antes columnas).
	 * Si la duma de los resultados anteriores sigue sin verificar mutacion, se analiza la diagonal principal
	 * Si todavia no se verifica mutacion, se analizan el resto de las diagonales de la matrix
	 * @param dnaSequence
	 * @return
	 */
	private boolean isMutant(DNASequence dnaSequence){
		int numberOfNitrogenBasesRepetition = Integer.parseInt(this.numberOfNitrogenBasesRepetition);
		int numberOfSequencesOccurence = Integer.parseInt(this.numberOfSequencesOccurence);
		
		int horizontalFinded = dnaSequence.repeatedNitrogenBasesHorizontally( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence );
		LOGGER.info("Horizontalmente se encontraron : "+horizontalFinded+" letras repetidas {} veces",numberOfNitrogenBasesRepetition);
		if( horizontalFinded >= numberOfSequencesOccurence)			
			return true;
		int verticalFinded = dnaSequence.repeatedNitrogenBasesVertically( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		LOGGER.info("Verticalmente se encontraron : "+verticalFinded+" letras repetidas {} veces",numberOfNitrogenBasesRepetition);
		if( (verticalFinded+horizontalFinded) >= numberOfSequencesOccurence)			
			return true;
		int obliquelyFinded = dnaSequence.repeatedNitrogenBasesObliquely( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		LOGGER.info("Oblicuamente se encontraron : "+obliquelyFinded+" letras repetidas {} veces",numberOfNitrogenBasesRepetition);
		if( (verticalFinded+horizontalFinded+obliquelyFinded) >= numberOfSequencesOccurence)		
			return true;
		
		return false;
	}
	
	
}
