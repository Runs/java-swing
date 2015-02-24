import java.awt.*;
import java.applet.*;
import java.io.*;
import java.awt.image.*;

//ͼƬ����

public class TurnApplet extends Applet implements Runnable{
  Image images[],showImage;  //ͼ�����鼰��ǰ��ʾͼ��
  MediaTracker imageTracker;  //����ͼ���MediaTracker����
  int imageWidth,imageHeight,currentImage,totalImage; //ͼ����,�߶�,��ǰͼ����,ͼ���ܵĸ���
  Thread thread; //ͼƬ�����߳�
  int delay;  //�����ӳ�
  Image image;  //����ͼ���Image����
  Graphics graphics;  //����ͼ���Grahpics����
  
  public void init(){
  	totalImage = 5; //��ʼ������
  	currentImage = 0;
    setBackground(Color.white);  //���ñ�����ɫ   
    images = new Image[totalImage]; 
    imageTracker = new MediaTracker(this);   //�õ�MediaTrackerʵ��
    String param ;
    for(int i=0; i<totalImage; i++) {
      param = getParameter("image" + (i+1)); //�õ�����
      images[i] = getImage(getCodeBase(),param); //�õ�ͼ��
      imageTracker.addImage(images[i],0); //����ͼƬ����������
    }
    try {
      imageTracker.waitForID(0); //����ͼ��
    }
    catch(InterruptedException e) {}
    
    param=getParameter("delay");  //�õ��ӳٲ���
    if(param== null){
      delay = 3000; //����Ĭ�ϲ���
    }
    else {
      delay = Integer.parseInt(param);
    }    
    imageWidth = images[0].getWidth(this);  //�õ�ͼ����
    imageHeight = images[0].getHeight(this);    //�õ�ͼ��߶� 
    image = createImage(imageWidth,imageHeight); //����Imageʵ��
    graphics = image.getGraphics();  //�õ�Graphicsʵ��
  }
  
  public void start() {
    if(thread == null) {
      thread = new Thread(this);  //ʵ�����߳�
      thread.start(); //�����߳�
    }
  }
  public void paint(Graphics g) {
    g.drawImage(image,0,0,this); //����ͼ��
  }
  
  public void update(Graphics g) {
    paint(g);
  }
    
  public void run() {
    while(thread != null) {
      try{     
        for(int i=0; i<=(imageHeight/2); i++) {  //ʵ��ͼƬ�ķ���Ч��
          thread.sleep(30);  //�߳�����,ʵ��ͼ����𽥷�ת
          graphics.setColor(Color.white); //���õ�ǰ��ɫ
          graphics.fillRect(0,0,imageWidth,imageHeight); //����������
          graphics.drawImage(images[currentImage],0,i,imageWidth,imageHeight-2*i,this); //�Բ�ͬ�߶Ȼ���ͼƬ
          repaint(); //�ػ���Ļ
        }
        
        currentImage = ((currentImage+1)%totalImage); //���ĵ�ǰͼ����ֵ
        
        for(int i=0; i<=(imageHeight/2); i++){ //ʵ��ͼƬ�ķ�����Ч��
          thread.sleep(30);
          graphics.setColor(Color.white);
          graphics.fillRect(0,0,imageWidth,imageHeight);
          graphics.drawImage(images[currentImage],0,(imageHeight/2)-i,imageWidth,2*i,this);
          repaint();
        }
         thread.sleep(delay);  //�߳�����         
      }
      catch(InterruptedException e){}
    }
  }
}