import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

public class UDPServerDemo extends JFrame{	
	int port=2345;	//�˿ں�
	
	public UDPServerDemo(){
		try{
			byte[] buffer=new byte[256]; //����������
			DatagramSocket socket=new DatagramSocket(port);	 //ʵ�������ݱ�Socket
			DatagramPacket packet; 
			System.out.println("Server start..."); //���������Ϣ
			
			while (true){
				packet=new DatagramPacket(buffer,buffer.length); //ʵ�������ݱ�
				socket.receive(packet);	 //��������							
				InetAddress target=packet.getAddress(); 
				System.out.println("Received from"+target); //��������ַ
				int port=packet.getPort(); //�õ����ն˿�
				byte[] message="This is server,Who are you?".getBytes(); //������������Ϣ
				packet=new DatagramPacket(message,message.length,target,port); //ʵ�������ݱ�
				socket.send(packet); //�������ݱ�
			}			
		}
		catch (Exception ex){
			ex.printStackTrace(); //���������Ϣ
		}
	}
	
	public static void main(String[] args){
		new UDPServerDemo();
	}
}