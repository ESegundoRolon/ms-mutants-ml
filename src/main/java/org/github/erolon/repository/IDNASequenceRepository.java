package org.github.erolon.repository;

import org.github.erolon.model.DNASequence;


public interface IDNASequenceRepository  {
	
	public int getNumberOfAllDNASequences();
	
	public int getNumberOfAllMutantDNASequences();
	
	public void save(DNASequence sequence);
	
}
