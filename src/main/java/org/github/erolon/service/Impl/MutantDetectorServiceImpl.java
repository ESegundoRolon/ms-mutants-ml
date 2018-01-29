package org.github.erolon.service.Impl;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.github.erolon.exceptions.NonMutantException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.model.Matrix;
import org.github.erolon.repository.IDNASequenceRepository;
import org.github.erolon.service.IMutantDetectorService;
import org.github.erolon.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private boolean isMutant(DNASequence dnaSequence){
		int numberOfNitrogenBasesRepetition = Integer.parseInt(this.numberOfNitrogenBasesRepetition);
		int numberOfSequencesOccurence = Integer.parseInt(this.numberOfSequencesOccurence);
		
		int horizontalFinded = dnaSequence.repeatedNitrogenBasesHorizontally( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence );
		LOGGER.info("Horizontalmente se encontraron : "+horizontalFinded+" letras iguales");
		if( horizontalFinded >= numberOfSequencesOccurence)			
			return true;
		int verticalFinded = dnaSequence.repeatedNitrogenBasesVertically( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		LOGGER.info("Verticalmente se encontraron : "+verticalFinded+" letras iguales");
		if( (verticalFinded+horizontalFinded) >= numberOfSequencesOccurence)			
			return true;
		int obliquelyFinded = dnaSequence.repeatedNitrogenBasesObliquely( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		if( (verticalFinded+horizontalFinded+obliquelyFinded) >= numberOfSequencesOccurence)		
			return true;
		
		return false;
	}
	
	
}
