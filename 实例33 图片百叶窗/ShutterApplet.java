import java.awt.*;
import java.applet.*;
import java.awt.image.*;

public class ShutterApplet extends Applet implements Runnable{
	Image images[],showImage; //����ʾ��ͼ�����鼰��ǰ��ʾ��ͼ��
	MediaTracker imageTracker; //ý��װ����
	int imageWidth,imageHeight,totalImage = 5,currentImage,nextImage; //ͼ����,�߶�,�ܵ�ͼ������,��ǰͼ���ż���һ��ͼ����
	Thread thread; //ͼƬ�л�Ч�����߳�
	int delay; //�л��ӳ�
	int totalPix,pix1[],pix2[],pix3[],pix4[],pix5[],pixA[],pixB[]; //ͼ������������ص�����
	
	public void init(){
		setBackground(Color.black);	//����Applet�ı�����ɫ
		images = new Image[totalImage]; //��ʼ������
		imageTracker = new MediaTracker(this); //ʵ����ý��װ����
		String param = new String(""); //�����ַ���
		for(int i=0; i<totalImage; i++)	{ //�õ�����ͼƬ
			param = getParameter("image" + i); //�õ�����
			images[i] = getImage(getCodeBase(),param); //�õ�ͼ��
			imageTracker.addImage(images[i],0); //����ͼ��ý��װ����
		}
		try {
			imageTracker.waitForID(0); //װ��ͼ��
		}
		catch(InterruptedException e){}		
		
		param=getParameter("delay"); //�õ��ӳٲ���
		if(param!= null){
			delay = Integer.parseInt(param);			
		}
		else{
			delay = 3000; //����Ĭ���ӳٲ���
		}
		
		imageWidth = images[0].getWidth(this); //�õ�ͼ����
		imageHeight = images[0].getHeight(this); //�õ�ͼ��߶�
		totalPix = imageWidth*imageHeight; //ͼ����ܵ����ص�����
		
		pix1 = new int[totalPix];
		PixelGrabber pg1 = new PixelGrabber(images[0],0,0,imageWidth,imageHeight,pix1,0,imageWidth); //��ȡͼ����������
		try{
			pg1.grabPixels(); //ץȡ����
		}
		catch(InterruptedException ex){}
		pix2 = new int[totalPix];
		PixelGrabber pg2 = new PixelGrabber(images[1],0,0,imageWidth,imageHeight,pix2,0,imageWidth);
		try{
			pg2.grabPixels();
		}
		catch(InterruptedException ex){}
		
		pix3 = new int[totalPix];
		PixelGrabber pg3 = new PixelGrabber(images[2],0,0,imageWidth,imageHeight,pix3,0,imageWidth);
		try	{
			pg3.grabPixels();
		}
		catch(InterruptedException ex){}
		
		pix4 = new int[totalPix];
		PixelGrabber pg4 = new PixelGrabber(images[3],0,0,imageWidth,imageHeight,pix4,0,imageWidth);
		try	{
			pg4.grabPixels();
		}
		catch(InterruptedException ex){}
		
		pix5 = new int[totalPix];
		PixelGrabber pg5 = new PixelGrabber(images[4],0,0,imageWidth,imageHeight,pix5,0,imageWidth);
		try	{
			pg5.grabPixels();
		}
		catch(InterruptedException ex){}
		
		currentImage = 0;
		pixA = new int[totalPix];
		pixB = new int[totalPix];
		showImage = images[0];	
	}
	
	public void start(){
    if(thread == null){
      thread = new Thread(this);  //ʵ�����߳�
      thread.start();  //�����߳�
    }
  }
  
	
	public void paint(Graphics g){
		g.drawImage(showImage,0,0,this); //���Ƶ�ǰͼ��
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	public void run(){
		while(true)
		{
			try
			{
				thread.sleep(delay); //�߳�����
				
				nextImage = ((currentImage+1)%totalImage); //������һ��ͼ����
				if (currentImage ==0){
					System.arraycopy(pix1,0,pixA,0,totalPix); //���鿽��
					System.arraycopy(pix2,0,pixB,0,totalPix);
					showImage = createImage(new MemoryImageSource(imageWidth,imageHeight,pixA,0,imageWidth)); //ת���������鵽ͼ��
					repaint(); //�ػ���Ļ
				}
				else if (currentImage ==1){
					System.arraycopy(pix2,0,pixA,0,totalPix);
					System.arraycopy(pix3,0,pixB,0,totalPix);
					showImage = createImage(new MemoryImageSource(imageWidth,imageHeight,pixA,0,imageWidth));
					repaint();
				}
				else if (currentImage ==2){
					System.arraycopy(pix3,0,pixA,0,totalPix);
					System.arraycopy(pix4,0,pixB,0,totalPix);
					showImage = createImage(new MemoryImageSource(imageWidth,imageHeight,pixA,0,imageWidth));
					repaint();
				}
				else if (currentImage ==3){
					System.arraycopy(pix4,0,pixA,0,totalPix);
					System.arraycopy(pix5,0,pixB,0,totalPix);
					showImage = createImage(new MemoryImageSource(imageWidth,imageHeight,pixA,0,imageWidth));
					repaint();
				}
				else if (currentImage ==4){
					System.arraycopy(pix5,0,pixA,0,totalPix);
					System.arraycopy(pix1,0,pixB,0,totalPix);
					showImage = createImage(new MemoryImageSource(imageWidth,imageHeight,pixA,0,imageWidth));
					repaint();
				}
				while(true)
				{
					for(int i=0; i<(int)(imageHeight/10);i++){
						try{
							thread.sleep(50); //�߳�����
							for(int j=0; j<imageHeight; j+=(int)(imageHeight/10)){
								for(int k=0; k<imageWidth; k++)	{
									pixA[imageWidth*(j+i) + k] = pixB[imageWidth*(j+i) + k]; 
								}
							}
						}
						catch(InterruptedException e){}
						showImage = createImage(new MemoryImageSource(imageWidth,imageHeight,pixA,0,imageWidth)); //���µ�ǰ��ʾImage
						repaint();						
					}
					break;
				}				
				currentImage = nextImage;
				repaint();
			}
			catch(InterruptedException e){}
		}
	}
}