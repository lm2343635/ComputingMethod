package edu.nefu.ComputingMethod.bean;

public class Matrix 
{
	private int row;
	private int column;
	double [][] data=new double[row][column];
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public double[][] getData() {
		return data;
	}
	
	public void setData(double[][] data) {
		this.data = data;
	}

	public Matrix(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}
}
