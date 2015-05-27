package edu.nefu.ComputingMethod.InterpolationFitting;

import edu.nefu.ComputingMethod.bean.Point;
import edu.nefu.ComputingMethod.bean.Polynomial;

/**
 * 牛顿插值多项式
 * @author lm2343635
 *
 */
public class NewtonInterpolation 
{
	private int order;
	private Point [] points;
	private double [] c;
	private Polynomial polynomial;
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public void setPoints(Point[] points) {
		this.points = points;
	}
	
	public double[] getC() {
		return c;
	}
	public void setC(double[] c) {
		this.c = c;
	}
	
	public Polynomial getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(Polynomial polynomial) {
		this.polynomial = polynomial;
	}

	public NewtonInterpolation(int order, Point[] points) 
	{
		super();
		this.order = order;
		this.points = points;
		calculateC();
		calculatePolynomial();
	}

	private void calculateC()
	{
		c=new double[order+1];
		double [][] f=new double[order+1][];
		f[0]=new double[order+1];
		for(int i=0;i<points.length;i++)
			f[0][i]=points[i].y;		
		for(int i=1;i<order+1;i++)
		{
			f[i]=new double[order+1-i];  
			for(int j=0;j<f[i].length;j++)
				f[i][j]=(f[i-1][j+1]-f[i-1][j])/(points[j+i].x-points[j].x);
		}
		/*
		for(int i=0;i<f.length;i++)
		{
			for(int j=0;j<f[i].length;j++)
				System.out.print(f[i][j]+",");
			System.out.println();
		}
		*/
		for(int i=0;i<order+1;i++)
			c[i]=f[i][0];
	}
	
	private void calculatePolynomial() 
	{
		Polynomial [] polynomials=new Polynomial[order+1];
		polynomials[0]=new Polynomial(0, new double[]{c[0]});
		polynomials[1]=new Polynomial(1, new double[]{-points[0].x,1});
		for(int i=2;i<polynomials.length;i++)
			polynomials[i]=polynomials[i-1].multiplyR(-points[i-1].x);
		for(int i=1;i<polynomials.length;i++)
			polynomials[i].ProportionalExpand(c[i]);
		/*
		for(int i=0;i<polynomials.length;i++)
		{
			for(double co:polynomials[i].getCoefficient())
				System.out.print(co+" ");
			System.out.println();
		}	
		*/
		double [] coefficient=new double[order+1];
		for(int i=0;i<coefficient.length;i++)
			coefficient[i]=0;
		for(int i=0;i<polynomials.length;i++)
		{
			double [] co=polynomials[i].getCoefficient();
			for(int j=0;j<co.length;j++)
				coefficient[j]+=co[j];
		}	
		/*
		for(double co:coefficient)
			System.out.print(co+" ");
			*/
		polynomial=new Polynomial(order+1, coefficient);
	}
	
	public double getEstimates(double x)
	{
		double y=0;
		double [] coefficient=polynomial.getCoefficient();
		for(int i=0;i<coefficient.length;i++)
			y+=coefficient[i]*Math.pow(x, i);
		return y;
	}
	
	public static void main(String[] args) 
	{
		int order=3;
		Point [] points=new Point[order+1];
		points[0]=new Point(1, 0);
		points[1]=new Point(3, 2);
		points[2]=new Point(4, 15);
		points[3]=new Point(7, 12);
		NewtonInterpolation nInterpolation=new NewtonInterpolation(order, points);
		for(double c:nInterpolation.getPolynomial().getCoefficient())
			System.out.println(c);
		System.out.println(nInterpolation.getEstimates(2));
	}
}
