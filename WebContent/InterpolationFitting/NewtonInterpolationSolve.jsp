<%@page import="edu.nefu.ComputingMethod.InterpolationFitting.NewtonInterpolation"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
NewtonInterpolation nInterpolation=(NewtonInterpolation)request.getSession().getAttribute("NewtonInterpolation");
double x=Double.parseDouble(request.getParameter("x"));
double [] coefficient=nInterpolation.getPolynomial().getCoefficient();
%>

<%=nInterpolation.getOrder()%>��ţ�ٲ�ֵ����ʽ<br>
N<sub><%=nInterpolation.getOrder()%></sub>(x) =
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
��x=<%=x%>��y�Ĺ���ֵ��<%=nInterpolation.getEstimates(x)%>