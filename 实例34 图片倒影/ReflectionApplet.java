import java.applet.Applet;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

//ͼƬ��Ӱ

public class ReflectionApplet extends Applet implements Runnable {

	Thread lakeThread; //ͼƬ��Ӱ�߳�
	Graphics graphics; //��Applet��Graphics����
	Graphics waveGraphics; //��Ӱ��Graphics����
	Image image; //Applet��Image����
	Image waveImage; //��Ӱ��Graphics����
	int currentImage,imageWidth,imageHeight; 
	boolean imageLoaded; //ͼƬ�Ƿ�װ��
	String imageName; //ͼƬ����
	
	public void init() {
		imageName= getParameter("image"); //�õ�ͼƬ����
	}

	public void start() {
		if (lakeThread == null) {
			lakeThread= new Thread(this); //ʵ�����߳�
			lakeThread.start(); //�����߳�
		}
	}

	public void run() {
		currentImage= 0;
		if (!imageLoaded) { //���ͼƬδװ��
			repaint(); //�û���Ļ
			graphics= getGraphics(); //�õ�Graphics����
			MediaTracker mediatracker= new MediaTracker(this); //ʵ����MediaTracker����
			image= getImage(getDocumentBase(), imageName); //�õ�Imageʵ��
			mediatracker.addImage(image, 0); //���Ӵ����ص�ͼƬ
			try {
				mediatracker.waitForAll();  //װ��ͼƬ
				imageLoaded= !mediatracker.isErrorAny();  //�Ƿ��д�����
			} catch (InterruptedException ex) {
			}
			if (!imageLoaded) {  //����ͼƬʧ��
				stop(); //Appletֹͣ����
				graphics.drawString("����ͼƬ����", 10, 40); //���������Ϣ
				return;
			}
			imageWidth= image.getWidth(this); //�õ�ͼ����
			imageHeight= image.getHeight(this);  //�õ�ͼ��߶�
			createAnimation(); //��������Ч��
		}
		repaint();  //�ػ���Ļ
		try {
			while (true) {
				repaint(); //�û���Ļ
				currentImage++;
				if (currentImage == 12) 
					currentImage= 0;
				Thread.sleep(50); //�߳�����
			}
		} catch (InterruptedException ex) {
				stop();
		}
	}
	
	public void createAnimation() {
		Image img= createImage(imageWidth, imageHeight);  //��ͼ��߶ȴ���Imageʵ��
		Graphics g= img.getGraphics(); //�õ�Image�����Graphics����
		g.drawImage(image, 0, 0, this);  //����Image
		for (int i= 0; i < imageHeight ; i++) {
			g.copyArea(0,imageHeight-1-i,imageWidth,1,0,-imageHeight+1+(i*2)); //����ͼ������
		}

		waveImage= createImage(13 * imageWidth, imageHeight); //�õ�����Ч����Imageʵ��
		waveGraphics= waveImage.getGraphics(); //�õ�����Ч����Graphicsʵ��
		waveGraphics.drawImage(img, 12 * imageWidth, 0, this); //����ͼ��
		int j= 0;
		while (j<12){
			makeWaves(waveGraphics, j);
			j++;
		}
		g.drawImage(image, 0, 0, this);  //����ͼ��
	}

	public void makeWaves(Graphics g, int i) { //����Ч��ģ��
		double d= (6.2831853071795862 * i) / 12;
		int j= (12 - i) * imageWidth;
		int waveHeight=imageHeight / 16;
		for (int l= 0; l < imageHeight; l++) {			
			int k=(int) ((waveHeight* (l + 28)* Math.sin(waveHeight * (imageHeight - l)/ (l + 1)+ d))/imageHeight);
			if (l < -k)
				g.copyArea(12 * imageWidth, l, imageWidth, 1, -j, 0); //����ͼ������,�γɲ���
			else
				g.copyArea(12 * imageWidth, l + k, imageWidth, 1, -j, -k);
		}
	}

	public void paint(Graphics g) {
		update(g);
	}
	
	public void update(Graphics g){
		if (waveImage != null) {
			g.drawImage(waveImage,-currentImage * imageWidth,imageHeight-1,	this);  //����ͼ��
			g.drawImage(waveImage,(12 - currentImage) * imageWidth,	imageHeight-1,this);
		}
		g.drawImage(image, 0, 1, this);
	}
}
