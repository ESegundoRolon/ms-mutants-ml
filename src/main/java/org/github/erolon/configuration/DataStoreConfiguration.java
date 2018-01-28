package org.github.erolon.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;



@Configuration
public class DataStoreConfiguration {
	
	@Value("${datastore.persistence.key}")
	private String keyString;

	
	@Bean
	public DatastoreService dataStoreService(){
		return DatastoreServiceFactory.getDatastoreService();
	}
}
