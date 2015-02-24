import java.io.*;
import java.net.*;
import javax.net.ssl.*;

//ʹ��SSL�׽��ֵ�HTTPS��������
//���ܿͻ��˵�һ������,������Hello,world.
public class HTTPSExample {

    public static void main(String[] args) {
  	try{

    //ʹ��8080�˿ڴ���SSL�������׽���
    SSLServerSocketFactory sslSocket =
      (SSLServerSocketFactory)SSLServerSocketFactory.getDefault(); //����ȱʡ��SocketFactory����
    ServerSocket serSocket = sslSocket.createServerSocket(8080);  //��8080�˿ڴ���һ��ServerSocket����

    //�ȴ�����
    while (true) {
        Socket socket = serSocket.accept();  //��������

        //��ȡ�ͻ�������
        BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
        System.out.println(in.readLine());

        //���ؿͻ��˵Ļ�Ӧ
        PrintWriter out = new PrintWriter( socket.getOutputStream()); //����PrintWriter����,���������Ϣ
        StringBuffer strBuffer = new StringBuffer();  //���ص�HTML����Ϣ
        strBuffer.append("<HTML><HEAD><TITLE>HTTPS Example</TITLE></HEAD>");  //���ӷ�����Ϣ
        strBuffer.append("<BODY><H2>Congratulate!</H2></BODY></HTML>");  //���ӷ�����Ϣ
        out.println(strBuffer.toString());  //�����Ϣ

        //�ر�����
        out.close();
        socket.close();
        }
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

}
