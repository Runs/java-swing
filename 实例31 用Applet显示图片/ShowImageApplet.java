import java.awt.*; 
import java.applet.*; 
import java.awt.image.ImageObserver; 
import java.net.URL; 

//��Applet��ʾͼƬ

public class ShowImageApplet extends Applet implements Runnable{ 
	Image[] images;  //Applet��Image����
	int xpoint=10;  //��ʾͼƬ��X����
	int ypoint=10;  //��ʾͼƬ��Y����
	Thread thread;  //ͼƬ�л����߳�
	int currentImage;  //��ǰ��ʾ��ͼƬ��

	public void init(){
		setBackground(Color.white); //���ñ���ɫ 
		setForeground(Color.blue); //����ǰ��ɫ 
		currentImage=0;	 //��ʼ������
		xpoint=10;	
		ypoint=10;
		
		images=new Image[5];
		MediaTracker tracker = new MediaTracker(this);  //ʵ����ý��װ����
		for (int i=0;i<images.length;i++){
			URL imgURL=getDocumentBase(); //·����html�����ļ�����ͬ
			images[i]=getImage(imgURL,"image"+i+".jpg");  //�õ�ͼ��
			tracker.addImage(images[i],i); //���Ӵ�װͼ��
		}
		
		try {
      		tracker.waitForID(0); //����ͼ��
    	}
    	catch(InterruptedException e) {}	
		
	} 
	
	public void start(){
      if(thread == null){
         thread =  new Thread(this);  //ʵ�����߳�
         thread.start(); //�����߳�
      }
   }
   
   public void run(){
      while(true){         
         try{
            Thread.sleep(1000); //�߳�����1000����
         }catch (InterruptedException e) {}
         repaint(); //�ػ���Ļ
      }      
   }
   
	public void paint(Graphics g){		
		g.drawImage(images[currentImage],xpoint,ypoint,this); //��ʾͼ��
		currentImage=(currentImage+1)%5;  //���ĵ�ǰ��ʾͼƬ��
	}
}

