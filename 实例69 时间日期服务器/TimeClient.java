import java.net.*;
import java.io.*;

public class TimeClient{
	int port=13; //�˿ں�
	String host="localhost"; //��������ַ
	Socket socket; //�ͻ����׽���
	
	public TimeClient(){
		try{
			socket=new Socket(InetAddress.getByName(host),port); //ʵ�����װ���			
			DataInputStream in=new DataInputStream(socket.getInputStream()); //�õ�������
			byte[] buffer=new byte[256]; //������
			in.read(buffer); //�������ݵ�������
			System.out.println(new String(buffer)); //�����Ϣ
			socket.close(); //�ر��׽���
		}
		catch (IOException ex){
			ex.printStackTrace(); //���������Ϣ
		}			
	}
	
	public static void main(String[] args){
		new TimeClient();
	}
}