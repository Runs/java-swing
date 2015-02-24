import java.sql.*;

public class TableDemo{
	private String dbURL="jdbc:odbc:example";	//���ݿ��ʶ��
	private String user="devon";	//���ݿ��û�
	private String password="book";	//���ݿ��û�����
	
	public TableDemo(){
		try	{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");	//װ�����ݿ�����
			Connection con=DriverManager.getConnection(dbURL,user,password); //�õ�����
			Statement st=con.createStatement();
			
			//�½���
			System.out.println("�½���products");  //�����Ϣ������̨
			String sqlStr="create table products(Name varchar(20),Price float,Provider varchar(40),Count int)"; //�½����SQL���
			st.executeUpdate(sqlStr); //ִ��SQL���,�½���
			
			//��������
			sqlStr="insert into products(Name,Price,Provider,Count) values(\'���\',2.5,\'�Ϻ�\',20)"; //��������SQL���
			st.executeUpdate(sqlStr); //ִ�в���
			sqlStr="insert into products(Name,Price,Provider,Count) values(\'����\',5.5,\'����\',13)";
			st.executeUpdate(sqlStr);			
			
			//��ʾ����
			sqlStr="select * from products"; //��ѯ����SQL���
			ResultSet rs=st.executeQuery(sqlStr); //��ȡ�����
			String name,provider; 
			float price;
			int count;
			while (rs.next()){
				name=rs.getString("Name"); //ȡ�ò�ѯ���
				price=rs.getFloat("Price");
				provider=rs.getString("Provider");
				count=rs.getInt("Count");
				System.out.println("Name:"+name+"\tPrice:"+price+"\tProvider:"+provider+"\tCount:"+count); //��ʾ��ѯ���
			}
			
			//ɾ����
			System.out.println("ɾ����products"); 
			sqlStr="drop table products"; //ɾ����SQL���
			st.executeUpdate(sqlStr); //ִ��ɾ��			

			con.close(); //�ر�����			
		}
		catch (Exception ex)	{
			ex.printStackTrace();  //���������Ϣ
		}
	}
	
	public static void main(String args[]){
		new TableDemo();
	}
}