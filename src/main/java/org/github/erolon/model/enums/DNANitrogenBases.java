package org.github.erolon.model.enums;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.stream.Collectors;

public enum DNANitrogenBases {
	//TO DO : sacar bases nitrogenadas de tabla Paramaters
	A("A"),T("T"),C("C"),G("G");
	
	private String value;

	
	DNANitrogenBases(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static String[] getNames(Class<? extends Enum<?>> e) {
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
	
	public static boolean isKnownNitrogenBase(String nitrogenSequence){
		
		if(nitrogenSequence == null || nitrogenSequence.isEmpty())
			return false;
		
		String regex = "^[";
		
		String dnaNitrogenBases = EnumSet.allOf(DNANitrogenBases.class)
		 .stream()
		 .map(dnaNitrogen -> dnaNitrogen.getValue().substring(0, 1))
		 .collect(Collectors.joining());

		regex += dnaNitrogenBases+"]*$";
		
		return nitrogenSequence.matches(regex);
	}

}
