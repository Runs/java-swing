<html>
<head>
<title>�鿴����</title>
</head>
<body>
<h2>����</h2>
<hr>
<%@ page import="java.sql.*"%>
<%
try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection con=DriverManager.getConnection("jdbc:odbc:example","devon","book");
	String str="select * from messages";
	Statement sm=con.createStatement();
	ResultSet rs=sm.executeQuery(str);
	String name,date,email,topic,words;
	int ID;
	while(rs.next())
	{
		ID=rs.getInt("ID");
		name=rs.getString("name");
		email=rs.getString("email");
		topic=rs.getString("subject");
		words=rs.getString("message");
		date=rs.getString("date");
		out.print("<table>");
		out.print("<tr><td align=\"rigth\"><b>����</b></td><td colspan=\"3\"><b>");
		out.print(topic);
		out.print("</b></td></tr>");
		out.print("<tr><td align=\"right\"><b> ����</b></td><td colspan=\"3\"><b>");
		out.print(name);
		out.print("</b></td></tr>");
		out.print("<tr><td align=\"right\"><b> Email</b></td><td colspan=\"3\"><b>");
		out.print(email);
		out.print("</b></td></tr>");
		out.print("<tr><td align=\"right\"><b> ����</b></td><td colspan=\"3\"><b>");
		out.print(words);
		out.print("</b></td></tr>");
		out.print("<tr><td align=\"right\"><b>����</b></td><td colspan=\"3\"><b>");
		out.print(date);
		out.print("<td><form name=\"deleteform\" action=\"delete.jsp\" method=\"POST\">");
		out.print("<td><input type=\"hidden\" name=\"delete\" value="+ID+"></td>");
		out.print("<td align=\"right\"><input type=\"submit\" name=\"action\" value=\"ɾ��\">");
		out.print("</td></tr></form></table>");
		out.print("</hr>");
	}
	sm.close();
	con.close();
}
catch(Exception e)
{
	out.print(e.getMessage());
}
%>
</body>
</html>
