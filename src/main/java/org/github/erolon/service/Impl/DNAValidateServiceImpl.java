package org.github.erolon.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.github.erolon.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.github.erolon.exceptions.InvalidDNASequenceException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.model.enums.DNANitrogenBases;
import org.github.erolon.service.IDNAValidateService;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DNAValidateServiceImpl implements IDNAValidateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DNAValidateServiceImpl.class);
	
	public void isValidOrThrowException(DNASequence dnaSequence) {
		
		try {
			LOGGER.info("Validando : " + new ObjectMapper().writeValueAsString(dnaSequence));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
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
		
		LOGGER.info("Las secuencias son correcta");
	}
	
	/**
	 * Filtra el array recibido, segun la presencia de caracteres numericos
	 * devuelve los string numericos encontrados
	 * @param dna
	 * @return
	 */
	private List<String> getNumericDNASequences(String[] dna){		
				return Arrays.asList(dna).stream()
				.filter( row -> StringUtils.stringContainsNumbers(row) )
				.collect(Collectors.toList());
	}
	
	/**
	 * Verifica que si se arma una matriz con el array recibido, esta sea cuadrada
	 * @param dna
	 * @return
	 */
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
