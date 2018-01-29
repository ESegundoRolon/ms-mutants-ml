package org.github.erolon.repository.impl;



import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.github.erolon.model.DNASequence;
import org.github.erolon.repository.IDNASequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Repository
public class DNASequenceRepositoryImpl implements IDNASequenceRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DNASequenceRepositoryImpl.class);

	@Resource
	private Nitrite nitriteDataBase;
	
	private ObjectRepository<DNASequence> repository;
	
	@PostConstruct
	public void springPostConstruct() {
        repository = nitriteDataBase.getRepository(DNASequence.class);
	}
	
	public void save(DNASequence sequence){
		 try {
			LOGGER.info("Guardando: " + new ObjectMapper().writeValueAsString(sequence));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repository.insert(sequence);
	}
	
	@Override
	public int getNumberOfAllDNASequences() {
		return repository.find().size();
	}

	@Override
	public int getNumberOfAllMutantDNASequences() {
		return repository.find(ObjectFilters.eq("isMutant", true)).size();
	}
	

}
