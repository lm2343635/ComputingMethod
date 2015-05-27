package edu.nefu.ComputingMethod.bean;

/**
 * 多项式
 * @author lm2343635
 *
 */
public class Polynomial 
{
	private int order;//多项式阶数
	private double [] coefficient=new double[order+1];//多项式系数
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public double[] getCoefficient() {
		return coefficient;
	}
	
	public void setCoefficient(double[] coefficient) {
		this.coefficient = coefficient;
	}
	
	public Polynomial(int order) 
	{
		super();
		this.order = order;
	}

	public Polynomial(int order, double[] coefficient) 
	{
		super();
		this.order = order;
		this.coefficient = coefficient;
	}
	
	/**
	 * 将一阶多项式(x+c)乘入原多项式
	 * @param c
	 */
	public void multiply(double c)
	{
		 double [] temp=new double[order+2];
		 temp[0]=0;
		 for(int i=1;i<temp.length;i++)
			 temp[i]=coefficient[i-1];
		 for(int i=0;i<temp.length-1;i++)
			 temp[i]+=c*coefficient[i];
		 coefficient=temp;
		 order++;
	}
	
	public Polynomial multiplyR(double c)
	{
		 int myorder=order;
		 double[] mycoefficient=coefficient;
		 double [] temp=new double[order+2];
		 temp[0]=0;
		 for(int i=1;i<temp.length;i++)
			 temp[i]=mycoefficient[i-1];
		 for(int i=0;i<temp.length-1;i++)
			 temp[i]+=c*mycoefficient[i];
		 mycoefficient=temp;
		 myorder++;
		 return new Polynomial(myorder, mycoefficient);
	}
	
	/**
	 * 按比例扩大，即多项式乘k
	 * @param k 比例系数
	 */
	public void ProportionalExpand(double k)
	{
		for(int i=0;i<coefficient.length;i++)
			coefficient[i]*=k;
	}
	
	/**
	 * 展开多项式(x^n-c[n])(x^(n-1)-c[n-1])...(x-c[0])
	 * @param c
	 * @return
	 */
	public static Polynomial expansion(double [] c)
	{
		double [] startCoefficient=new double[2];
		startCoefficient[0]=c[0];
		startCoefficient[1]=1;
		Polynomial polynomial=new Polynomial(1,startCoefficient);
		for(int i=1;i<c.length;i++)
			polynomial.multiply(c[i]);
		return polynomial;
	}
	
//	for(int i=0;i<n;i++)
//		for(int j=0;i<m;j++)
//			c[i+j]=a[i]+[j];
	
	public String getString()
	{
		String str="";
		for(int i=coefficient.length-1;i>=0;i--)
		{
			if(coefficient[i]!=0)
			{
				str+=coefficient[i];
				if(i!=0)
					str+="x^"+i+"";
				if(i!=0)
				{
					if(coefficient[i-1]>=0)
						str+="+";
				}
			}	
		}
		return str;
	}
	
	public static void main(String[] args) 
	{
		/*
		Polynomial p=new Polynomial(2, new double[]{1,2,3});
		p.multiply(1);
		for(double co:p.getCoefficient())
			System.out.println(co);
		*/
		//(x-1)(x-2)(x-3)=(x^2-3x+2)(x-3)=x^3-3x^2+2x-3x^2+9x-6=x^3-6x^2+11x-6
		double [] c={-1,-2,-3};
		Polynomial p=expansion(c);
		p.ProportionalExpand(2);
		for(double co:p.getCoefficient())
			System.out.println(co);
		
	}
}
