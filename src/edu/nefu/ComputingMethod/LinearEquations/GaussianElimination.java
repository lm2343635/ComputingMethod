package edu.nefu.ComputingMethod.LinearEquations;

import java.util.Arrays;

public class GaussianElimination
{
	int number;
	private double [][] CoefficientMatrix=new double[number][number];
	private double [] ConstantTerm=new double[number];
	
	public double[][] getA() {
		return CoefficientMatrix;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double[][] getCoefficientMatrix() {
		return CoefficientMatrix;
	}

	public void setCoefficientMatrix(double[][] coefficientMatrix) {
		CoefficientMatrix = coefficientMatrix;
	}

	public double[] getConstantTerm() {
		return ConstantTerm;
	}

	public void setConstantTerm(double[] constantTerm) {
		ConstantTerm = constantTerm;
	}

	public GaussianElimination(int number, double[][] CoefficientMatrix,double[] ConstantTerm) 
	{
		super();
		this.number = number;
		this.CoefficientMatrix = CoefficientMatrix;
		this.ConstantTerm = ConstantTerm;
	}

	private int getZeroRow(double[][] AugmentedMatrix)
	{
		for(int i=1;i<AugmentedMatrix.length;i++)
			for(int j=0;j<i;j++)
				if(AugmentedMatrix[i][j]!=0)
					return i-1;
		return AugmentedMatrix.length;
	}
	
	private double[][] getAugmentedMatrix(double[][] CoefficientMatrix,double [] ConstantTerm)
	{
		double[][] AugmentedMatrix=new double[CoefficientMatrix.length][CoefficientMatrix.length+1];
		for(int i=0;i<AugmentedMatrix.length;i++)
		{
			for(int j=0;j<AugmentedMatrix[0].length-1;j++)
				AugmentedMatrix[i][j]=CoefficientMatrix[i][j];
			AugmentedMatrix[i][AugmentedMatrix[0].length-1]=ConstantTerm[i];
		}
		return AugmentedMatrix;
	}
	
	private double[][] getCoefficientMatrix(double[][] AugmentedMatrix)
	{
		double [][] CoefficientMatrix=new double[AugmentedMatrix.length][AugmentedMatrix.length];
		for(int i=0;i<AugmentedMatrix.length;i++)
			for(int j=0;j<AugmentedMatrix.length;j++)
				CoefficientMatrix[i][j]=AugmentedMatrix[i][j];
		return CoefficientMatrix;
	}
	
	private double[] getConstantTerm(double[][] AugmentedMatrix)
	{
		double [] ConstantTerm=new double[AugmentedMatrix.length];
		for(int i=0;i<AugmentedMatrix.length;i++)
			ConstantTerm[i]=AugmentedMatrix[i][AugmentedMatrix[0].length-1];
		return ConstantTerm;
	}
	
	public static void display(double [][] matrix)
	{
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix[0].length;j++)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}
		System.out.println();
	}
	
	private double[][] exchangeRow(double[][] AugmentedMatrix) 
	{
		int row=getZeroRow(AugmentedMatrix);
		double [] sort=new double[number-row];
		int n=row;
		for(int i=0;i<sort.length;i++)
		{
			sort[i]=Math.abs(AugmentedMatrix[n][0]);
			n++;
		}
		Arrays.sort(sort);
		double [] buffer=new double[4];
		int flag=0;
		for(int i=row;i<AugmentedMatrix.length;i++)
			if(AugmentedMatrix[i][0]==sort[sort.length-1])
			{
				buffer=AugmentedMatrix[i];
				flag=i;
			}	

		AugmentedMatrix[flag]=AugmentedMatrix[row];
		AugmentedMatrix[row]=buffer;
		return AugmentedMatrix;
	}
	
	private double[][] elimination(double[][] AugmentedMatrix) 
	{
		int row=getZeroRow(AugmentedMatrix);
		double [] buffer=new double[number+1];
		for(int i=row+1;i<AugmentedMatrix.length;i++)
		{
			double k=-AugmentedMatrix[i][row]/AugmentedMatrix[row][row];
			double [] add=new double[number+1];
			for(int j=0;j<buffer.length;j++)
				add[j]=k*AugmentedMatrix[row][j];
			for(int j=0;j<buffer.length;j++)
				AugmentedMatrix[i][j]=add[j]+AugmentedMatrix[i][j];
		}
		
		return AugmentedMatrix;
	}
	
	public double [] solve()
	{
		double [] x=new double[number];
		double [][] AugmentedMatrix=getAugmentedMatrix(CoefficientMatrix,ConstantTerm);
		while(getZeroRow(AugmentedMatrix)<AugmentedMatrix.length)
		{
			AugmentedMatrix=exchangeRow(AugmentedMatrix);
			AugmentedMatrix=elimination(AugmentedMatrix);
		}
		display(AugmentedMatrix);
		double [] ConstantTerm=getConstantTerm(AugmentedMatrix);
		double [][] CoefficientMatrix=getCoefficientMatrix(AugmentedMatrix);
		for(int i=AugmentedMatrix.length-1;i>=0;i--)
		{
			double buffer=ConstantTerm[i];
			for(int j=i+1;j<AugmentedMatrix.length;j++)
				buffer-=CoefficientMatrix[i][j]*x[j];
			x[i]=buffer/CoefficientMatrix[i][i];
		}
		return x;	
	}
	
	public static void main(String[] args) 
	{
		double [][] a={{2,1,2},{5,-1,1},{1,-3,-4}};
		double [] b={5,8,-4};
		GaussianElimination gaussianElimination=new GaussianElimination(a.length,a, b);
		double [] x=gaussianElimination.solve();
		for(int i=0;i<x.length;i++)
			System.out.println("x"+i+"="+x[i]);
		
	}
}