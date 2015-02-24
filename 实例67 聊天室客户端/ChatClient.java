import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatClient extends Applet {
    
  	TextField tfName = new TextField(15); //���������ı���
  	Button btConnect = new Button("����"); //���Ӱ�ť
  	Button btDisconnect = new Button("�Ͽ�����");
  	TextArea tfChat = new TextArea(8,27); //��ʾ������Ϣ�ı���
  	Button btSend = new Button("����");
  	TextField tfMessage = new TextField(30);  //��������
  	java.awt.List list1 = new java.awt.List(9); //��ʾ�����û���Ϣ
  
   	Socket socket=null;  //���Ӷ˿�
  	PrintStream ps=null; //�����
  	Listen listen=null;  //�����߳�
  
  	public void init() {  //Applet��ʼ��
	    tfChat.setEditable(false);	 //������Ϣ��ʾ��Ϊ���ɱ༭   
	    Panel panel1 = new Panel();  //ʵ�������     
  		Panel panel2 = new Panel();        
  		Panel panel3 = new Panel();
  		tfChat.setBackground(Color.white);  //�������������ɫ
  		panel1.setBackground(Color.orange);
  		panel2.setBackground(Color.pink);    
	    panel3.setBackground(Color.orange);	    
	    panel1.add(new Label("������"));  //��������������
	    panel1.add(tfName);
	    panel1.add(btConnect);
	    panel1.add(btDisconnect);    
	    panel2.add(tfChat);
	    panel2.add(list1);    
	    panel3.add(new Label("������Ϣ"));
	    panel3.add(tfMessage);
	    panel3.add(btSend);	    
	    setLayout(new BorderLayout()); //����Applet���ֹ�����
	    add(panel1, BorderLayout.NORTH);  //������嵽Applet��
	    add(panel2, BorderLayout.CENTER);
	    add(panel3,  BorderLayout.SOUTH);
  	}

  	public boolean action(Event evt,Object obj){    //�¼�����
  		try{
			if(evt.target==btConnect){   //������Ӱ�ť		
			    if (socket==null){
				 	socket=new Socket(InetAddress.getLocalHost(),5656);     //ʵ����һ���׽���			                 
				 	ps=new PrintStream(socket.getOutputStream());   //��ȡ�����
				 	StringBuffer info=new StringBuffer("INFO: ");       			                                                      
				 	String userinfo=tfName.getText()+":"+InetAddress.getLocalHost().toString();
				 	ps.println(info.append(userinfo));	//�����Ϣ		
				 	ps.flush();
				 	listen=new Listen(this,tfName.getText(),socket);    //ʵ���������߳�
				 	listen.start();     //�����߳�
				}   
			}
		   	else if(evt.target==btDisconnect){    //����Ͽ����Ӱ�ť
		         disconnect();  //���öϿ����ӷ���
		    }
		   	else if(evt.target==btSend){   //������Ͱ�ť
		         if(socket!=null){
				     StringBuffer msg=new StringBuffer("MSG: ");     
					 String msgtxt=new String(tfMessage.getText());
					 ps.println(msg.append(tfMessage.getText()));   //������Ϣ  
					 ps.flush();
				 }
		   	}
		}
		catch (Exception ex){
			ex.printStackTrace();  //���������Ϣ
		}
    	return true;
  	}   

  	public void disconnect() {   //�Ͽ����ӷ���
     	if(socket!=null){
			ps.println("QUIT");  //������Ϣ
			ps.flush();
	 	}
  	}

  	class Listen extends Thread{    //�������������͵���Ϣ
 		String name=null;          //�û���
	 	BufferedReader reader ;    //������
 		PrintStream ps=null;     //�����
 		Socket socket=null;      //�����׽���
 		ChatClient client=null;   //�ͻ���ChatClientʵ��

 		public Listen(ChatClient p,String n,Socket s) {  
	 		client=p;
	 		name=n;
	 		socket=s;

	 		try{
			    reader = new BufferedReader(new InputStreamReader(s.getInputStream())); //��ȡ������
		 		ps=new PrintStream(s.getOutputStream());  //��ȡ�����

			}
	 		catch(IOException ex){
	    		client.disconnect(); //������Ͽ�����
	    		ex.printStackTrace(); //���������Ϣ
	   		}
    	}  
  
 		public void run(){  
      		String msg=null;
	  		while(socket!=null){
	     		try{
	     			msg=reader.readLine();  //��ȡ�������˴�����Ϣ
	     		}                 
		 		catch(IOException ex){
	    			client.disconnect(); //������Ͽ�����
	    			ex.printStackTrace(); //���������Ϣ
		 		}
		 		if (msg==null) {    //�ӷ�������������ϢΪ����Ͽ��˴�����
			   		client.listen=null;              
		   			client.socket=null;
		   			client.list1.removeAll();
		   			return;
		 		}
		 		StringTokenizer st=new StringTokenizer(msg,":");   //�ֽ��ַ���
		 		String keyword=st.nextToken();         

		 		if(keyword.equals("newUser")) {    //���û�������Ϣ
		     		client.list1.removeAll();  //�Ƴ�ԭ���û���
			 		while(st.hasMoreTokens()) {    //ȡ��Ŀǰ�����������û���
			     		String str=st.nextToken();
				 		client.list1.add(str);  //���ӵ��û��б���
			 		}
		 		}
		 		else if(keyword.equals("MSG")) {    //������Ϣ	                                                 
	     			String usr=st.nextToken();
			 		client.tfChat.append(usr);  //����������Ϣ����Ϣ��ʾ��
			 		client.tfChat.append(st.nextToken("\0"));
			 		client.tfChat.append("\n");  
			 	}
		 		else if(keyword.equals("QUIT")) {   //����������Ϣ  
		     		System.out.println("Quit");
		     		try{
		      			client.listen=null;
		      			client.socket.close();  //�رն˿�
			  			client.socket=null;
             		}
             		catch(IOException e){}
			  		client.list1.removeAll();  //�Ƴ��û��б�	 
			 		return;
		 		}
	  		}
 		}     
	} 
}     
