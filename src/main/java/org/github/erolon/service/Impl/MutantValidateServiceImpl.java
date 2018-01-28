package org.github.erolon.service.Impl;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.github.erolon.exceptions.NonMutantException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.model.Matrix;
import org.github.erolon.repository.IDNASequenceRepository;
import org.github.erolon.service.IMutantValidateService;
import org.github.erolon.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.prisma.todopago.repository.transactions.bsa.impl.TrxEventsRepositoryImpl;

@Service
public class MutantValidateServiceImpl  implements IMutantValidateService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MutantValidateServiceImpl.class);
	
	@Value("${mutant.match.factor}")
	private String numberOfNitrogenBasesRepetition;
	
	@Value("${mutant.secuences.occurences}")
	private String numberOfSequencesOccurence;
	
	@Autowired
	private IDNASequenceRepository dnaSequenceRepository;
	
	@Override
	public boolean verifyMutation(DNASequence dnaSequence) {
		LOGGER.info("Analizando si secuencia recibida es mutante");
		if(!isMutant(dnaSequence))
			throw new NonMutantException();
		LOGGER.info("La secuencia recibida es mutante, guardando en datastore");
		dnaSequence.setIsMutant(true);
		dnaSequenceRepository.saveDNASequence(dnaSequence);
		LOGGER.info("En total se tienen :"+dnaSequenceRepository.countAllDNASequences());
		LOGGER.info("Los mutantes son :"+dnaSequenceRepository.countAllMutantDNASequences());
		return true;
	}
	
	private boolean isMutant(DNASequence dnaSequence){
		int numberOfNitrogenBasesRepetition = Integer.parseInt(this.numberOfNitrogenBasesRepetition);
		int numberOfSequencesOccurence = Integer.parseInt(this.numberOfSequencesOccurence);
		
		int horizontalFinded = dnaSequence.repeatedRowWithNitrogenBasesHorizontally( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence );
		LOGGER.info("Horizontalmente se encontraron : "+horizontalFinded+" letras iguales");
		if( horizontalFinded >= numberOfSequencesOccurence)			
			return true;
		int verticalFinded = dnaSequence.repeatedColumnsWithNitrogenBasesVertically( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		LOGGER.info("Verticalmente se encontraron : "+verticalFinded+" letras iguales");
		if( (verticalFinded+horizontalFinded) >= numberOfSequencesOccurence)			
			return true;
		int obliquelyFinded = dnaSequence.repeatedColumnsWithNitrogenBasesObliquely( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		if( (verticalFinded+horizontalFinded+obliquelyFinded) >= numberOfSequencesOccurence)		
			return true;
		
		return false;
	}
	
	
}
