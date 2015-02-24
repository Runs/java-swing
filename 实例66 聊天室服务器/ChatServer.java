import java.net.*;
import java.io.*;
import java.util.*;


public class ChatServer {

	static int port=5656;  //�˿ں�
  	static Vector clients=new Vector(10);   //�洢���ӿͻ���Ϣ
  	static ServerSocket server=null;    //����������socket
  	static Socket socket=null;   //�׽�������

  	public ChatServer() {                      
		try {
  			System.err.println("Server start...");
	    	server=new ServerSocket(port);    //��ʼ���������׽���
			while(true){	   
		    	socket=server.accept();   //�ȴ�����
		    	System.err.println(socket.getInetAddress()+"����\n"); //�õ��ͻ�����ַ
		    	Client client=new Client(socket);  //ʵ����һ���ͻ��߳�
			    clients.addElement(client);  //���ӿͻ��̵߳�������           		
			   	client.start();   //�����߳�
				notifyChatRoom();   //�������������ӱ仯					                                               
			}
		}
		catch(Exception ex) {
	    	ex.printStackTrace(); //���������Ϣ
		}
  	}
  	
  	public static void notifyChatRoom() {   
		StringBuffer newUser=new StringBuffer("newUser");
	 	for(int i=0;i<clients.size();i++){
	     	Client c=(Client)clients.elementAt(i);
		 	newUser.append(":"+c.name);	//�ͻ��������ַ���	 
	 	}
	 	sendClients(newUser);   //������Ϣ���ͻ���
  	}

  	public static void sendClients(StringBuffer message){ 
     	for(int i=0;i<clients.size();i++){		
	     	Client client=(Client)clients.elementAt(i); //�ֱ�õ�ÿ���ͻ��˵�����
		 	client.send(message);  //������Ϣ
	 	}
  	}

  	public void closeAll()	{   //�ر���������
     	while(clients.size()>0) {   //��������Vector
			Client client=(Client)clients.firstElement();  //�õ�һ���ͻ���
		 	try{
		     	client.socket.close(); //�رն˿�
		 	}
		 	catch(IOException ex){
		     	ex.printStackTrace(); //���������Ϣ
		 	}
		    clients.removeElement(client); //�Ƴ��ͻ�����Ϣ
		}
	}

 	public static void disconnect(Client c){     //�Ͽ��ͻ���
		try{
		 	System.err.println(c.ip+"�Ͽ�����\n");
	     	c.send(new StringBuffer("QUIT"));   //��ͻ����ͶϿ�������Ϣ
		 	c.socket=null;	//�رն˿�	 
	 	}
	 	catch(Exception ex){
	    	ex.printStackTrace();
	 	}
	    clients.removeElement(c);   //�Ƴ��ͻ�����Ϣ
  	}
  
    public static void main(String[] args) {
    	new ChatServer();      //ʵ����һ��ChatServer��
  	}
  	
  	class Client extends Thread  {  
	    Socket socket;    //���Ӷ˿�
		String name;   //�û�����
		String ip;    //�ͻ���IP��ַ
	 	BufferedReader reader ;  //������
		PrintStream ps;   //�����   
	
		public  Client(Socket s){ 	  
		   socket=s;                                           
		   try{
		       reader = new BufferedReader(new InputStreamReader(s.getInputStream())); //�õ�������
			   ps=new PrintStream(s.getOutputStream());     //�õ������
			   String info=reader.readLine();   //��ȡ���ܵ�����Ϣ
			   StringTokenizer stinfo=new StringTokenizer(info,":");  //�ֽ��ַ���
			   String head=stinfo.nextToken();    //��ȡ�ؼ���
	           if(stinfo.hasMoreTokens())		   
			   		name=stinfo.nextToken();     //��ȡ�û���
			   if(stinfo.hasMoreTokens())
			   		ip=stinfo.nextToken();    //��ȡIP��ַ
		   }
		   catch(IOException ex){
		       ex.printStackTrace();
		   }
		}
		
		public void send(StringBuffer msg) {  
		   ps.println(msg);   //�����Ϣ
		   ps.flush();
		}
	
		public void run(){    
		   	while(true){
		       	String line=null;
			   	try{
			      line=reader.readLine();  //��ȡ������		       
			   	}
			   	catch(IOException ex){
			       ex.printStackTrace(); //���������Ϣ
				   ChatServer.disconnect(this); //�Ͽ�����
				   ChatServer.notifyChatRoom(); //������Ϣ
				   return;
			   	}
	
			   	if(line==null){    //�ͻ��뿪			  
				   ChatServer.disconnect(this);
				   ChatServer.notifyChatRoom();
				   return;
				}
			   
			   	StringTokenizer st=new StringTokenizer(line,":"); //�ֽ��ַ���
			   	String keyword=st.nextToken();
	
			   	if(keyword.equals("MSG")){  //����������������Ϣ                 			  
			       	StringBuffer msg=new StringBuffer("MSG:");    
				   	msg.append(name);  //����Ϣ�������û���
				   	msg.append(st.nextToken("\0"));
				   	ChatServer.sendClients(msg);      //����������䵽�����ͻ���
			   	}
			   	else if(keyword.equals("QUIT")){ 	//�˳�����		   		
			       	ChatServer.disconnect(this);   //�Ͽ����� 
				   	ChatServer.notifyChatRoom();   //ˢ����Ϣ 
				}
			}
		}     
	}
}

