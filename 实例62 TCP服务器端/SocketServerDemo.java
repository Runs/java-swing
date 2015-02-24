import java.net.*;
import java.io.*;

public class SocketServerDemo{
	int port=2345; //�˿ں�
	ServerSocket serverSocket; //�������׽���
	
	public SocketServerDemo(){
		try{
			serverSocket=new ServerSocket(port); //ʵ�����׽���
			System.out.println("start server at port "+port); //������������ʾ��Ϣ
			
			while (true){	//һֱ�ȴ��ͻ�������		
				Socket client=serverSocket.accept();  //�ȴ�����
				System.out.println("Connect: "+client.getInetAddress());  //����ͻ�����ַ
				DataInputStream in=new DataInputStream(client.getInputStream()); //�õ�������
				DataOutputStream out=new DataOutputStream(client.getOutputStream()); //�õ������
				
				byte[] message="Connect ok.This message is from server.".getBytes(); //�����Ϣ�ַ�����
				out.write(message,0,message.length); //�����Ϣ
			}
		}
		catch (IOException ex){
				ex.printStackTrace(); //���������Ϣ
		}					
	}
	
	public static void main(String[] args){
		new SocketServerDemo();
	}
}