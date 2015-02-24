import javax.microedition.lcdui.*;

public class BallCanvas extends javax.microedition.lcdui.Canvas implements Runnable{
	static java.util.Random random = new java.util.Random();
	
	int posX=5, posY=5; //С����ʾλ��
	int ballSize = 10; //С��ߴ�
	Display display; //��ʾ��
	
	public BallCanvas(Display display){  //���캯��
		super();
		this.display=display;
		
	}	
	
	public void run() { //�̵߳�������
		while (true){
			this.posX = (random.nextInt()>>>1) % (this.getWidth()-20) + 10;  //����С��λ��X����
			this.posY = (random.nextInt()>>>1) % (this.getHeight()-20) + 10;  //����Y����
			try {
				Thread.sleep(100);  //�߳�����
			} catch (InterruptedException e) {}
			repaint(); //�ػ���Ļ
		}
	}
	
	void start() {
	    display.setCurrent(this); //���õ�ǰ��Ļ
	    Thread t = new Thread(this);
		t.start(); //��ʼ�߳����� 	    
		repaint();
	}
	
	void destroy() {
	}	
	
	protected void paint(Graphics g) {
		int x = g.getClipX(); //��ȡ������λ��
        int y = g.getClipY();
        int w = g.getClipWidth(); //��ȡ��������С
        int h = g.getClipHeight();
		g.setColor(0xffffff); //���õ�ǰ��ɫ
		g.fillRect(x, y, w, h); //����������
		g.setColor(0); 
		g.fillArc(posX, posY, ballSize, ballSize, 0, 360); //����С��
	}
	
}