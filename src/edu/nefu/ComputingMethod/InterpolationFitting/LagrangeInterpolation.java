package edu.nefu.ComputingMethod.InterpolationFitting;

import edu.nefu.ComputingMethod.bean.Point;

/**
 * 拉格朗日插值多项式
 * @author lm2343635
 *
 */
public class LagrangeInterpolation 
{
	private int number;
	private Point [] points;
	private double [] LnX;
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	public void setPoints(Point[] points) {
		this.points = points;
	}

	public double[] getLnX() {
		return LnX;
	}

	public void setLnX(double[] lnX) {
		LnX = lnX;
	}

	public LagrangeInterpolation(int number, Point[] points) 
	{
		super();
		this.number = number;
		this.points = points;
		calculateLnX();
	}
	
	/**
	 * 计算系数Ln(x)
	 */
	private void calculateLnX()
	{
		LnX=new double[number+1];
		for(int i=0;i<number+1;i++)
		{
			LnX[i]=1;
			for(int j=0;j<number+1;j++)
				if(j!=i)
					LnX[i]*=points[i].x-points[j].x;
		}
	}
	
	/**
	 * 根据拉格朗日多项式估算y
	 * @param x x坐标
	 * @return 估算值y'
	 */
	public double getEstimates(double x)
	{
		double y=0;
		for(int i=0;i<LnX.length;i++)
		{
			double buffer=points[i].y/LnX[i];
			for(int j=0;j<LnX.length;j++)
				if(j!=i)
					buffer*=x-points[j].x;
			y+=buffer;
		}
		return y;
	}
	
	public static void main(String[] args) 
	{
		int number=2;
		Point [] points=new Point[number+1];
		points[0]=new Point(0, 1);
		points[1]=new Point(1, 0.95);
		points[2]=new Point(2, 0.82);
		LagrangeInterpolation lInterpolation=new LagrangeInterpolation(number, points);
		for(double lnx:lInterpolation.getLnX())
			System.out.println(lnx);
		System.out.println(lInterpolation.getEstimates(1.5));
	}
}
