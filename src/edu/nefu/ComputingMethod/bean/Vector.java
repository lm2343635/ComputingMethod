package edu.nefu.ComputingMethod.bean;

public class Vector 
{
	private int dimension;
	double [] data=new double[dimension];
	
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}

	public Vector(int dimension) 
	{
		super();
		this.dimension = dimension;
	}
	
}
