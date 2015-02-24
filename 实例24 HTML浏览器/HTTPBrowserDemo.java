import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

//html�����

public class HTTPBrowserDemo extends JFrame{
	JTextField jtfAddress; //����html�ļ���ַ����ַ
	JButton jbGo; //ת���ļ���ť
	JTextPane jtpShow; //��ʾ�ļ�
	JLabel jlInfo; //��ʾ��Ϣ

	public HTTPBrowserDemo(){
		super("html�����"); //���ø��๹�캯��
		jtfAddress=new JTextField(20); //ʵ������ַ�����
		jbGo=new JButton("ת��"); //ʵ����ת��ť
		jtpShow=new JTextPane(); //ʵ������ʾ���ݿ�
		jlInfo=new JLabel(); //ʵ������Ϣ��ʾ��ǩ
		
		JPanel panel=new JPanel(); //ʵ�������
		panel.add(new JLabel("��ַ")); //��������������
		panel.add(jtfAddress);
		panel.add(jbGo);
		JScrollPane jsp=new JScrollPane(jtpShow);	//ʵ������������
		Container container=getContentPane(); //�õ�����
		container.add(panel,BorderLayout.NORTH); //���������������
		container.add(jsp,BorderLayout.CENTER);
		container.add(jlInfo,BorderLayout.SOUTH);
		
		jbGo.addActionListener(new ShowHTMLListener());  //�¼�����,������ť���ʱ��ʾҳ������
		jtfAddress.addActionListener(new ShowHTMLListener());
		
		setSize(350,280);  //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 //�رմ���ʱ�˳�����
	}
	
	class ShowHTMLListener implements ActionListener {	 //��ʾҳ�������¼�����
		public void actionPerformed(ActionEvent event) {
			try{
				jlInfo.setText("��������...");  //��ʾ��ʾ��Ϣ
				URL address=new URL(jtfAddress.getText());  //�õ�HTMLҳ���URL��ַ
				jtpShow.setPage(address); //������ʾҳ��
				jlInfo.setText("���");
			}
			catch (Exception ex){
				jlInfo.setText("���ӳ���");
				ex.printStackTrace(); //���������Ϣ
			}
		}
	}
	
	public static void main(String[] args){
		new HTTPBrowserDemo();
	}
}