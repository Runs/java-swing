import java.net.*;
import java.io.*;
import java.util.zip.GZIPInputStream;

public class GetFile{
	int port=2345; //�˿ں�
	String host="localhost"; //��������ַ
	Socket socket; //�ͻ����׽���
	
	public GetFile(){
		try{
			socket=new Socket(InetAddress.getByName(host),port); //ʵ�����װ���
			
			DataInputStream in=new DataInputStream(socket.getInputStream()); //�õ�������
			GZIPInputStream gin=new GZIPInputStream(in); //ѹ��������
			FileOutputStream fileOut=new FileOutputStream("e:/2.txt"); //�ļ������
			
			
			byte[] buffer=new byte[1024]; //������
			int length;
			while ((length=gin.read(buffer))!=-1){  //��ȡ����
				fileOut.write(buffer,0,length);  //д�����ݵ��ļ�
			} 
			System.out.println("Received Success!");
			gin.close(); //�ر�������
			socket.close(); //�ر��׽���
			fileOut.close(); //�ر������
		}
		catch (IOException ex){
			ex.printStackTrace(); //���������Ϣ
		}			
	}
	
	public static void main(String[] args){
		new GetFile();
	}
}