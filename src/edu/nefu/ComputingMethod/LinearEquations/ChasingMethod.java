package edu.nefu.ComputingMethod.LinearEquations;

public class ChasingMethod
{
	private int order;
	private double [] a;
	private double [] b;
	private double [] c;
	private double [] d;
	private double [] l;
	private double [] u;
	private double [] y;
	private double [] x;
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public double[] getA() {
		return a;
	}
	
	public void setA(double[] a) {
		this.a = a;
	}
	
	public double[] getB() {
		return b;
	}
	
	public void setB(double[] b) {
		this.b = b;
	}
	
	public double[] getC() {
		return c;
	}
	
	public void setC(double[] c) {
		this.c = c;
	}

	public double[] getD() {
		return d;
	}

	public void setD(double[] d) {
		this.d = d;
	}

	public double[] getX() {
		return x;
	}

	public ChasingMethod(int order, double[] a, double[] b, double[] c,double [] d) 
	{
		super();
		this.order = order;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		l=new double[order];
		u=new double[order];
		y=new double[order];
		x=new double[order];
		calculateParameter();
		solve();
	}
	
	private void calculateParameter()
	{
		u[0]=b[0];
		for(int i=1;i<order;i++)
		{
			l[i]=a[i]/u[i-1];
			u[i]=b[i]-l[i]*c[i-1];
		}
	}
	
	private void solve()
	{
		y[0]=d[0];
		for(int i=1;i<order;i++)
			y[i]=d[i]-l[i]*y[i-1];
		x[order-1]=y[order-1]*u[order-1];
		for(int i=order-2;i>=0;i--)
			x[i]=(y[i]-c[i]*x[i+1])/u[i];
	}
	
	public static void main(String[] args) 
	{
		int order=4;
		double [] a={0,-1,-1,-1};
		double [] b={2,2,2,2};
		double [] c={-1,-1,-1};
		double [] d={1,0,0,6};
		ChasingMethod chasingMethod=new ChasingMethod(order, a, b, c, d);
		for(double x:chasingMethod.getX())
			System.out.println(x);
	}
}
