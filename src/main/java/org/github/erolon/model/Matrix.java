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
	
//	public static Matrix buildNegativeIdentityMatrix(int size){
//		Matrix matrix = new Matrix(size, size);
//		IntStream.range(0,size).forEach( i -> matrix.data[i][i] = -1);
//		return matrix;
//	}
	
//	public void transpose(){
//		System.out.print("==================="+data.length);
//		IntStream.range(0, data.length).forEach(row ->
//				IntStream.range(0, data.length).forEach( column -> data[row][column] = data[data.length -1 - column][row]));
//	}

	public Matrix rotate() {
		char[][] returnedData = new char[data.length][data.length];
		IntStream.range(0, data.length).forEach(row ->
		IntStream.range(0, data.length).forEach( column -> returnedData[row][column] = data[data.length -1 - column][row]));
		return new Matrix(returnedData);
	}
}
