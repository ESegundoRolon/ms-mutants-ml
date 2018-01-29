package org.github.erolon.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Matrix {
	
	private char data[][];
	
	public Matrix (int rows, int columns){data = new char[rows][columns];}
	
	public Matrix (char data[][]){
		this.data = new char[data.length][data[0].length];
		IntStream.range(0, this.data.length).forEach(row ->
			IntStream.range(0, data[0].length).forEach(column -> this.data[row][column] = data [row][column]));
	}
	
	public char[][] getData() { return data; }

	public Matrix rotate() {
		char[][] returnedData = new char[data.length][data.length];
		IntStream.range(0, data.length).forEach(row ->
		IntStream.range(0, data.length).forEach( column -> returnedData[row][column] = data[data.length -1 - column][row]));
		return new Matrix(returnedData);
	}
	
	public String getMainDiagonal( )
    {
		StringBuilder diagonal = new StringBuilder();
        for (int row = 0; row < data.length -1; row++)
        {
            for (int col = 0; col < data[row].length -1; col++)
            {
            	diagonal.append(String.valueOf(data[row][row]));
            }
        }
        return diagonal.toString();
    }
}
