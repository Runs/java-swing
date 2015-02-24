import java.applet.*;
import java.awt.*;
import java.net.*;

//ͼƬ����Ч��

public class FirePicApplet extends Applet implements Runnable{
  private Image backImage,foreImage; //ǰ���ͱ���Image����
  private Image image,fireImage; //Applet�ͻ��ƻ����Ч����Image����
  private Graphics graphics,fireGraphics; //Applet�ͻ��ƻ����Ч����Graphics����
  private Thread thread; //����Ч�����߳�
  private MediaTracker imageTracker;  //װ��ͼƬ
  private int height,width; //Applet�ĸ߶�,���
  
  public void init() {
  	Dimension dim=getSize(); //�õ�Applet�ĳߴ�   
    width = dim.width; //�õ����ֵ
    height = dim.height; //�õ��߶�ֵ
    backImage = getImage(getDocumentBase(),"back.jpg");  //�õ�ͼƬ
    foreImage = getImage(getDocumentBase(),"image1.gif");  
    imageTracker = new MediaTracker(this); //ʵ����MediaTracker����
    imageTracker.addImage(backImage,0); //����ͼƬ��ͼƬװ����
    imageTracker.addImage(foreImage,0);
    
    try{
      imageTracker.waitForID(0); //װ��ͼƬ
    }
    catch(InterruptedException e){}
    
    image = createImage(width,height); //�õ�Image����ʵ��
    graphics = image.getGraphics();  //�õ�Graphics����ʵ��
    
    fireImage=createImage(width*2,height*2);
    fireGraphics=fireImage.getGraphics();       
  }
  
  public void start(){
    if(thread == null){
      thread = new Thread(this);  //ʵ�����߳�
      thread.start();  //�����߳�
    }
  }
  
  
  
  public void run(){
    int x= 0, y=0;  //���ƻ���Ч����X����,Y����  
    int tileWidth = backImage.getWidth(this);  //����ͼƬ�Ŀ��
    int tileHeight= backImage.getHeight(this); //����ͼƬ�ĸ߶�
    while(thread != null) {
		x=fireImage.getWidth(this)-width; //�õ�X����,Y����ֵ
		y=fireImage.getHeight(this)-height;
	    for(;(x>0)&&(y>0); x--,y--)	{
	      if((x==0)||(y==0)) { //����ص���ʱ,��������
	        x=fireImage.getWidth(this)-width;
	        y=fireImage.getHeight(this)-height;
	      }
	      
	      for(int j=0; j < fireImage.getHeight(this); j = j + tileHeight){
	        for(int i=0; i < fireImage.getWidth(this); i = i + tileWidth){
	          fireGraphics.drawImage(backImage, i, j, this); //���Ʊ���ͼƬ
	        }       
	      }   
	      
	      fireGraphics.drawImage(foreImage, x, y,width,height,this); //����ǰ��ͼƬ
	      graphics.drawImage(fireImage,-x,-y,this);  //���ƻ���Ч��Image
	      repaint(); //�ػ���Ļ
	    }      
	 }
  }
  
  public void update(Graphics g){
    paint(g);
  }
  
  public void paint(Graphics g){
    g.drawImage(image,0,0,this); //����Image
  }
}