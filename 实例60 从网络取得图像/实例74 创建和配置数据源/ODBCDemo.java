import java.sql.*;

public class ODBCDemo{
	private String dbURL="jdbc:odbc:example";	//���ݿ��ʶ��
	private String user="devon";	//���ݿ��û�
	private String password="book";	//���ݿ��û�����
	
	public ODBCDemo(){
		try	{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	//װ�����ݿ�����
			Connection con=DriverManager.getConnection(dbURL,user,password); //�õ�����
			System.out.println(con.getCatalog());   //��ӡ��ǰ���ݿ�Ŀ¼����
			System.out.println("���ӳɹ�");  
			con.close(); //�ر�����			
		}
		catch (Exception ex)	{
			ex.printStackTrace();  //���������Ϣ
		}
	}
	
	public static void main(String args[]){
		new ODBCDemo();
	}
}