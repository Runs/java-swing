import java.net.*;
import java.io.*;

public class SocketClientDemo{
	int port=2345; //�˿ں�
	String host="localhost"; //��������ַ
	Socket socket; //�ͻ����׽���
	
	public SocketClientDemo(){
		try{
			socket=new Socket(InetAddress.getByName(host),port); //ʵ�����װ���
			
			DataInputStream in=new DataInputStream(socket.getInputStream()); //�õ�������
			DataOutputStream out=new DataOutputStream(socket.getOutputStream()); //�õ������
			byte[] buffer=new byte[256]; //������
			in.read(buffer); //�������ݵ�������
			System.out.println(new String(buffer)); //�����Ϣ
			System.out.println("Connect Success!");
			socket.close(); //�ر��׽���
		}
		catch (IOException ex){
			ex.printStackTrace(); //���������Ϣ
		}			
	}
	
	public static void main(String[] args){
		new SocketClientDemo();
	}
}