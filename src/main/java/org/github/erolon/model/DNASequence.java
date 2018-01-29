package org.github.erolon.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.github.erolon.dto.DNASequenceRequest;
import org.github.erolon.model.enums.DNANitrogenBases;
import org.github.erolon.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DNASequence {

	private String[] dna;
	@JsonIgnore
	private String dnaNitrogenBases;
	private boolean isMutant;

	public DNASequence(){
		super();
		initialize();
	}
	
	public DNASequence(DNASequenceRequest request){
		super();
		initialize();
		this.dna = request.getDna();
	}
	
	private void initialize(){
		this.dnaNitrogenBases=Stream.of(DNANitrogenBases.values())
		        .map(Enum::name)
		        .collect(Collectors.joining());
	}
	
	public String[] getDNA() {	
		return dna;
	}
	
	public void setDNA(String[] dna) {
		this.dna = dna;
	}
	
	public String getDnaNitrogenBases() {
		return dnaNitrogenBases;
	}

	public void setDnaNitrogenBases(String dnaNitrogenBases) {
		this.dnaNitrogenBases = dnaNitrogenBases;
	}
	
	public void setIsMutant(boolean value){
		this.isMutant = value;
	}
	
	public boolean getIsMutant(){
		return isMutant;
	}
	
	public int repeatedNitrogenBasesHorizontally ( int numberOfNitrogenBasesRepetition , int numberOfSequencesOccurence){
		 List<String> horizontallyRepeatedNitrogenBases = Arrays.asList(dna).stream()
				.filter( row -> getNbConsecutiveNitrogenBases ( row , dnaNitrogenBases , numberOfNitrogenBasesRepetition , numberOfSequencesOccurence) >=1 )
				.collect(Collectors.toList());
		 return horizontallyRepeatedNitrogenBases.size();
	}
	
	public int repeatedNitrogenBasesVertically ( int numberOfNitrogenBasesRepetition , int numberOfSequencesOccurence ){
		Matrix matrix = new Matrix(StringUtils.transformStringArrayToCharArray(dna));
		Matrix rotaedMatrix = matrix.rotate();	
		char[][] data = rotaedMatrix.getData();
		List<String> verticallyRepeatedNitrogenBases = Arrays
		        .stream(data)
		        .map(String::valueOf)
		        .filter(row -> getNbConsecutiveNitrogenBases( row , dnaNitrogenBases , numberOfNitrogenBasesRepetition , numberOfSequencesOccurence) >=1)
		        .collect(Collectors.toList());
		return verticallyRepeatedNitrogenBases.size();
	}
	
	public int repeatedNitrogenBasesObliquely ( int numberOfNitrogenBasesRepetition , int numberOfSequencesOccurence ){
		
		Matrix matrix = new Matrix(StringUtils.transformStringArrayToCharArray(dna));
		int repeatedNitrogenBasesInMainDiagonal = getNbConsecutiveNitrogenBases( matrix.getMainDiagonal() , dnaNitrogenBases , numberOfNitrogenBasesRepetition , numberOfSequencesOccurence);
		if( repeatedNitrogenBasesInMainDiagonal >= numberOfSequencesOccurence)
			return repeatedNitrogenBasesInMainDiagonal;
		List<String> repeatedNitrogenBasesInDiagonals = Arrays.asList(matrix.getDiagonals()).stream()
				.filter( row -> getNbConsecutiveNitrogenBases( row , dnaNitrogenBases , numberOfNitrogenBasesRepetition , numberOfSequencesOccurence) >=1)
				.collect(Collectors.toList());
		return repeatedNitrogenBasesInMainDiagonal + repeatedNitrogenBasesInDiagonals.size();
	}
	
	private int getNbConsecutiveNitrogenBases(String dnaSequence , String nitrogenBases, int numberOfNitrogenBasesRepetitions, int numberOfSequencesRepetitions){
		return StringUtils.getRepetitionsInStringWithCriteria( dnaSequence , nitrogenBases , numberOfNitrogenBasesRepetitions , numberOfSequencesRepetitions );
	}
}
