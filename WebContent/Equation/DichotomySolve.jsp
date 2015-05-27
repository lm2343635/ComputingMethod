<%@page import="edu.nefu.ComputingMethod.Equation.Dichotomy"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
Dichotomy dichotomy=(Dichotomy)request.getSession().getAttribute("Dichotomy");
double [] coefficient=dichotomy.getCoefficient();
%>

方程
<%
for(int i=coefficient.length-1;i>1;i--)
{
	%>
	<%=coefficient[i]%>x<sup><%=i%></sup>+
	<% 
}
%>
<%=coefficient[1]%>x+
<%=coefficient[0]%><br>
在区间
[<%=dichotomy.getStart() %>,<%=dichotomy.getEnd() %>]
内精度为<%=dichotomy.getAccuracy()%>的解是x=<%=dichotomy.getRoot()%>