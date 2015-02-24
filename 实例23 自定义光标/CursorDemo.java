import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//�Զ�����

public class CursorDemo extends JFrame{
	
	public CursorDemo(){
		super("�Զ�����");  //���ø��๹�캯��		
		int[] cursor={Cursor.DEFAULT_CURSOR,Cursor.HAND_CURSOR,Cursor.MOVE_CURSOR,Cursor.N_RESIZE_CURSOR,Cursor.W_RESIZE_CURSOR,Cursor.WAIT_CURSOR,Cursor.TEXT_CURSOR};	 //Ԥ����������	
		
		Container container=getContentPane(); //�õ�����
		container.setLayout(new FlowLayout()); //�����������ֹ�����
		for (int i=0;i<cursor.length;i++){ 
			JTextArea jta=new JTextArea(3,6); //ʵ����һ���ı������
			jta.setCursor(Cursor.getPredefinedCursor(cursor[i])); //�����ı���Ĺ��
			container.add(jta); //���������������
		}
				
		Toolkit toolkit=Toolkit.getDefaultToolkit(); //�õ�Ĭ�ϵ�ToolKit����
		Image image=toolkit.getImage("cursor.gif"); //�õ�ͼ��
		Cursor customCursor=toolkit.createCustomCursor(image,new Point(6,6),"MyCursor"); //ʵ�����Զ��������
		JTextArea jta=new JTextArea(3,6); //ʵ�����ı���
		jta.setCursor(customCursor);  //�����ı�����
		container.add(jta);	 //�������
		 
		setSize(300,160); //���ô��ڴ�С
		setVisible(true);  //���ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
	
	public static void main(String[] args){
		new CursorDemo();
	}
}