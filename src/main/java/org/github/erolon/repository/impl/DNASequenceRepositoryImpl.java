package org.github.erolon.repository.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.filters.Filters;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.github.erolon.exceptions.InternalUnexpectedException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.repository.IDNASequenceRepository;
import org.github.erolon.repository.NitriteRepository;
import org.github.erolon.service.Impl.MutantDetectorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Repository
public class DNASequenceRepositoryImpl implements IDNASequenceRepository{
	private static final Logger LOGGER = LoggerFactory.getLogger(DNASequenceRepositoryImpl.class);

	private Nitrite nitriteDataBase;
	@Value("${spring.configuration.env:dev}")
	private String environment ;
	protected ObjectRepository<DNASequence> repository;
	
	public DNASequenceRepositoryImpl(){

	}
	
	public void save(DNASequence sequence){
		 try {
			LOGGER.info("Guardando:" + new ObjectMapper().writeValueAsString(sequence));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repository.insert(sequence);
	}
	
	@PostConstruct
	public void springPostConstruct() {
		//repository = nitriteDataBase.getRepository(DNASequence.class);
		String dataDir = System.getProperty("java.io.tmpdir") + File.separator + "nitrite" + File.separator + "data";
        if(environment.equals("dev") )
        	dataDir = "gs://ml-challenge-mutants.appspot.com";
		File file = new File(dataDir);
        if (!file.exists()) 
            file.mkdirs();
        LOGGER.info(file.getPath() + File.separator + UUID.randomUUID().toString() + ".db");
        nitriteDataBase = Nitrite.builder()
		        .compressed()
		        .filePath(file.getPath() + File.separator + UUID.randomUUID().toString() + ".db")
		        .openOrCreate("user", "password");
        repository = nitriteDataBase.getRepository(DNASequence.class);
		LOGGER.info("No se Ejecuto");
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
