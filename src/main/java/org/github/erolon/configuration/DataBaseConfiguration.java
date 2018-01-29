package org.github.erolon.configuration;

import java.io.File;
import java.util.UUID;

import org.dizitart.no2.Nitrite;
import org.github.erolon.controllers.MutantController;
import org.github.erolon.service.Impl.StatsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataBaseConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseConfiguration.class);
//	@Value("${spring.configuration.env:dev}")
//	private String environment ;
//	
//	@Bean
//	public Nitrite nitriteDataBase(){
//
//	}



}
