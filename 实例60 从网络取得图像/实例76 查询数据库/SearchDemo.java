import java.sql.*;

public class SearchDemo{
	private String dbURL="jdbc:microsoft:sqlserver://202.115.26.181:1433";	// ���ݿ��ʶ��
	private String user="devon";	// ���ݿ��û�
	private String password="book";	// ���ݿ��û�����
	
	public SearchDemo(){
		try	{
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");  //����������
			Connection con=DriverManager.getConnection(dbURL,user,password); //��ȡ����
			String sqlStr="select * from users where age>20"; //SQL��ѯ���
			Statement st=con.createStatement(); //��ȡStatement����
			ResultSet rs=st.executeQuery(sqlStr); //ִ�в�ѯ
			String name,sex,email; //��ѯ���
			int age;
			while (rs.next()){ //����ResultSet
				name=rs.getString("name"); //��ȡ����
				age=rs.getInt("age");
				sex=rs.getString("sex");
				email=rs.getString("email");
				System.out.println("Name: "+name+"\tAge:"+age+"\tSex:"+sex+"\tEmail:"+email); //�ڿ���̨�������
			}			

			con.close(); //�ر�����
		}
		catch(Exception ex){
			ex.printStackTrace(); //���������Ϣ
		}
	}
	
	public static void main(String[] args){
		new SearchDemo();
	}
}