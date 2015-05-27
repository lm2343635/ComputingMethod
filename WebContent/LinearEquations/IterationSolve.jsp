<%@page import="edu.nefu.ComputingMethod.LinearEquations.Iteration"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
Iteration iteration=(Iteration)request.getSession().getAttribute("iteration");
double [][] CoefficientMatrix=iteration.getCoefficientMatrix().getData();
double [] ConstantTerm=iteration.getConstantTerm().getData();
double [] x=iteration.solve().getData();
int number=iteration.getNumber();
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