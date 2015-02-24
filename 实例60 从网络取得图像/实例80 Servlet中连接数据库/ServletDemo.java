import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class ServletDemo extends HttpServlet{	

	private String dbURL="jdbc:odbc:example";	// ���ݿ��ʶ��
	private String user="devon";	// ���ݿ��û�
	private String password="book";	// ���ݿ��û�����
	private Connection con;
	
	public void init() {
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  //װ�����ݿ�����
	        con=DriverManager.getConnection(dbURL,user,password); //��ȡ����
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req,HttpServletResponse res)  {
		try{
	    	res.setContentType("text/html"); //��������      
	        
	        //���Ӳ���ѯ���ݿ�

	        String sqlStr="select * from users"; //SQL��ѯ���
			Statement st=con.createStatement(); //��ȡPreparedStatement����
			ResultSet rs=st.executeQuery(sqlStr); //ִ�в�ѯ
			
			//����ҳ�������ѯ���		
			PrintWriter out = new PrintWriter(res.getOutputStream()); //��ȡ�����
	    	out.println("<html>"); //�������
	        out.println("<head>");
	        out.println("<title>SimpleDB</title>");
	        out.println("</head>");	
	        out.println("<body>");
			out.println("<center><table border='2'>");	    	
	    	ResultSetMetaData rsmd = rs.getMetaData(); //��ȡResultSetMetaData����
	    	int colCount = rsmd.getColumnCount(); //��ȡ������
	    	out.println("<tr>");
	    	for(int i=0; i<colCount; i++){
	    		out.println("<th>"+rsmd.getColumnLabel(i+1)+"</th>"); //��ȡ�б���
	    	}
	    	out.println("</tr>");
	    	while(rs.next()){ //�������
	    	 	out.println("<tr>");
	    	 	for(int i=0; i<colCount; i++) {
	    	 	 	out.println("<td>"+rs.getString(i+1)+"</td>"); //�������
	    	 	}
	    	 	out.println("</tr>");
	    	}
	    	out.println("</table></center>");	    	
	     	out.println("</body></html>");	     

	     	out.close();  //�ر������
	     	
	   	}
	   	catch (Exception ex){
			ex.printStackTrace();  //���������Ϣ
	   	}
	}
	
	public void destroy() {
		try {
			con.close(); //�ر�����
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}