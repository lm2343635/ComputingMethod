package edu.nefu.ComputingMethod.InterpolationFitting;

import edu.nefu.ComputingMethod.LinearEquations.Iteration;
import edu.nefu.ComputingMethod.bean.Matrix;
import edu.nefu.ComputingMethod.bean.Point;
import edu.nefu.ComputingMethod.bean.Polynomial;
import edu.nefu.ComputingMethod.bean.Vector;

/**
 * ��С�������
 * @author lm2343635
 *
 */
public class LeastSquaresFitting 
{
	private int m;//��Ϻ�������
	private Point [] points;//��ϵ�����

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}

	public LeastSquaresFitting(int m,Point[] points) 
	{
		super();
		this.m=m;
		this.points = points;
	}
	
	/**
	 * ����ϵ������
	 * @return ϵ������
	 */
	public Matrix calculateCoefficientMatrix()
	{
		int order=m+1;
		int n=points.length;
		double [][] data=new double[order][order];
		Matrix coefficientMatrix=new Matrix(order,order);
		double [] sumX=new double[2*m+1];
		for(int i=0;i<sumX.length;i++)
		{
			sumX[i]=0;
			for(int j=0;j<n;j++)
				sumX[i]+=Math.pow(points[j].x,i);
		}
		for(int i=0;i<order;i++)
			for(int j=0;j<order;j++)
				data[i][j]=sumX[i+j];
		coefficientMatrix.setData(data);
		return coefficientMatrix;
	}
	
	/**
	 * ���㳣����
	 * @return ������
	 */
	public Vector calculateConstantTerm()
	{
		int order=m+1;
		int n=points.length;
		Vector constantTerm=new Vector(order);
		double [] sumYX=new double[m+1];
		for(int i=0;i<sumYX.length;i++)
		{
			sumYX[i]=0;
			for(int j=0;j<n;j++)
				sumYX[i]+=points[j].y*Math.pow(points[j].x,i);
		}
		constantTerm.setData(sumYX);
		return constantTerm;
	}
	
	/**
	 * ������ʽ
	 * @return ��϶���ʽ
	 */
	public Polynomial solveFittingPolynomial()
	{
		Iteration iteration=new Iteration(m+1, 0.001, calculateCoefficientMatrix(), calculateConstantTerm());
		Polynomial polynomial=new Polynomial(m+1);
		polynomial.setCoefficient(iteration.solve().getData());
		return polynomial;
	}
	
	public static void main(String[] args) 
	{
		Point [] points=new Point[7];
		points[0]=new Point(1, 2);
		points[1]=new Point(2, 3);
		points[2]=new Point(3, 6);
		points[3]=new Point(4, 7);
		points[4]=new Point(6, 5);
		points[5]=new Point(7, 3);
		points[6]=new Point(8, 2);
		LeastSquaresFitting lfFitting=new LeastSquaresFitting(2,points);
		Polynomial polynomial=lfFitting.solveFittingPolynomial();
		System.out.println(polynomial.getString());
	}
}
