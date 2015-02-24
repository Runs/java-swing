import java.net.*;
import java.io.*;
import java.util.Date;

public class TimeServer{
	int port=13; //�˿ں�
	ServerSocket serverSocket; //�������׽���
	
	public TimeServer(){
		try{
			serverSocket=new ServerSocket(port); //ʵ�����׽���
			System.out.println("start server at port "+port); //������������ʾ��Ϣ
			
			while (true){	//һֱ�ȴ��ͻ�������		
				Socket client=serverSocket.accept();  //�ȴ�����
				System.out.println("Connect from: "+client.getInetAddress());  //����ͻ�����ַ
				PrintStream out=new PrintStream(client.getOutputStream()); //�õ������
				Date now=new Date(); //��ȡ��ǰʱ��
				out.println(now);  //�����ǰʱ�䵽�ͻ���					
			}
		}
		catch (IOException ex){
				ex.printStackTrace(); //���������Ϣ
		}					
	}
	
	public static void main(String[] args){
		new TimeServer();
	}
}