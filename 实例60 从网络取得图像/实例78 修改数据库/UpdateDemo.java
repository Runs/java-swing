import java.sql.*;

public class UpdateDemo{
	private String dbURL="jdbc:odbc:example";	// ���ݿ��ʶ��
	private String user="devon";	// ���ݿ��û�
	private String password="book";	// ���ݿ��û�����
	
	public UpdateDemo(){
		try	{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  //����������
			Connection con=DriverManager.getConnection(dbURL,user,password); //��ȡ����
			String sqlStr="select * from users"; //SQL��ѯ���
			Statement st=con.createStatement(); //��ȡPreparedStatement����
			ResultSet rs=st.executeQuery(sqlStr); //ִ�в�ѯ
			String name,sex,email; //��ѯ���
			int age;
			System.err.println("����ǰ����");
			while (rs.next()){ //����ResultSet
				name=rs.getString("name"); //��ȡ����
				age=rs.getInt("age");
				sex=rs.getString("sex");
				email=rs.getString("email");
				System.out.println("Name: "+name+"\tAge:"+age+"\tSex:"+sex+"\tEmail:"+email); //�ڿ���̨�������
			}
			
			//���²���
			sqlStr="update users set age=21 where name=\'John\'";
			st.executeUpdate(sqlStr);

			
			//ɾ������
			sqlStr="delete from users where name=\'Jinnifier\'";
			st.executeUpdate(sqlStr);
			
			//�������
			sqlStr="insert into users(name,age,sex,email) values(\'Bush\',53,\'male\',\'bush@yahoo.com\')";
			st.executeUpdate(sqlStr);
			
			//��ʾ�޸ĺ���	
			System.err.println("���º�����");		
			sqlStr="select * from users"; //SQL��ѯ���		
			rs=st.executeQuery(sqlStr); //ִ�в�ѯ
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
		new UpdateDemo();
	}
}