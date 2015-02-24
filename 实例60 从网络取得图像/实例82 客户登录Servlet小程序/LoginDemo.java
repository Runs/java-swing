import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginDemo extends HttpServlet{
	private String dbURL="jdbc:odbc:example";	//���ݿ��ʶ��
	private String user="devon";	//���ݿ��û�
	private String password="book";	//���ݿ��û�����
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
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){		
		try{
			response.setContentType("text/html;charset=gb2312"); //����ͷ��
			PrintWriter out=response.getWriter();  //�õ�PrintWriterʵ��
			String name,password;  //��������
			name=request.getParameter("name");  //�õ�����
			password=request.getParameter("password"); 

			out.println("<HTML><HEAD><TITLE>a</TITLE></HEAD>");  //�����Ϣ���ͻ���
			out.println("<BODY>");
			
			if (validateUser(name,password)){  //������֤�û�����
				out.println("<P><H3>��֤�ɹ�</H3></P>"); //��ʾ��֤���
			}
			else{
				out.println("<P><H3>��֤ʧ��</H3></P>");
			}

			out.println("</BODY></HTML>");			
		
		}
		catch (Exception ex){
			ex.printStackTrace();  //���������Ϣ
		}
	}
	

	public boolean validateUser(String username, String password) {
		try{
		    String sqlStr="select * from users where name=\'"+username+"\' and password=\'"+password+"\'"; //SQL��ѯ���
			Statement st=con.createStatement(); //��ȡPreparedStatement����
			ResultSet rs=st.executeQuery(sqlStr); //ִ�в�ѯ		
			if (rs.next()){ //��ѯ���
				return true;
			}
			st.close(); 
		}
		catch (Exception ex){
			ex.printStackTrace();			
			return false;
		}
		return false;
	}
	
	public void destroy() {
		try {
			con.close(); //�ر�����
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}