import java.net.*;
import java.io.*;
import java.util.zip.GZIPOutputStream;

public class FileServer{
	int port=2345; //�˿ں�
	ServerSocket serverSocket; //�������׽���
	
	public FileServer(){
		try{
			serverSocket=new ServerSocket(port); //ʵ�����׽���
			System.out.println("start server at port "+port); //������������ʾ��Ϣ
			
			while (true){	
				Socket client=serverSocket.accept();  //�ȴ�����
				System.out.println("Connect: "+client.getInetAddress());  //����ͻ�����ַ
				DataOutputStream out=new DataOutputStream(client.getOutputStream()); //�õ������				
				GZIPOutputStream gout=new GZIPOutputStream(out); //ѹ�������	
				FileInputStream fileIn=new FileInputStream("c:/1.txt");  //�����͵��ļ�			
				
				byte[] buffer=new byte[1024]; //��������С
				int length;
				while ((length=fileIn.read(buffer))!=-1){  //��ȡ����
					gout.write(buffer,0,length);  //д�����ݵ��ļ�
				} 
				gout.close(); //�ر������
				fileIn.close();
				client.close(); //�رն˿�
				System.out.println("Send Success.");
			}
		}
		catch (IOException ex){
				ex.printStackTrace(); //���������Ϣ
		}					
	}
	
	public static void main(String[] args){
		new FileServer();
	}
}