import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import sun.net.ftp.*;

//FTP���������

public class FtpClientDemo extends JFrame{
	
	JTextField jtfServer;  //����Ftp��������ַ
	JTextField jtfUser;  //�����û���
	JPasswordField jtfPass;  //��������
	JButton jbConnect;  //���Ӱ�ť
	JButton jbDisconnect;  //�Ͽ���ť
	JTextArea jtaShowFiles;  //��ʾ������Ŀ¼���ļ�
	FtpClient ftpClient; 
	
	public FtpClientDemo(){
		super("FTP���������");  //���ø��๹�캯��
				
		jtfServer=new JTextField(13);  //ʵ�������
		jtfUser=new JTextField(8);
		jtfPass=new JPasswordField(8);
		jbConnect=new JButton("����");
		jbDisconnect=new JButton("�Ͽ�");
		jtaShowFiles=new JTextArea();		

		Container container=getContentPane(); //�õ�����
		JPanel panel=new JPanel();  //ʵ�������
		panel.add(new JLabel("��ַ")); //��������������
		panel.add(jtfServer);
		panel.add(new JLabel("�û���"));
		panel.add(jtfUser);
		panel.add(new JLabel("����"));
		panel.add(jtfPass);
		container.add(panel,BorderLayout.NORTH);  //���������������
		JScrollPane jsp=new JScrollPane(jtaShowFiles);
		container.add(jsp,BorderLayout.CENTER);
		panel=new JPanel();
		panel.add(jbConnect);
		panel.add(jbDisconnect);
		container.add(panel,BorderLayout.SOUTH);

		jbConnect.addActionListener(new ActionListener(){  //���Ӱ�ť�¼�����
			public void actionPerformed(ActionEvent ent){
				connectServer();  //�������ӵ��������ķ���
			}	
		});		
		
		jbDisconnect.addActionListener(new ActionListener(){  //�Ͽ���ť�¼�����
			public void actionPerformed(ActionEvent ent){
				disconnectServer();  //���öϿ�����������ӵķ���
			}	
		});	
		
		setSize(460,260);  //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
	public void connectServer(){
		try{
			ftpClient = new FtpClient();  //ʵ����FtpClient����
			String serverAddr=jtfServer.getText();  //�õ���������ַ
			String user=jtfUser.getText();  //�õ��û���
			String pass=jtfPass.getPassword().toString();  //�õ�����
			ftpClient.openServer(serverAddr);  //���ӵ�������
			ftpClient.login(user,pass);  //�ڷ�������ע��
			InputStream is=ftpClient.list();  //�õ�������Ŀ¼���ļ��б�������
			StringBuffer info=new StringBuffer();  //ʵ����StringBuffer����,���������Ϣ
			int ch;
			while ((ch=is.read())>=0){  //δ�����б�,�����
				info.append((char)ch); //������Ϣ
			}
			jtaShowFiles.setText(new String(info)); //��ʾ��Ϣ
			is.close(); //�ر�������
		}
		catch (IOException ex){
			JOptionPane.showMessageDialog(FtpClientDemo.this,ex.getMessage()); //��ʾ��ʾ��Ϣ
			ex.printStackTrace();  //�������д������������Ϣ
		}
		
	}
	
	public void disconnectServer(){
		try{
			ftpClient.closeServer(); //�ر��������������
		}
		catch (IOException ex){
			ex.printStackTrace();
		}	
	}
	
	public static void main(String[] args){
		new FtpClientDemo();	
	}
}