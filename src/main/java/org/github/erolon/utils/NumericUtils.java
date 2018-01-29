package org.github.erolon.utils;

import java.text.DecimalFormat;

public class NumericUtils {

	public static double truncate(double value){
		DecimalFormat df = new DecimalFormat("#.#");
		return Double.parseDouble(df.format(value));	
	}
}
