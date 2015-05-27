package edu.nefu.ComputingMethod.LinearEquations;

import edu.nefu.ComputingMethod.bean.Matrix;
import edu.nefu.ComputingMethod.bean.Vector;

public class Iteration
{
	private int number;
	private double relaxationFactor=1;
	private double accuracy=0.001;
	private Matrix CoefficientMatrix;
	private Vector ConstantTerm;
	
	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public double getRelaxationFactor() {
		return relaxationFactor;
	}

	public void setRelaxationFactor(double relaxationFactor) {
		this.relaxationFactor = relaxationFactor;
	}

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Matrix getCoefficientMatrix() {
		return CoefficientMatrix;
	}
	
	public void setCoefficientMatrix(Matrix CoefficientMatrix) {
		this.CoefficientMatrix = CoefficientMatrix;
	}
	
	public Vector getConstantTerm() {
		return ConstantTerm;
	}
	
	public void setConstantTerm(Vector ConstantTerm) {
		this.ConstantTerm = ConstantTerm;
	}

	public Iteration(int number,double accuracy,Matrix CoefficientMatrix, Vector ConstantTerm) 
	{
		super();
		this.number = number;
		this.CoefficientMatrix = CoefficientMatrix;
		this.ConstantTerm = ConstantTerm;
		this.accuracy=accuracy;
	}
	
	public Iteration(int number, double relaxationFactor,double accuracy,Matrix coefficientMatrix, Vector constantTerm) 
	{
		super();
		this.number = number;
		this.relaxationFactor = relaxationFactor;
		CoefficientMatrix = coefficientMatrix;
		ConstantTerm = constantTerm;
		this.accuracy=accuracy;
	}
	
	private double getError(Vector v1,Vector v2)
	{
		double error=Math.abs(v1.getData()[0]-v2.getData()[0]);
		for(int i=1;i<number;i++)
			if(Math.abs(v1.getData()[i]-v2.getData()[i])>error)
				error=Math.abs(v1.getData()[i]-v2.getData()[i]);
		return error;
	}

	private Vector getIterationVector(Vector inputVector)
	{
		double [] x=new double[number];
		double [] x1=new double[number];
		for(int i=0;i<number;i++)
			x[i]=inputVector.getData()[i];
		for(int i=0;i<number;i++)
		{
			double buffer=0;
			for(int j=0;j<i;j++)
				buffer+=CoefficientMatrix.getData()[i][j]*x1[j];
			for(int j=i;j<number;j++)
				buffer+=CoefficientMatrix.getData()[i][j]*x[j];
			x1[i]=x[i]+relaxationFactor/CoefficientMatrix.getData()[i][i]*(ConstantTerm.getData()[i]-buffer);
		}
		Vector iterationVector=new Vector(number);	
		for(int i=0;i<number;i++)
			iterationVector.setData(x1);
		return iterationVector;
	}
	
	public Vector solve()
	{
		Vector solve=new Vector(number);
		double [] data=new double[number];
		for(int i=0;i<number;i++)
			data[i]=1;
		solve.setData(data);
		while(getError(solve, getIterationVector(solve))>accuracy)
			solve=getIterationVector(solve);
		return solve;
	}
	
	public static void main(String[] args) 
	{
		Matrix CoefficientMatrix=new Matrix(4, 4);
		CoefficientMatrix.setData(new double[][]
				{
					{5,1,-1,-2},
					{2,8,1,3},
					{1,-2,-4,-1},
					{-1,3,2,7}
				});
		Vector ConstantTerm=new Vector(4);
		ConstantTerm.setData(new double[]{-2,-6,6,12});
		Iteration iteration=new Iteration(4,1.15,0.0001,CoefficientMatrix, ConstantTerm);
		Vector solve=iteration.solve();
		for(double x:solve.getData())
			System.out.println(x);
	}
}