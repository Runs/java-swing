import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;

//������ȡ��ͼ��

public class GetImageDemo extends JFrame{
	JTextField jtfUrl;  //����ͼ���ַurl
	JButton jbGetImage;  //ȡͼ��ť
	Image image; //��ȡ��ͼ��
	Toolkit toolKit;  //Toolkit����,���ڻ�ȡͼ��
	
	public GetImageDemo(){
		super("������ȡ��ͼ��");  //���ø��๹�캯��
		
		Container container=getContentPane();	//�õ�����
		jtfUrl=new JTextField(18); //ʵ������ַ�����
		jbGetImage=new JButton("ȡͼ��");  //ʵ������ť
		container.setLayout(new FlowLayout()); //���ò��ֹ�����
		container.add(jtfUrl);  //���������������
		container.add(jbGetImage);
		toolKit=getToolkit(); //�õ����߰�
		
		jbGetImage.addActionListener(new ActionListener(){  //��ť�¼�����
			public void actionPerformed(ActionEvent ent){
				try{
					String urlStr=jtfUrl.getText();    //�õ�ͼ���URL��ַ
					URL url=new URL(urlStr);
					image=toolKit.getImage(url); //��ȡͼ��
					repaint(); //�ػ���Ļ
				}
				catch(MalformedURLException ex){
					ex.printStackTrace(); //���������Ϣ
				}
			}
		});
	
		setSize(320,160);  //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if (image!=null){
			g.drawImage(image,100,70,this); //������ϻ���ͼ��
		}
	}
	
	public static void main(String[] args){
		new GetImageDemo();
	}
}