<%@page import="edu.nefu.ComputingMethod.Equation.Dichotomy"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
Dichotomy dichotomy=(Dichotomy)request.getSession().getAttribute("Dichotomy");
double [] coefficient=dichotomy.getCoefficient();
%>

����
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
������
[<%=dichotomy.getStart() %>,<%=dichotomy.getEnd() %>]
�ھ���Ϊ<%=dichotomy.getAccuracy()%>�Ľ���x=<%=dichotomy.getRoot()%>