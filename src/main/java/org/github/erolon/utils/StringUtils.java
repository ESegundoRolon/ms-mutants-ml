package org.github.erolon.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class StringUtils {
	
	public static boolean stringContainsNumbers(String value){
		return Pattern.compile("(.)*(\\d)(.)*", Pattern.CASE_INSENSITIVE).matcher(value).matches();
	}
	
	/**
	 * Devuelve el numero de veces que en el string stringToSearch existen nbOcurrences 
	 * veces las repeticiones de algun caracter del string charactersToFind,
	 * nbConsecutiveCount veces consecutivas
	 * 
	 *  <p>Ejemplo: 
	 *  stringToSearch = ATGCCCCTTTT nbOcurrences = 2
	 *  nbConsecutiveCount = 4 charactersToFind = ATGC
	 *  
	 *  Devuelve 2 ya que en ATGCCCCTTTT existen 2 veces las repeticiones 
	 *  de algun caracter del string ATGC, 4 veces consecutivas
	 *  
	 * @param stringToAnalyze
	 * @param charactersToFind
	 * @param nbConsecutiveCount
	 * @param nbOcurrences
	 * @return boolean
	 */
	public static int getRepetitionsInStringWithCriteria ( String stringToSearch , String charactersToFind , int nbConsecutiveCount , int nbOcurrences ){
		//Creamos hashmap para guardar las ocurrencias de cada caracter a buscar
		Map<String, Integer> occurencesOfMatches = new HashMap<>();
		int j = 0;
		int nbOfRepeatedSequences = 0;
		//Iteramos sobre cada caracter a buscar en el string a analizar
		while( j < charactersToFind.length() &&  nbOfRepeatedSequences < nbOcurrences) {		
			int iterator = 1;
			int characterCount = 1;
			//Mientras siga iterando y tenga nbConsecutiveCount consecutivos continua el bucle
			while (iterator < stringToSearch.length() && characterCount < nbConsecutiveCount) {
				if (stringToSearch.charAt(iterator-1)==stringToSearch.charAt(iterator)) characterCount++;
				else characterCount=1;
				iterator++;
			}
			String characterString = String.valueOf(charactersToFind.charAt(j));
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