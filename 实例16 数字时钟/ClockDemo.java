import java.awt.*;
import java.util.*;
import javax.swing.*;

//����ʱ��
public class ClockDemo extends JFrame implements Runnable{
	Thread clock;	
	
	public ClockDemo(){
		super("����ʱ��");	//���ø��๹�캯��	
		setFont(new Font("Times New Roman",Font.BOLD,60));	//����ʱ�ӵ���ʾ����
		start(); //��ʼ����
		setSize(280,100);  //���ô��ڳߴ�
		setVisible(true);  //���ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}
	
	public void start(){ //��ʼ����
		if (clock==null){ //�������Ϊ��ֵ
			clock=new Thread(this); //ʵ��������
			clock.start(); //��ʼ����
		}
	}
	
	public void run(){  //���н���
		while (clock!=null){ 
			repaint(); //����paint�����ػ����
			try{
				Thread.sleep(1000);  //�߳���ͣһ��(1000����)
			}
			catch (InterruptedException ex){
				ex.printStackTrace();  //���������Ϣ
			}
		}	
	}
	
	public void stop(){  //ֹͣ����
		clock=null;
	}
	
	public void paint(Graphics g){  //���������paint����
		Graphics2D g2=(Graphics2D)g;  //�õ�Graphics2D����
		Calendar now=new GregorianCalendar(); //ʵ������������
		String timeInfo=""; //�����Ϣ
		int hour=now.get(Calendar.HOUR_OF_DAY); //�õ�Сʱ��
		int minute=now.get(Calendar.MINUTE);   //�õ�����
		int second=now.get(Calendar.SECOND);  //�õ�����
		
		if (hour<=9) 
			timeInfo+="0"+hour+":"; //��ʽ�����
		else 
			timeInfo+=hour+":";
		if (minute<=9)
			timeInfo+="0"+minute+":";
		else
			timeInfo+=minute+":";
		if (second<=9)
			timeInfo+="0"+second;
		else
			timeInfo+=second;

		g.setColor(Color.white);  //���õ�ǰ��ɫΪ��ɫ
		Dimension dim=getSize();  //�õ����ڳߴ�
		g.fillRect(0,0,dim.width,dim.height);  //��䱳��ɫΪ��ɫ
		g.setColor(Color.orange);  //���õ�ǰ��ɫΪ��ɫ
		g.drawString(timeInfo,20,80);  //��ʾʱ���ַ���
	}

	public static void main(String[] args){
		new ClockDemo();
	}
}