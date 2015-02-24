import java.awt.*;
import java.io.*;
import javax.swing.*;

//�洢���ȡ����
public class ObjectDemo extends JFrame{	
	public ObjectDemo(){
		super("�洢���ȡ����");  //���ø��๹�캯��		
		Container container=getContentPane();  //�õ�����
		Icon imageIcon=new ImageIcon("image.gif");  //ʵ����һ��ͼ��
		writeIcon(imageIcon);	//��ͼ��д�뵽�ļ���(�洢�����ļ�)
		Icon readIcon=(Icon)readIcon();	//���ļ��ж�ȡ����
		container.add(new JLabel(readIcon)); //���Ӷ���������
		
		setSize(300,200); //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
	public void writeIcon(Object obj){  //д������ļ��ķ���
		try{
			FileOutputStream fos=new FileOutputStream("label.obj"); //�õ��ļ������
			ObjectOutputStream oos=new ObjectOutputStream(fos); //�õ����������
			oos.writeObject(obj); //д�����
			oos.close(); //�رն��������
			fos.close(); //�ر��ļ������
			System.out.println("д������ļ�"); //�������д��������ʾ��Ϣ
		}
		catch (Exception ex){
			ex.printStackTrace(); //�������д������������Ϣ
		}
	}
	
	public Object readIcon(){ //���ļ��ж�ȡ����
		Object obj=null; 
		try{
			FileInputStream fis=new FileInputStream("label.obj"); //�õ��ļ�������
			ObjectInputStream ois=new ObjectInputStream(fis); //�õ�����������
			obj=ois.readObject(); //��ȡ����
			ois.close(); //�رն���������
			fis.close(); //�ر��ļ�������
			System.out.println("���ļ��ж�ȡ����"); //�����ʾ��Ϣ
		}
		catch (Exception ex){
			ex.printStackTrace();
		}		
		return obj;
	}
	
	public static void main(String[] args){
		new ObjectDemo();	
	}
}