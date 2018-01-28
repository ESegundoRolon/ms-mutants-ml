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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MutantValidateServiceImpl  implements IMutantValidateService{
	
	@Value("${mutant.match.factor}")
	private String numberOfNitrogenBasesRepetition;
	
	@Value("${mutant.secuences.occurences}")
	private String numberOfSequencesOccurence;
	
	@Autowired
	private IDNASequenceRepository dnaSequenceRepository;
	
	@Override
	public boolean verifyMutation(DNASequence dnaSequence) {
		
		if(!isMutant(dnaSequence))
			throw new NonMutantException();
		
		dnaSequence.setIsMutant(true);
		dnaSequenceRepository.saveDNASequence(dnaSequence);
		System.out.print("Registros: "+dnaSequenceRepository.countAllDNASequences());
		System.out.print("Registros Mutantes: "+dnaSequenceRepository.countAllMutantDNASequences());
		return true;
	}
	
	private boolean isMutant(DNASequence dnaSequence){
		int numberOfNitrogenBasesRepetition = Integer.parseInt(this.numberOfNitrogenBasesRepetition);
		int numberOfSequencesOccurence = Integer.parseInt(this.numberOfSequencesOccurence);
		
		int horizontalFinded = dnaSequence.repeatedRowWithNitrogenBasesHorizontally( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence );
		System.out.println("DNA: "+dnaSequence.getDNA()+" found: "+horizontalFinded);
		if( horizontalFinded >= numberOfSequencesOccurence)			
			return true;
		int verticalFinded = dnaSequence.repeatedColumnsWithNitrogenBasesVertically( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		if( (verticalFinded+horizontalFinded) >= numberOfSequencesOccurence)			
			return true;
		int obliquelyFinded = dnaSequence.repeatedColumnsWithNitrogenBasesObliquely( numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		if( (verticalFinded+horizontalFinded+obliquelyFinded) >= numberOfSequencesOccurence)		
			return true;
		
		return false;
	}
	
	
}
