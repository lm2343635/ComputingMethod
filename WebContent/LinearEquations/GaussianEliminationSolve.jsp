<%@page import="edu.nefu.ComputingMethod.LinearEquations.GaussianElimination"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
GaussianElimination gaussianElimination=(GaussianElimination)request.getSession().getAttribute("GaussianElimination");
double [][] CoefficientMatrix=gaussianElimination.getCoefficientMatrix();
double [] ConstantTerm=gaussianElimination.getConstantTerm();
double [] x=gaussianElimination.solve();
int number=gaussianElimination.getNumber();
%>
方程<br>
<%
for(int i=0;i<number;i++)
{
	for(int j=0;j<number;j++)
	{
		%>
		<%=CoefficientMatrix[i][j]%>x<sub><%=j+1%></sub>
		<% 
		if(j!=number-1)
			out.print("+");
	}
	%>
	=<%=ConstantTerm[i]%>
	<br>
	<% 
}
%>
的解是<br>
<%
for(int i=0;i<number;i++)
{
	%>
	x<sub><%=i+1%></sub>=<%=x[i]%><br>
	<%
}
%>
<a href="../index.html">返回首页</a>