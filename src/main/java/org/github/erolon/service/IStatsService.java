package org.github.erolon.service;

import org.github.erolon.dto.StatsResponse;
import org.github.erolon.model.DNASequence;

public interface IStatsService {

	public StatsResponse getStats();
	
	public void saveDNASequence (DNASequence dnaSequence);
}
