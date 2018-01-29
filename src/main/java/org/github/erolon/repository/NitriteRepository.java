package org.github.erolon.repository;

import java.io.File;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.github.erolon.configuration.DataBaseConfiguration;
import org.github.erolon.model.DNASequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class NitriteRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(NitriteRepository.class);

	
	public NitriteRepository(){
		
	}
	
	@PostConstruct
	public void springPostConstruct() {
		//repository = nitriteDataBase.getRepository(DNASequence.class);
		LOGGER.info("No se Ejecuto");
	}
	
}
