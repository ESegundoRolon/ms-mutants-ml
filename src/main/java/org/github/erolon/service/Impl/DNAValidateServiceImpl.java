package org.github.erolon.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.github.erolon.utils.StringUtils;
import org.github.erolon.exceptions.InvalidDNASequenceException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.model.enums.DNANitrogenBases;
import org.github.erolon.service.IDNAValidateService;
import org.springframework.stereotype.Service;

@Service
public class DNAValidateServiceImpl implements IDNAValidateService {

	/**
	 * Metodo para validar si la secuencia de ADN recibida:
	 * No es Numerica
	 * No contiene bases nitrogenadas invalidas
	 * Forman una tabla de NxN
	 * Caso contrario devuelve InvalidDNASequenceException
	 */
	public void isValidOrThrowException(DNASequence dnaSequence) {
		
		String[] dna = dnaSequence.getDNA();
		
		//Si la secuencia ADN contiene numeros no es valida		
		if(getNumericDNASequences(dna).size()>0)
			throw new InvalidDNASequenceException();

		//Si una secuencia contiene bases nitrogenadas desconocidas, no es valida
		 List<String> unknownDNASequences = Arrays.asList(dna).stream()
				.filter( row -> !DNANitrogenBases.isKnownNitrogenBase(row) )
				.collect(Collectors.toList());
		
		if(unknownDNASequences.size()>0 || !isSquareDNASequences(dna))
			throw new InvalidDNASequenceException();
		
		
	}
	
	private List<String> getNumericDNASequences(String[] dna){		
				return Arrays.asList(dna).stream()
				.filter( row -> StringUtils.stringContainsNumbers(row) )
				.collect(Collectors.toList());
	}
	
	private boolean isSquareDNASequences(String[] dna){	
		 if(dna ==  null || dna.length == 0)
			 return false;
		 
		 int dnaNitrogenSequenceSize = dna[0].length();
		 
		 List<String> sameSizeSequencesList = Arrays.asList(dna).stream()
					.filter( row -> row.length()== dnaNitrogenSequenceSize )
					.collect(Collectors.toList());
		 
		 return sameSizeSequencesList.size() == dna.length ? true : false;
		
	}
}
