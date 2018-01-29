package org.github.erolon.service;

import static org.junit.Assert.fail;

import org.github.erolon.exceptions.InvalidDNASequenceException;
import org.github.erolon.model.DNASequence;
import org.github.erolon.repository.IDNASequenceRepository;
import org.github.erolon.service.Impl.DNAValidateServiceImpl;
import org.github.erolon.service.Impl.MutantDetectorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
public class MutantValidateServiceTest {

	private DNASequence valid4DNASequences;
	private DNASequence valid3DNASequences;
	private DNASequence valid2DNASequences;
	private DNASequence valid8DNASequences;
	private DNASequence valid4DiagonalDNASequences;
	private DNASequence valid8DiagonalDNASequences;
	private String numberOfNitrogenBasesRepetition = "4";
	private String numberOfSequencesOccurence = "2";
	private int nbBasesRepetition = Integer.parseInt(numberOfNitrogenBasesRepetition);
	private int nbSequencesRepetition = Integer.parseInt(numberOfSequencesOccurence);
	
	@InjectMocks
	private MutantDetectorServiceImpl toTest;
	
	@Mock
	IDNASequenceRepository dnaSequenceRepository;
	
	@Before
	public void setUp() {
		toTest = new MutantDetectorServiceImpl();
		valid4DNASequences = new DNASequence();
		valid3DNASequences = new DNASequence();
		valid2DNASequences = new DNASequence();
		valid8DNASequences = new DNASequence();
		valid4DiagonalDNASequences = new DNASequence();
		valid8DiagonalDNASequences = new DNASequence();
		initMock();
		
		ReflectionTestUtils.setField(toTest, "dnaSequenceRepository", dnaSequenceRepository);
		ReflectionTestUtils.setField(toTest, "numberOfNitrogenBasesRepetition", numberOfNitrogenBasesRepetition );
		ReflectionTestUtils.setField(toTest, "numberOfSequencesOccurence", numberOfSequencesOccurence );
						
//		Mockito.when(dnaSequenceRepository.countAllDNASequences()).thenReturn(0);
//		Mockito.when(dnaSequenceRepository.countAllMutantDNASequences()).thenReturn(0);
//		 
	}
	
	private void initMock(){
		valid4DNASequences.setDNA(new String[]{"AAAA","CAGA","TTAA","AAAA"});
		valid3DNASequences.setDNA(new String[]{"TTTT","CAGG","TTTT","TTTG"});
		valid2DNASequences.setDNA(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		valid8DNASequences.setDNA(new String[]{"AAAAAAAA","CAGTGCAA","TTATGTAA","AGAAGGAA","CCCCTAAA","TCACTGAA","CCCCTAAA","TCACTGAA"});
		valid4DiagonalDNASequences.setDNA(new String[]{"ATTT","CAGG","TTAT","TTTA"});
		//valid8DiagonalDNASequences.setDNA(new String[]{"AATAATAA","CAGTGCAA","TTATGTAA","AGAAGGAA","CCGCAATA","TCACTAAA","CCGCTCAC","TCACTGAA"});
		valid2DNASequences = Mockito.spy(valid2DNASequences);
		valid3DNASequences = Mockito.spy(valid3DNASequences);
		valid4DNASequences = Mockito.spy(valid4DNASequences);
		valid8DNASequences = Mockito.spy(valid8DNASequences);
		valid4DiagonalDNASequences = Mockito.spy(valid4DiagonalDNASequences);
		//valid8DiagonalDNASequences = Mockito.spy(valid8DiagonalDNASequences);
	}
	
	@Test
	public void Exist2RepeatedRowWithNitrogenBases() {
		Assert.assertEquals(true,toTest.verifyMutation(valid2DNASequences).getIsMutant());
		verify(valid2DNASequences,times(1)).repeatedNitrogenBasesHorizontally( nbBasesRepetition, nbSequencesRepetition);
		verify(valid2DNASequences,times(1)).repeatedNitrogenBasesVertically( nbBasesRepetition, nbSequencesRepetition);
		verify(valid2DNASequences,times(0)).repeatedNitrogenBasesObliquely( nbBasesRepetition, nbSequencesRepetition);
		
	}
	
	@Test
	public void Exist3RepeatedRowWithNitrogenBases() {
		Assert.assertEquals(true,toTest.verifyMutation(valid3DNASequences).getIsMutant());
		verify(valid3DNASequences,times(1)).repeatedNitrogenBasesHorizontally( nbBasesRepetition, nbSequencesRepetition);
		verify(valid3DNASequences,times(0)).repeatedNitrogenBasesVertically( nbBasesRepetition, nbSequencesRepetition);
		verify(valid3DNASequences,times(0)).repeatedNitrogenBasesObliquely( nbBasesRepetition, nbSequencesRepetition);
		
	}
	
	@Test
	public void Exist4RepeatedRowWithNitrogenBases() {
		Assert.assertEquals(true,toTest.verifyMutation(valid4DNASequences).getIsMutant());
		verify(valid4DNASequences,times(1)).repeatedNitrogenBasesHorizontally( nbBasesRepetition, nbSequencesRepetition);
		verify(valid4DNASequences,times(0)).repeatedNitrogenBasesVertically( nbBasesRepetition, nbSequencesRepetition);
		verify(valid4DNASequences,times(0)).repeatedNitrogenBasesObliquely( nbBasesRepetition, nbSequencesRepetition);
		
	}
	
	@Test
	public void Exist8RepeatedRowWithNitrogenBases() {
		Assert.assertEquals(true,toTest.verifyMutation(valid8DNASequences).getIsMutant());
		verify(valid8DNASequences,times(1)).repeatedNitrogenBasesHorizontally( nbBasesRepetition, nbSequencesRepetition);
		verify(valid8DNASequences,times(0)).repeatedNitrogenBasesVertically( nbBasesRepetition, nbSequencesRepetition);
		verify(valid8DNASequences,times(0)).repeatedNitrogenBasesObliquely( nbBasesRepetition, nbSequencesRepetition);
		
	}
	
//	@Test
//	public void Exist4RepeatedDiagonalRowWithNitrogenBases() {
//		Assert.assertEquals(true,toTest.verifyMutation(valid4DiagonalDNASequences).getIsMutant());
//		verify(valid4DiagonalDNASequences,times(1)).repeatedNitrogenBasesHorizontally( nbBasesRepetition, nbSequencesRepetition);
//		verify(valid4DiagonalDNASequences,times(1)).repeatedNitrogenBasesVertically( nbBasesRepetition, nbSequencesRepetition);
//		verify(valid4DiagonalDNASequences,times(1)).repeatedNitrogenBasesObliquely( nbBasesRepetition, nbSequencesRepetition);
//		
//	}
//	
	
//	@Test
//	public void Exist8RepeatedDiagonalRowWithNitrogenBases() {
//		Assert.assertEquals(true,toTest.verifyMutation(valid8DiagonalDNASequences).getIsMutant());
//		verify(valid8DiagonalDNASequences,times(1)).repeatedNitrogenBasesHorizontally( nbBasesRepetition, nbSequencesRepetition);
//		verify(valid8DiagonalDNASequences,times(1)).repeatedNitrogenBasesVertically( nbBasesRepetition, nbSequencesRepetition);
//		verify(valid8DiagonalDNASequences,times(1)).repeatedNitrogenBasesObliquely( nbBasesRepetition, nbSequencesRepetition);
//		
//	}

}
