package edu.nefu.ComputingMethod.util;

import java.util.Arrays;

public class Sort 
{
	public static void main(String[] args) 
	{
		double [] data={-1,-5,4,2};
		Arrays.sort(data);
		for(double i:data)
			System.out.println(i);
	}
}
