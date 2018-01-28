package org.github.erolon.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.github.erolon.exceptions.InternalUnexpectedError;
import org.github.erolon.model.DNASequence;
import org.github.erolon.repository.IDNASequenceRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.api.client.util.store.DataStore;
import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@Component
public class DNASequenceRepositoryImpl implements IDNASequenceRepository {
		
	@Resource
	private DatastoreService dataStoreService;

	
	public void saveDNASequence(DNASequence dnaSequence){
		Entity dnaEntity = new Entity(DNASequence.class.getName()); // create a new entity
		dnaEntity.setProperty("dna", dnaSequence.getDNA());
		dnaEntity.setProperty("isMutant", dnaSequence.getIsMutant());
		try {
			dataStoreService.put(dnaEntity); // store the entity

		  } catch (DatastoreFailureException e) {
			  throw new InternalUnexpectedError();
		  }
	}
	
	public int countAllDNASequences(){
		Query query = new Query(DNASequence.class.getName());
		int resultCount = 0;
		try {
			resultCount = dataStoreService.prepare(query).countEntities(FetchOptions.Builder.withDefaults());

		  } catch (DatastoreFailureException e) {
			  throw new InternalUnexpectedError();
		  }
		return resultCount;
	}

	public int countAllMutantDNASequences(){
		final Query query = new Query(DNASequence.class.getName()).setFilter(new FilterPredicate("isMutant", FilterOperator.EQUAL, "true"));
		int resultCount = 0;
		try {
			resultCount = dataStoreService.prepare(query).countEntities(FetchOptions.Builder.withDefaults());

		  } catch (DatastoreFailureException e) {
			  throw new InternalUnexpectedError();
		  }
		return resultCount;
	}

}
