import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

//������ȡ���ļ�

public class GetFileDemo extends JFrame{
	JTextField jtfUrl;  //�����ļ���ַurl
	JButton jbGetFile;  //ȡ�ļ���ť
	JLabel jlInfo; //��ʾ��ʾ��Ϣ
	
	public GetFileDemo(){
		super("������ȡ���ļ�");  //���ø��๹�캯��
		
		Container container=getContentPane();	//�õ�����
		jtfUrl=new JTextField(18); //ʵ������ַ�����
		jbGetFile=new JButton("ȡ�ļ�");  //ʵ������ť
		jlInfo=new JLabel(); 
		JPanel p=new JPanel();  //ʵ����һ�����,�������ɵ�ַ������ȡ�ļ���ť
		p.add(jtfUrl); //��������������
		p.add(jbGetFile);
		container.add(p,BorderLayout.NORTH);  //���������������
		container.add(jlInfo,BorderLayout.CENTER);
		
		jbGetFile.addActionListener(new ActionListener(){  //��ť�¼�����
			public void actionPerformed(ActionEvent ent){
				try{
					jlInfo.setText("���ڶ�ȡ");
					URL url=new URL(jtfUrl.getText());    //�õ��ļ���URL��ַ
					InputStream in=url.openStream();  //�õ��ļ�������
					String outFilename=JOptionPane.showInputDialog(GetFileDemo.this,"���뱣���ļ��� "); //���뱣����ļ���
					FileOutputStream out=new FileOutputStream(outFilename);  //�õ��ļ������
					byte[] buffer=new byte[1024]; //��������С
					int length;
					while ((length=in.read(buffer))!=-1){  //��ȡ����
						out.write(buffer,0,length);  //д�����ݵ��ļ�
					} 
					out.close(); //�ر��ļ������
					in.close();	 //�ر�������			 
					jlInfo.setText("��ȡ�ļ��ɹ�");  //��ʾ��ʾ��Ϣ
				}
				catch(Exception ex){
					ex.printStackTrace(); //���������Ϣ
					jlInfo.setText("��ȡ�ļ�ʧ��");
				}
			}
		});
	
		setSize(320,100);  //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}
	
	public static void main(String[] args){
		new GetFileDemo();
	}
}