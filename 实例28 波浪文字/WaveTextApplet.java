import java.awt.*;
import java.applet.*;

//��������

public class WaveTextApplet extends Applet implements Runnable {

  String message;  //��ʾ�ı�
  int direct,phase; //�˶��������
  Thread thread; //�����˶��߳�
  char words[]; //��ʾ�ı����ַ�����
  Image image;  //Image����
  Graphics graphics; //Graphics����
  Color colors[]; //��ʾ�ı���ɫ
  private Font font; //��ʾ����
  private FontMetrics fontMetric; //��ʾ����� FontMetrics����

   public void init() {   
     direct=1;  //��ʼ����ֵ
     phase = 0; 
     message = getParameter("Text"); //������ʾ�ı�
     if (message==null){ //����ı�Ϊ��
     	message="��������"; //����Ĭ���ı�
     }
     setBackground(Color.black); //���ñ���ɫ
     
     words =  new char [message.length()]; //��ʼ����ʾ�ַ�����
     message.getChars(0,message.length(),words,0); 
     image = createImage(getSize().width,getSize().height); //�õ�Imageʵ��
     graphics = image.getGraphics(); //�õ�Graphicsʵ��
 
     font = new Font("TimesRoman",Font.BOLD,36); //������ʾ����
     fontMetric=getFontMetrics(font);  //�õ������FontMetric����
     graphics.setFont(font);  //������ʾ����
     
     float h;
     colors = new Color[message.length()]; //��ʼ����ɫ����
     for (int i = 0; i < message.length(); i++) {
        h = ((float)i)/((float)message.length());
        colors[i] = new Color(Color.HSBtoRGB(h,1.0f,1.0f)); //�����ɫ��������
     }

   }



   public void start() {
     if(thread == null) {
       thread = new Thread(this); //ʵ�����߳�
       thread.start(); //�����߳�
     }
   }


  public void run() {
      while (thread != null) {
         try {
            Thread.sleep(200); //�߳�����
         }catch (InterruptedException e) {
         }
	 		repaint(); //�ػ���Ļ
      }
   }


   public void update(Graphics g) {
      int x, y; //��ʾ�ַ���X����,Y����
      double ang; 
      int Hrad = 12;
  		int Vrad = 12;

      graphics.setColor(Color.black); //���õ�ǰ��ɫ
      graphics.fillRect(0,0,getSize().width,getSize().height); //��䱳��
      phase+=direct; 
      phase%=8;
      for(int i=0;i<message.length();i++) {
         ang = ((phase-i*direct)%8)/4.0*Math.PI; //�˶��Ƕ�
         x = 20+fontMetric.getMaxAdvance()*i+(int)(Math.cos(ang)*Hrad); //�ַ���X����
         y = 60+  (int) (Math.sin(ang)*Vrad); //�ַ���Y����
         graphics.setColor(colors[(phase+i)%message.length()]); //�����ı���ɫ
         graphics.drawChars(words,i,1,x,y); //��ʾ�ַ�
      }
      g.drawImage(image,0,0,this); //����Image
   }

  public void paint(Graphics g) {
   update(g);
  }
}