package org.github.erolon.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.github.erolon.model.enums.DNANitrogenBases;

public class StringUtils {

	public static boolean stringContainsNumbers(String value){
		return Pattern.compile("(.)*(\\d)(.)*", Pattern.CASE_INSENSITIVE).matcher(value).matches();
	}
	
	/**
	 * Se itera sobre cada character del string @stringToAnalyze , buscando las letras del @targetToFind
	 * Para cada letra del @targetToFind se guarda en un mapa las ocurrencias, al finalizar el iterado se verifica
	 * cuantos characters de @targetToFind son al menos el criterio @nbConsecutiveCount
	 * 
	 * Devuelve la cantidad de veces que alguna de las letras de @targetToFind, se encontro en @stringToAnalyze
	 * 
	 * Si se recibe por ejemplo AAAAAAAA se toma como 2 sequencias de 4
	 * 
	 * @param stringToAnalyze
	 * @param targetToFind
	 * @param nbConsecutiveCount
	 * @param nbOcurrences
	 * @return int
	 */
	public static int getNumberOfRepeatedCharacters (String stringToAnalyze,String targetToFind, int nbConsecutiveCount, int nbOcurrences){

		//Creamos hashmap para guardar las ocurrencias de cada caracter a buscar
		Map<String, Integer> occurencesOfMatches = new HashMap<>();
		int j = 0;
		int nbOfRepeatedSequences = 0;
		//Iteramos sobre cada caracter a buscar en el string a analizar
		while( j < targetToFind.length() &&  nbOfRepeatedSequences < nbOcurrences) {		
			int iterator = 1;
			int characterCount = 1;
			//Mientras siga iterando y tenga nbConsecutiveCount consecutivos continua el bucle
			while (iterator < stringToAnalyze.length() && characterCount<nbConsecutiveCount) {
				if (stringToAnalyze.charAt(iterator-1)==stringToAnalyze.charAt(iterator)) characterCount++;
				else characterCount=1;
				iterator++;
			}
			String characterString = String.valueOf(targetToFind.charAt(j));
			if(characterCount == nbConsecutiveCount ){
				//en el map guardo la cantidad de veces que el caracter a machear, se repite en multiplo de nbConsecutiveCount
				//ya que en en stringToAnalyze se puede repetir N veces
				occurencesOfMatches.put(characterString, (occurencesOfMatches.get(characterString) != null ?
						occurencesOfMatches.get(characterString) : 1) % nbConsecutiveCount);
			}
			j++;
			//verifico en cada iteracion del bucle cuantas repeticiones voy
			nbOfRepeatedSequences = occurencesOfMatches.values().stream().mapToInt(Number::intValue).sum();
		}
		return nbOfRepeatedSequences;
	}
	
	public static char[][] transformStringArrayToCharArray(String[] strArray){
		return Stream.of(strArray).map(String::toCharArray).toArray(char[][]::new);
	}
}