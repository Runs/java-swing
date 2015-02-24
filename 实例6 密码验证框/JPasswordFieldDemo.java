import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JPasswordFieldDemo extends JFrame {
	JTextField username;  //�û��������
   JPasswordField password;  //���������
  	JButton logonButton;  //��¼��ť
  	JButton cancelButton;  //�˳���ť

   public JPasswordFieldDemo() {  //���캯��

   	super("JPasswordField��ʾ");  //���ø��๹�캯��
   	Container container=getContentPane();  //�õ�����
   	container.setLayout(new GridLayout(3, 2, 2, 2));  //���ò��ֹ�����

   	username=new JTextField(16);  //��ʼ���ı������,���Ϊ16��
   	password=new JPasswordField(16);  //��ʼ�����������,���Ϊ16��
   	logonButton=new JButton("��¼");  //��ʼ����¼��ť
   	logonButton.addActionListener(  //��¼��ť�¼�����
   		new ActionListener(){
      	public void actionPerformed(ActionEvent evt){
      		char[] pw=password.getPassword();  //�õ�����
         	String message="�����û�����"+username.getText()+"\n�������룺"+new String(pw);  //��Ϣ�ַ���
   			JOptionPane.showMessageDialog(JPasswordFieldDemo.this, message);  //��ʾ��Ϣ
       }
    	});
	   	cancelButton=new JButton("�˳�");  //��ʼ���˳���ť
	   	cancelButton.addActionListener(  //��ʼ����ť�¼�����
	   		new ActionListener(){
	      	public void actionPerformed(ActionEvent evt){
	            System.exit(0);  //�˳�����
	       }
	    });

	   container.add(new JLabel("      �û���:"));  //�������
	   container.add(username);
	   container.add(new JLabel("      ����:"));
	  	container.add(password);
	  	container.add(logonButton);
	  	container.add(cancelButton);
	  	setResizable(false);  //�������û��ı䴰�ڴ�С
	  	setSize(300,120);  //���ô��ڳߴ�
	  	setVisible(true);  //���ô��ڿ���
	  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
 	}

 	public static void main(String[] args) {
   		new JPasswordFieldDemo();
  	}
}
