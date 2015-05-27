<%@ page language="java" pageEncoding="gb2312"%>

<%
String sNumber="2";
if(request.getParameter("number")!=null)
	sNumber=request.getParameter("number");
int number=Integer.parseInt(sNumber);
%>

梯形公式求解幂函数积分<br>
选择函数次数
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

<form action="../TrapezoidIntegrationServlet?task=TrapezoidIntegration" method="post">
f(x)=
	<%
		for(int i=0;i<number+1;i++)
		{
			%>
			<input type="text" name="coefficient<%=i%>" size="2">
			<% 
			if(i!=0)
				out.print("x<sup>"+i+"</sup>");
			if(i!=number)
				out.print("+");
		}
		%>
		<br>
		<% 
	%>
	积分区间
	[<input type="text" name="a" size="2" >,
	<input type="text" name="b" size="2">]
	等分区间数
	<input type="text" name="n" size="2" value="10000"><br>
	<input type="submit">
	<input type="reset">
	<input type="hidden" name="number" value="<%=number%>">
</form>

<script>
function number()
{
	var number=document.getElementById("number").value;
	window.location.href="TrapezoidIntegration.jsp?number="+number;
}
</script>