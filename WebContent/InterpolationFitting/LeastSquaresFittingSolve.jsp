<%@page import="edu.nefu.ComputingMethod.InterpolationFitting.LeastSquaresFitting"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
LeastSquaresFitting lFitting=(LeastSquaresFitting)request.getSession().getAttribute("LeastSquaresFitting");
double [] coefficient=lFitting.solveFittingPolynomial().getCoefficient();
%>
拟合曲线方程<br>
p<sub><%=lFitting.getPoints().length%></sub>(x) =
<% 
for(int i=coefficient.length-1;i>=0;i--)
{
	if(coefficient[i]!=0)
	{
		out.print(coefficient[i]);
		if(i!=0)
			out.print("x<sup>"+i+"</sup>");
		if(i!=0)
		{
			if(coefficient[i-1]>=0)
				out.print("+");
		}
	}	
}
%><br>
<a href="../index.html">返回首页</a>