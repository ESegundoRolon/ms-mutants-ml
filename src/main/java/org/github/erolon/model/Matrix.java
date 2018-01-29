package org.github.erolon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Matrix {
	private static final Logger LOGGER = LoggerFactory.getLogger(Matrix.class);

	private char data[][];
	
	public Matrix (int rows, int columns){data = new char[rows][columns];}
	
	public Matrix (char data[][]){
		this.data = new char[data.length][data[0].length];
		IntStream.range(0, this.data.length).forEach(row ->
			IntStream.range(0, data[0].length).forEach(column -> this.data[row][column] = data [row][column]));
	}
	
	public char[][] getData() { return data; }

	/**
	 * Metodo para rotar una matrix en 90 grados sentido horario
	 * @return Matrix rotada
	 */
	public Matrix rotate() {
		char[][] returnedData = new char[data.length][data.length];
		LOGGER.debug("Antes de rotar: {}",Arrays.deepToString(data));
		IntStream.range(0, data.length).forEach(row ->
		IntStream.range(0, data.length).forEach( column -> returnedData[row][column] = data[data.length -1 - column][row]));
		LOGGER.debug("Luego de rotar: {}",Arrays.deepToString(returnedData));
		return new Matrix(returnedData);
	}
	
	/**
	 * Devuelve los caracteres de la diagonal principal en un string
	 * @return String 
	 */
	public String getMainDiagonal( )
    {
		StringBuilder diagonal = new StringBuilder();
		IntStream.range(0, this.data.length).forEach(row -> diagonal.append(String.valueOf(data[row][row]) ));
        String result = diagonal.toString();
        LOGGER.debug("Diagonal principal: {}", result );
        return result;
    }
	
	/**
	 * Devuelve todas las diagonales excluyendo la principal
	 * @return String 
	 */
	public String[] getDiagonals(){
		List<String> diagonals = new ArrayList<String>();
		
	    for( int k = 0 ; k < data.length * 2 ; k++ ) {
	    	StringBuilder diagonal = new StringBuilder();
	        for( int j = 0 ; j <= k ; j++ ) {
	            int i = k - j;
	            if( i < data.length && j < data.length ) {          	
            		diagonal.append( String.valueOf(data[i][j]) );
            	}
            }
	        if(!diagonal.toString().isEmpty()){
	        	LOGGER.debug("Agrego : {}",diagonal.toString());
	        	diagonals.add(diagonal.toString());	     
	        }
	    }
	    //el primero y el anteultimo son los vertices superior izquierdo e inferior derecho
	    diagonals.remove(0);
	    diagonals.remove(diagonals.size()-1);
	    return diagonals.toArray(new String[0]);
	}
}
