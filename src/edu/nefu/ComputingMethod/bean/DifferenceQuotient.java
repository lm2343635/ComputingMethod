package edu.nefu.ComputingMethod.bean;

public class DifferenceQuotient 
{
	private int order;
	private int start;
	private int end;
	private double data;
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public double getData() {
		return data;
	}
	
	public void setData(double data) {
		this.data = data;
	}

	public DifferenceQuotient(int order, int start, int end) {
		super();
		this.order = order;
		this.start = start;
		this.end = end;
	}
	
	public DifferenceQuotient(int order, int start, int end, double data) {
		super();
		this.order = order;
		this.start = start;
		this.end = end;
		this.data = data;
	}


}
