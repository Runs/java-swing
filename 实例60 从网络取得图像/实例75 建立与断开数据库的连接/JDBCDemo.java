import java.sql.*;

public class JDBCDemo{
	private String dbURL="jdbc:microsoft:sqlserver://202.115.26.181:1433";	// ���ݿ��ʶ��
	private String user="devon";	// ���ݿ��û���
	private String password="book";		// ���ݿ��û�����
	
	public JDBCDemo(){
		try	{
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");  //����������
			Connection con=DriverManager.getConnection(dbURL,user,password); //��ȡ����
			DatabaseMetaData dbmd=con.getMetaData(); //��ȡDatabaseMetaDataʵ��
			
			System.out.println(dbmd.getDatabaseProductName()); //��ȡ���ݿ�����
			System.out.println(dbmd.getDatabaseProductVersion());  //��ȡ���ݿ�汾��
			System.out.println(dbmd.getDriverName());  //��ȡJDBC����������
			System.out.println(dbmd.getDriverVersion());  //��ȡ�������汾��
			System.out.println(dbmd.getUserName());	 //��ȡ��¼�û���

			con.close();  //�ر�����
		}
		catch(Exception ex)	{
			ex.printStackTrace(); //���������Ϣ
		}
	}
	
	public static void main(String[] args){
		new JDBCDemo();
	}
}
