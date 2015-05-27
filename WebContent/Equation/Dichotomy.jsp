<%@ page language="java" pageEncoding="gb2312"%>

二分法解幂函数方程
<%
String sNumber="2";
if(request.getParameter("number")!=null)
	sNumber=request.getParameter("number");
int number=Integer.parseInt(sNumber);
%>

选择方程次数
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

<form action="../EquationServlet?task=Dichotomy" method="post">
	<%
	for(int i=number;i>1;i--)
	{
		%>
		<input type="text" name="coefficient<%=i%>" size="2">x<sup><%=i%></sup>+
		<% 
	}
	%>
	<input type="text" name="coefficient<%=1%>" size="2">x+
	<input type="text" name="coefficient<%=0%>" size="2">=0<br>
	区间设置：
	[<input type="text" name="start" size="2">,<input type="text" name="end" size="2">]
	精度：
	<input type="text" name="accuracy" size="2">
	<br>
	<input type="submit" value="解方程">
	<input type="reset">
	<input type="hidden" name="number" value="<%=number%>">
</form>

<script>
function number()
{
	var number=document.getElementById("number").value;
	window.location.href="Dichotomy.jsp?number="+number;
}
</script>