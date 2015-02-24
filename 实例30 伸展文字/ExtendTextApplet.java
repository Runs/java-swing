import java.awt.*;
import java.applet.*;

//��չ����

public class ExtendTextApplet extends Applet implements Runnable{
	Image image; //��Applet��Image����
	Graphics graphics;  //��Applet��Graphics����
	int appletWidth,appletHeight; //Applet�ĸ߶�,���
	String message; //��ʾ�ı�
	Thread thread; //�����߳�
	int ypoint=0, xheight; //��ʾ�ı���Y����͸߶�
	int phase = 0; //״̬����
	Font font; //��ʾ�ı�������
	
	public void init(){
		font=new Font("TimesRoman",Font.BOLD,30); //ʵ��������
		appletWidth = getSize().width; //�õ�Applet�Ŀ��
		appletHeight = getSize().height;  //�õ�Applet�ĸ߶�
		xheight = appletHeight / 3; //��ʾ���ָ߶�ֵ
		ypoint = xheight; //��ʾ���ֵ�Y����ֵ
		message = getParameter("Text"); //�õ���ʾ��Ϣ
		if(message==null)
			message = "��չ����"; //����Ĭ����Ϣ
		image = createImage(appletWidth,appletHeight);  //�õ�Imageʵ��
		graphics = image.getGraphics();  //�õ�Graphicsʵ��
	}  
  
	public void start(){
		if(thread == null){
			thread = new Thread(this);  //ʵ�����߳�
			thread.start();  //�����߳�
		}
	}     
   
	public void update(Graphics g){
		paint(g); //������Ļ
	}
   
	public void paint(Graphics g){
		g.drawImage(image, 0, ypoint, appletWidth, xheight ,this);  //����Image����
	}
   
	public void run()	{
		try{
			while(true){ 
				ypoint = 0; 
				xheight = appletHeight;
				graphics.setColor(Color.white); //���õ�ǰ��ɫ
				graphics.fillRect(0 ,0, appletWidth, appletHeight); //��䱳��
				repaint(); //�ػ���Ļ
				thread.sleep(100); //�߳�����100����
				if(phase==0){ 
					graphics.setColor(Color.orange); //���õ�ǰ��ɫ
					for(int i = appletWidth; i>=0; i--)	{ //��������չЧ��
						graphics.fillRect(i, appletHeight / 3 ,appletWidth, appletHeight /10);  //������
						repaint(); //�ػ���Ļ
						thread.sleep(10); //�߳�����10����
					}
				}
				else if(phase == 1){
					graphics.setColor(Color.pink); //���õ�ǰ��ɫ
					for(int i = 0; i<=appletWidth; i++)	{ 
						graphics.fillRect(0, appletHeight / 3 ,i, appletHeight /10); //������
						repaint(); //�ػ���Ļ
						thread.sleep(10); //�߳�����10����
					}
				}
				ypoint = appletHeight/3; 
				xheight = appletHeight/3;
				for(int i = appletHeight / 3; i>=0; i--){
					ypoint--;
					xheight += 2;
					if(phase == 0){
						graphics.setColor(Color.orange); //���õ�ǰ��ɫ
						graphics.fillRect(0,0, appletWidth,appletHeight); //������
						graphics.setFont(font); //���õ�ǰ����
						graphics.setColor(Color.white);  //���õ�ǰ��ɫ
						graphics.drawString(message,0,35);  //�����ַ���
						phase++; //�ı�״̬����
					}
					else if(phase == 1){
						graphics.setColor(Color.pink); //���õ�ǰ��ɫ
						graphics.fillRect(0,0, appletWidth,appletHeight); //������
						graphics.setFont(font); //���õ�ǰ����
						graphics.setColor(Color.black);  //���õ�ǰ��ɫ
						graphics.drawString(message,0,35); //�����ַ���          
						phase=0; //�ı�״̬����
					}				
					repaint(); //�ػ���Ļ
					thread.sleep(100); //�߳�����100����
				}
				thread.sleep(2000);  //�߳�����2500����       
			}
		}
		catch(InterruptedException e){}
	}	
}