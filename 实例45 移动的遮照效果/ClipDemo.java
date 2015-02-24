import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;

// �ƶ�������Ч��
public class ClipDemo extends JFrame {
		
	public ClipDemo() {		
		super("�ƶ�������Ч��"); //���ø��๹�캯��
		Container content = getContentPane(); //��ô��ڵ�����
		DrawPanel drawPanel = new DrawPanel(); //����DrawPanel�������ڻ���ͼ��
		content.add(drawPanel, BorderLayout.CENTER); //���������������		
		setSize(250, 160); //���ô��ڳߴ�
		setVisible(true); //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}

	public static void main(String[] args) {
		new ClipDemo();
	}
	
	//��ʾͼ�ε����
	class DrawPanel extends JPanel implements Runnable {
		Thread thread;	
		Image image = this.getToolkit().getImage("image0.jpg"); //��ȡͼ��
		BufferedImage bufImage; //������ͼ��
		Graphics2D bufImageG; //ͼ�λ���
		Ellipse2D aEllipse;  //�������յ���Բ
		int eW,eH;  //��Բ�Ŀ�Ⱥ͸߶�
		int imageW,imageH;  //ͼ��Ŀ�Ⱥ͸߶�
		int delay = 20;  //������ʾ���ӳ�ʱ��
		
		public DrawPanel() {
			enableEvents(AWTEvent.MOUSE_EVENT_MASK|AWTEvent.MOUSE_MOTION_EVENT_MASK); //������¼�
			MediaTracker mt = new MediaTracker(this); //ʵ����ý������� 
			mt.addImage(image, 0); //���Ӵ����ص�ͼ��
			try {
				mt.waitForAll(); //�ȴ�ͼ��ļ������
			} catch (Exception ex) {
				ex.printStackTrace(); //���������Ϣ
			}
			bufImage =new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_ARGB); //����������ͼ��
			bufImageG = bufImage.createGraphics(); //����������ͼ���ͼ�λ���
			imageW = image.getWidth(this); //��ȡͼ����
			imageH = image.getHeight(this); //��ȡͼ��߶�
			eW = imageW/4;
			eH = imageH/4;						
			startThread(); //��ʼ�����߳�
		}
		
		protected void processMouseEvent(MouseEvent e) {
			if(e.getID() == MouseEvent.MOUSE_EXITED) { //����˳�ʱ�����߳�
				startThread();
			}
		}
		
		public void processMouseMotionEvent(MouseEvent e) {
			if(e.getID() == MouseEvent.MOUSE_MOVED) {
				stopThread(); //ֹͣ�߳�����
				displayPicture(e.getX()-eW,e.getY()-eH); //����ͼƬЧ��
			}				
		}
		//��ʼ�߳�		
		public void startThread() {
			if(thread==null) {
				thread = new Thread(this);
				thread.start(); //�߳�����
			}
		}
		public void stopThread() {
	        if(thread != null)
	        {
	            thread.interrupt(); //�ж��߳�����
	            thread = null;
	        }			
		}
		//ʵ�ֽӿ�Runnable�ķ���
		public void run() {
			double x=Math.random()*3/4*imageW,y=Math.random()*3/4*imageH; //����˶��������
			double xi=Math.random()*imageW/64,yi=Math.random()*imageH/64;
			try {
				while(true) {
					x +=xi;
					y +=yi;
					if(x>(3*imageW/4)) {
						xi = -Math.random()*imageW/64;
						x += xi;
						yi = (Math.random()-0.5)*imageW/32;
					}
					if(x<0) {
						xi = Math.random()*imageW/64;
						x +=xi;
						yi = (Math.random()-0.5)*imageW/32;
					}
					
					if(y>(3*imageH/4)) {
						yi = -Math.random()*imageW/64;
						y +=yi;
						xi = (Math.random()-0.5)*imageW/32;
					}
					if(y<0) {
						yi = Math.random()*imageW/64;
						y +=yi;
						xi = (Math.random()-0.5)*imageW/32;
					}
					displayPicture(x,y); //��ʾͼƬ,���������ܷ�
					Thread.sleep(delay); //�߳�����
				}
			} catch(InterruptedException e) {}
		}
		
		//��ʾͼ�����յ���ʾλ��Ϊ(x,y)
		public void displayPicture(double x,double y) {
			aEllipse = new Ellipse2D.Double(x,y,eW,eH); //�õ���Բ��ʵ��
			bufImageG.setColor(Color.black); //���õ�ǰ��ɫ
			bufImageG.fillRect(0,0,imageW,imageH); //����������,���ǰ��
			bufImageG.setClip(aEllipse); //���ü�������
			bufImageG.drawImage(image, 0, 0, this); //��ͼ�λ������ƻ�����ͼ��
			repaint();
		}
		
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(bufImage, 0, 0, this); //��ͼ�λ������ƻ�����ͼ��
		}
	}
}
