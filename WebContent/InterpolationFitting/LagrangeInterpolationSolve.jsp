<%@page import="edu.nefu.ComputingMethod.InterpolationFitting.LagrangeInterpolation"%>
<%@ page language="java" pageEncoding="gb2312"%>

<%
LagrangeInterpolation lInterpolation=(LagrangeInterpolation)request.getSession().getAttribute("LagrangeInterpolation");
double x=Double.parseDouble(request.getParameter("x"));
%>

<%=lInterpolation.getNumber()%>���������ղ�ֵ����ʽ<br>
L<sub><%=lInterpolation.getNumber()%></sub>(x) =
<% 
double [] lnx=lInterpolation.getLnX();
for(int i=0;i<lnx.length;i++)
{
	out.print("(1/"+lnx[i]+")*");
	for(int j=0;j<lnx.length;j++)
		if(j!=i)
			out.print("(x-"+lInterpolation.getPoints()[j].x+")");
	out.print("*"+lInterpolation.getPoints()[i].y);
	if(i!=lnx.length-1)
		out.print("+");
}
%><br>
��x=<%=x%>��y�Ĺ���ֵ��<%=lInterpolation.getEstimates(x)%>
