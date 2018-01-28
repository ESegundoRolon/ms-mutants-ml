package org.github.erolon.service;

import static org.junit.Assert.fail;

import org.github.erolon.exceptions.InvalidDNASequenceException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.service.Impl.DNAValidateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DNAValidateServiceTest {

	private DNASequence invalidSquareDNASequence;
	private DNASequence invalidNonSquareDNASequence;
	private DNASequence validDNASequence;
	
	@InjectMocks
	private DNAValidateServiceImpl toTest;
	
	@Before
	public void setUp() {
		toTest = new DNAValidateServiceImpl();
		invalidSquareDNASequence = new DNASequence();
		validDNASequence = new DNASequence();
		invalidNonSquareDNASequence = new DNASequence();
		invalidSquareDNASequence.setDNA(new String[]{"YYAYYY","12345G","ATGCGA","CAGTGC","TTATGT","AGAAGG"});
		invalidNonSquareDNASequence.setDNA(new String[]{"YYAYYY","12345G"});
		validDNASequence.setDNA(new String[]{"CCCCTA","TTATGT", "ATGCGA","CAGTGC","TTATGT","AGAAGG"});
	}
	
	@Test
	public void isValidOrThrowExceptionThrowInvalidDNASequenceException() {
		
		try {
			toTest.isValidOrThrowException(invalidSquareDNASequence);
			fail("Expected a InvalidDNASequenceException but was not thrown");
		} catch (NullPointerException e) {
			fail("Expected a InvalidDNASequenceException but a NullPointerException was received");
		} catch (InvalidDNASequenceException invalidDNAException) {
			// Success
		}
	}
	
	@Test
	public void isValidOrThrowExceptionWithNonSquareDNASequence() throws InvalidDNASequenceException {
		try {
			toTest.isValidOrThrowException(invalidNonSquareDNASequence);
			fail("Expected a InvalidDNASequenceException but was not thrown");
		} catch (NullPointerException e) {
			fail("Expected a InvalidDNASequenceException but a NullPointerException was received");
		} catch (InvalidDNASequenceException invalidDNAException) {
			// Success
		}		
	}
	
	@Test
	public void isValidOrThrowExceptionSucess() throws InvalidDNASequenceException {
		toTest.isValidOrThrowException(validDNASequence);		
	}
}
