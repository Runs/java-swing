import java.awt.*;
import java.applet.*;

public class FlyTextApplet extends Applet implements Runnable{
   
   Image image; //��Applet��Image����
   Graphics graphics; //��Applet��Graphics����
   Font font; //��ʾ����
   String message; //��ʾ�ı�
   Thread thread; //�����˶��߳�
   int xpos, ypos, fontsize; //X����,Y���꼰�����С
   
   public void init(){   
      image = createImage(getSize().width, getSize().height);   //�õ�Imageʵ��
      graphics = image.getGraphics();  //�õ�Graphicsʵ��
      message = getParameter("Text"); //�õ��ı�����
      if(message == null){ //�����ʾ�ı�Ϊ��
         message = "��������"; //����Ĭ���ı�
       }
      font = new Font("TimesRoman", Font.BOLD, 10);  //ʵ��������
   }
   
   public void start(){
      if(thread == null){
         thread =  new Thread(this);  //ʵ�����߳�
         thread.start(); //�����߳�
      }
   }
   
   public void run(){
      while(thread != null){         
         if(fontsize >getSize().height) //�������ߴ����
            fontsize = 0; //��������ߴ�
         try{
            Thread.sleep(50); //�߳�����
         }catch (InterruptedException e) {}
         repaint(); //�ػ���Ļ
      }      
   }
   
   public void stop(){
   		thread=null;
   }
   
   public void update(Graphics g){
   	graphics.setColor(Color.black); //���õ�ǰ��ɫ
      graphics.fillRect(0,0,getSize().width, getSize().height); //��䱳��
      font = new Font("TimesRoman", Font.BOLD, fontsize); //�õ�����ʵ��
      graphics.setFont(font); //��������
      graphics.setColor(Color.pink); //���õ�ǰ��ɫ
      FontMetrics fontMetrics = graphics.getFontMetrics(font); //�õ������FontMetrics����
      int fontheight = fontMetrics.getHeight(); //�õ�����߶�
      int width; //������
      int baseline = getSize().height / 2 + fontheight / 2;  //��ʾ�ı�����
      
      width = fontMetrics.stringWidth(message);  //�ַ������   
      width = (getSize().width - width) / 2;  //��ʾ�ַ������
      
      graphics.drawString(message, width, baseline-=20);   //�����ַ���
      g.drawImage(image,0,0, this); //����Image����
      fontsize++;  //��������ߴ�      
   }
   
   public void paint(Graphics g){
      update(g);
   }  

}