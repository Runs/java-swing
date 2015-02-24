import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class UDPClientDemo extends JFrame{
	String host="localhost"; //��������ַ
	int port=2345; //�˿ں�
	
	public UDPClientDemo(){
		try{		
			DatagramSocket socket=new DatagramSocket(); //ʵ����һ�����ݱ�Socket
			InetAddress address=InetAddress.getByName(host);  //��������ַ
			byte[] buffer=new byte[256]; //������
			DatagramPacket packet=new DatagramPacket(buffer,buffer.length,address,port); //ʵ����һ�����ݱ�
			socket.send(packet); //���ͱ���
			
			packet=new DatagramPacket(buffer,buffer.length); 
			socket.receive(packet); //���ܻ�Ӧ
			
			String message=new String(packet.getData()); //�õ�������Ϣ
			System.out.println("Received from: "+packet.getAddress()); //��ʾ��ϢԴ��ַ
			System.out.println(message); //��ʾ������������Ϣ
			
			socket.close(); //�رն˿�
		}
		catch (Exception ex){
			ex.printStackTrace(); //���������Ϣ
		}		
	}
	
	public static void main(String[] args){
		new UDPClientDemo();
	}
}