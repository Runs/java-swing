import java.applet.Applet;
import java.awt.*;

public class Text3DApplet extends Applet implements Runnable {

	Image image; //�������ֵ�Image����
	Graphics graphics; //�������ֵ�Graphics����
	Thread thread;  //��ʾ��ά�����߳�
	int width,height; //��ʾ��ȡ��߶�
	String message; //��ʾ��Ϣ
	int fontSize; //���ֳߴ�
	Font font; //����

	public void init() {
		Dimension dim=getSize(); //�õ�Applet�ĳߴ�
		width = dim.width; //�õ����
		height = dim.height; //�õ��߶�
		image = createImage(width, height); //�õ�Imageʵ��
		graphics= image.getGraphics(); //�õ�Grahpicsʵ��
		message = getParameter("text"); //��HTML�ļ��еõ���ʾ��Ϣ
		if (message == null) { //�����ϢΪ��
			message="��ά����"; //����Ĭ����Ϣ
		}
		fontSize = 30; //���������С
	}
	

	public void start() { 
		if (thread == null) {
			thread = new Thread(this);  //ʵ�����߳�
			thread.start(); //�����߳�
		}
	}

	public void run() { //�߳���������
		while (thread != null) {				
				try {
					Thread.sleep(50L); //�߳�����
				} catch (InterruptedException ex) {
				}
				repaint(); //�ػ���Ļ
			}
	}

	public void update(Graphics g) {		
		font = new Font("TimesRoman", 1, fontSize); //�õ�����ʵ��
		graphics.setFont(font);  //������ʾ����
		int j = (int) (255 * Math.random()); //����,�������ɽ�����ɫ
		int k = (int) (255 * Math.random());
		int l = (int) (255 * Math.random());
		try {
			Thread.sleep(2000); //�߳�����
		} catch (InterruptedException ex) {
		}
		graphics.setColor(Color.orange); //���õ�ǰ��ɫ
		graphics.fillRect(0, 0, width, height); //��䱳��
		for (int i = 0; i < 6; i++) { //��ά���
			graphics.setColor( //���ý�����ɫ
				new Color(
					255 - ((255 - j) * i) / 10,
					255 - ((255 - k) * i) / 10,
					255 - ((255 - l) * i) / 10));
			graphics.drawString(message, 15 - i, height - 15-i); //�����ַ���
		}
		g.drawImage(image, 0, 0, this); //����Image����Ļ
	}

	public void paint(Graphics g) {
		update(g);
	}
}
