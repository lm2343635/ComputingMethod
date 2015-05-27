package edu.nefu.ComputingMethod.Equation;

public class Dichotomy 
{
	private double start;
	private double end;
	private double [] coefficient;
	private double accuracy;
	private double root;
	
	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public void setRoot(double root) {
		this.root = root;
	}

	public double getRoot() {
		return root;
	}
	
	public double getAccuracy() {
		return accuracy;
	}

	public double[] getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double[] coefficient) {
		this.coefficient = coefficient;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public Dichotomy(double start, double end,double [] coefficient,double accuracy) 
	{
		super();
		this.start = start;
		this.end = end;
		this.accuracy=accuracy;
		this.coefficient=coefficient;
		solve(accuracy);
	}

	private double f(double x)
	{
		double y=0;
		for(int i=0;i<coefficient.length;i++)
			y+=Math.pow(x,i)*coefficient[i];  
		return y;
	}
	
	private void dichotomize()
	{
		double middle=(start+end)/2;
		double fStart=f(start);
		double fEnd=f(end);
		double fMiddle=f(middle);
		if(fMiddle*fStart<0)
			end=middle;
		else if(fMiddle*fEnd<0)
			start=middle;
		else if(fMiddle==0)
			start=end=middle;
	}

	private void solve(double accuracy)
	{
		while(Math.abs(start-end)>accuracy)
			dichotomize();
		root=(start+end)/2;
	}
	
	public static void main(String[] args) 
	{
		double [] coff={0.65,0.15,-1.8,1};
		Dichotomy d=new Dichotomy(0.5,1.25,coff, 0.001);
     	System.out.println(d.getRoot());
	}
}
