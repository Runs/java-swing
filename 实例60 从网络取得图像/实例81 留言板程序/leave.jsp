<html>
<head><title>���Ա���</title><head>
<body>
<center><h2>лл��������</h2></center>
<hr>
<h4>���������£�</h4>
<%@ page import="java.sql.*"%>
<%
String getDate=new java.util.Date().toString();
String getName=request.getParameter("Name");
String getEmail=request.getParameter("Email");
String getWords=request.getParameter("Message");
String getTopic=request.getParameter("Topic");
if(getName.length()<1)
{
	out.print("����������");
}
else
if(getEmail.length()<1)
{
	out.print("�����Email����ȷ,����������");
}
else
if(getWords.length()<1)
{
	out.print("��������ȷ������");
}
else
if(getTopic.length()<1)
{
	out.print("����������");
}
else
{
	out.print("<table>");
	out.print("<tr><td align=\"right\">����:</td><td>");
	out.print(getName);
	out.print("</td></tr>");
	out.print("<tr><td align=\"rigut\">Email:</td><td>");
	out.print(getEmail);
	out.print("</td></tr>");
	out.print("<tr><td align=\"rigut\">����:</td><td>");
	out.print(getTopic);
	out.print("</td></tr>");
	out.print("<tr><td valign=\"top\" align=\"rigut\">����:</td><td>");
	out.print(getWords);
	out.print("</td></tr>");
	out.print("<tr><td align=\"rigut\">����:</td><td>");
	out.print(getDate);
	out.print("</td></tr></table>");
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection("jdbc:odbc:example","devon","book");
		Statement sm=con.createStatement();
		String str="insert into messages values('"+getName+"','"+getEmail+"','"+getTopic+"','"+getWords+"','"+getDate+"')";
		sm.executeUpdate(str);
		sm.close();
		con.close();
		out.print("<center><h3>���Գɹ�</h3></center>");
	}
	catch(Exception e)
	{
		out.print(e.getMessage());
	}
}
%>
<hr>
<table width="100%" border="0" cellpadding="0" align="center">
<tr>
<td align="right"><a href="message.jsp" title="���Բ�">���Բ�</a></td>
<td align="center">����</td>
<td align="left"><a href="viewleave.jsp" title="�鿴����">�鿴����</a></td>
</tr></table>
</body>
</html>
