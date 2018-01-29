package org.github.erolon.model;

import java.util.Arrays;

import org.github.erolon.model.enums.DNANitrogenBases;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;


@RunWith(SpringRunner.class)
public class DNANitrogenBasesTest {

	private String unknownNitrogenSequence;
	private String numericNitrogenSequence;
	private String[] blankOrWhiteSpaceNitrogenSequence;
	private String nullNitrogenSequence;
	private String[] validNitrogenSequences;
	
	@Before
    public void setup() {
		unknownNitrogenSequence = "MNBATH";
		numericNitrogenSequence = "MNBAT5";
		blankOrWhiteSpaceNitrogenSequence = new String []{" YIATG",""," ","AG AGG"};
		nullNitrogenSequence = null;
		validNitrogenSequences = new String []{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
	}
	
	@Test
	public void unknownNitrogenSequence(){
		Assert.assertEquals(false, DNANitrogenBases.isKnownNitrogenBase(unknownNitrogenSequence));
	}
	
	@Test
	public void numericNitrogenSequence(){
		Assert.assertEquals(false, DNANitrogenBases.isKnownNitrogenBase(numericNitrogenSequence));
	}
	
	@Test
	public void blankOrWhiteSpaceNitrogenSequence(){
		Arrays.asList(blankOrWhiteSpaceNitrogenSequence).stream()
		.forEach( nitrogenBase -> Assert.assertEquals(false, DNANitrogenBases.isKnownNitrogenBase(nitrogenBase)));		
	}
	
	@Test
	public void nullNitrogenSequence(){
		Assert.assertEquals(false, DNANitrogenBases.isKnownNitrogenBase(nullNitrogenSequence));
	}
	
	@Test
	public void validNitrogenSequences(){
		Arrays.asList(validNitrogenSequences).stream()
		.forEach( nitrogenBase -> Assert.assertEquals(true, DNANitrogenBases.isKnownNitrogenBase(nitrogenBase)));		
	}
}
