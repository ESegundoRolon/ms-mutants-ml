package org.github.erolon.repository;

import org.github.erolon.model.DNASequence;

public interface IDNASequenceRepository {
	
	public void saveDNASequence(DNASequence dnaSequence);
	
	public int countAllDNASequences();
	
	public int countAllMutantDNASequences();
}
