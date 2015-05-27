package edu.nefu.ComputingMethod.NumericalIntegration;

import edu.nefu.ComputingMethod.bean.Polynomial;

public class TrapezoidIntegration 
{
	private Polynomial polynomial;

	public Polynomial getPolynomial() {
		return polynomial;
	}

	public void setPolynomial(Polynomial polynomial) {
		this.polynomial = polynomial;
	}

	public TrapezoidIntegration(Polynomial polynomial) {
		super();
		this.polynomial = polynomial;
	}
	
	public double getY(double x)
	{
		double y=0;
		 double [] coefficient=polynomial.getCoefficient();
		for(int i=0;i<coefficient.length;i++)
			y+=coefficient[i]*Math.pow(x, i);
		return y;
	}
	
	public double calculateIntegration(double a,double b,int n)
	{
		double h=(b-a)/n;
		double buffer=0;
		double xk=a;
		for(int i=0;i<n;i++)
		{
			buffer+=getY(xk);
			xk+=h;
		}
		double s=(h/2)*(getY(a)+2*buffer+getY(b));
		return s;
	}
	
	public static void main(String[] args) 
	{
		Polynomial p=new Polynomial(2, new double[]{1,1,1});
		TrapezoidIntegration tIntegration=new TrapezoidIntegration(p);
		System.out.println(tIntegration.calculateIntegration(0, 1, 10000));
	}
}
