<%@ page language="java" pageEncoding="gb2312"%>

<%
String sNumber="2";
if(request.getParameter("number")!=null)
	sNumber=request.getParameter("number");
int number=Integer.parseInt(sNumber);
%>

������<br>
���ɳڵ����������ɳ�����Ϊ1ʱΪ��˹-���¶�������<br>
ѡ�񷽳̽���
<select name="number" id="number" onchange="number()">
	<%
	for(int i=2;i<=15;i++)
	{
		%>
		<option value="<%=i%>" <%if(i==number) out.print("selected");%>><%=i%></option>
		<%
	}
	%>
</select>
<br>

<form action="../LinearEquationsServlet?task=Iteration" method="post">
	<%
	for(int i=0;i<number;i++)
	{
		for(int j=0;j<number;j++)
		{
			%>
			<input type="text" name="CoefficientMatrix<%=i%><%=j%>" size="2">x<sub><%=j+1%></sub>
			<% 
			if(j!=number-1)
				out.print("+");
		}
		%>
		=<input type="text" name="ConstantTerm<%=i%>" size="2">
		<br>
		<% 
	}
	%>
	�ɳ�����
	<input type="text" name="relaxationFactor" size="2" value="1">
	����
	<input type="text" name="accuracy" size="2" value="0.001"><br>
	<input type="submit">
	<input type="reset">
	<input type="hidden" name="number" value="<%=number%>">
</form>

<script>
function number()
{
	var number=document.getElementById("number").value;
	window.location.href="Iteration.jsp?number="+number;
}
</script>