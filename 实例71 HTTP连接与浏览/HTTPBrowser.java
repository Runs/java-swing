import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;

//HTTP���������

public class HTTPBrowser extends JFrame{
	JTextField jtfAddress; //����html�ļ���ַ����ַ
	JTextPane jtpShow; //��ʾҳ��	
	JTextArea jtaSource; //��ʾHTMLԴ�ļ�

	public HTTPBrowser(){
		super("HTTP���������"); //���ø��๹�캯��
		jtfAddress=new JTextField(30); //ʵ������ַ�����
		jtpShow=new JTextPane(); //ʵ������ʾ���ݿ�
		jtaSource=new JTextArea(); 
		
		JPanel p1=new JPanel(); //ʵ�������
		JSplitPane spane=new JSplitPane(JSplitPane.VERTICAL_SPLIT); //ʵ�����ָ����
		p1.add(new JLabel("��ַ")); //��������������
		p1.add(jtfAddress);
		spane.add(new JScrollPane(jtpShow),JSplitPane.TOP);
		spane.add(new JScrollPane(jtaSource),JSplitPane.BOTTOM);
		spane.setDividerLocation(130); //���÷ָ�λ��
		spane.setDividerSize(2); //���÷ָ����ߴ�
		Container container=getContentPane(); //�õ�����
		container.add(p1,BorderLayout.NORTH); //���������������
		container.add(spane,BorderLayout.CENTER);
		
		jtfAddress.addActionListener(new ShowHTMLListener());  //�����ַ�ı����¼�����	
		
		setSize(380,300);  //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 //�رմ���ʱ�˳�����
	}
	
	class ShowHTMLListener implements ActionListener {	 
		public void actionPerformed(ActionEvent event) {
			try{
				URL address=new URL(jtfAddress.getText());  //�õ�HTMLҳ���URL��ַ
				jtpShow.setContentType("text/html"); //�������ݸ�ʽ
				jtpShow.setPage(address); //������ʾҳ��
				
				BufferedReader in= new BufferedReader(new InputStreamReader(address.openStream())); //��ȡ������
				String line;
				StringBuffer content = new StringBuffer(); //�ļ�����
				while ((line = in.readLine()) != null) { //��ȡ�ļ�
					content.append(line+"\n");
				}					
				jtaSource.setText(new String(content)); //������ʾ�ı�
				in.close(); //�ر�������
			}
			catch (Exception ex){
				ex.printStackTrace(); //���������Ϣ
			}
		}
	}
		
	public static void main(String[] args){
		new HTTPBrowser();
	}
}