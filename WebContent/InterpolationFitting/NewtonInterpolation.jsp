<%@ page language="java" pageEncoding="gb2312"%>

n��ţ�ٲ�ֵ����ʽ<br>

<%
String sNumber="2";
if(request.getParameter("number")!=null)
	sNumber=request.getParameter("number");
int number=Integer.parseInt(sNumber);
%>

ѡ�����ʽ����
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

<form action="../InterpolationFittingServlet?task=NewtonInterpolation" method="post">
	<%
	for(int i=0;i<number+1;i++)
	{
		%>
		��<%=i+1%>��������꣺
		(<input type="text" size="2" name="p<%=i%>x">,
		<input type="text" size="2" name="p<%=i%>y">)
		<br>
		<%
	}
	%>
	����ֵx���꣺<input type="text" size="2" name="x"><br>
	<input type="hidden" name="number" value="<%=number%>">
	<input type="submit">
	<input type="reset">
</form>

<script>
function number()
{
	var number=document.getElementById("number").value;
	window.location.href="NewtonInterpolation.jsp?number="+number;
}
</script>