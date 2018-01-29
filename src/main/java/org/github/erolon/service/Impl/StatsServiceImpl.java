package org.github.erolon.service.Impl;

import org.github.erolon.dto.StatsResponse;
import org.github.erolon.model.DNASequence;
import org.github.erolon.repository.IDNASequenceRepository;
import org.github.erolon.service.IStatsService;
import org.github.erolon.utils.NumericUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsServiceImpl implements IStatsService{

	private static final Logger LOGGER = LoggerFactory.getLogger(StatsServiceImpl.class);
	
	@Autowired
	private IDNASequenceRepository dnaSequenceRepository;
	
	/**
	 * Servicio para obtener la cantidad de secuencias guardadas
	 * Obtiene todos los documentos filtrando por el flag isMutant,
	 * luego obtiene todos los documentos y de la resta se obtiene 
	 * las secuencias de ADN humanas, y el ratio
	 */
	public StatsResponse getStats(){
		int nbMutandDNA = dnaSequenceRepository.getNumberOfAllMutantDNASequences();
		int nbAllDNA = dnaSequenceRepository.getNumberOfAllDNASequences();
		
		int nbHumanDNA = nbAllDNA - nbMutandDNA;
		double ratio = (nbAllDNA) != 0 ? ((float)nbMutandDNA / ((float)nbAllDNA)): 0.0;
		ratio = NumericUtils.truncate(ratio);

		
		LOGGER.info("El numero de todas las secuencias guardadas es: "+nbAllDNA);
		LOGGER.info("El numero de todas las secuencias mutantes es: "+nbMutandDNA);
		LOGGER.info("El numero de todas las secuencias humanas es: "+nbHumanDNA);
		LOGGER.info("El ratio : "+ratio);
		
		return new StatsResponse( nbMutandDNA , nbHumanDNA , ratio );
	}
	
	/**
	 * Servicio para guardar el objeto DNASequence con las secuencias de ADN y el flag si es
	 * mutante
	 */
	public void saveDNASequence (DNASequence dnaSequence){
		dnaSequenceRepository.save(dnaSequence);
	}
}
