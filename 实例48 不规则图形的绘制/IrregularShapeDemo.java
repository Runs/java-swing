import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

//������ͼ�εĻ���

public class IrregularShapeDemo extends JFrame {
	
	GeneralPath gPath= new GeneralPath(); //GeneralPath����ʵ��
	Point aPoint; 
	
    //���캯��
    public IrregularShapeDemo() {
		super("������ͼ�εĻ���"); //���ø��๹�캯��
		enableEvents(AWTEvent.MOUSE_EVENT_MASK|AWTEvent.MOUSE_MOTION_EVENT_MASK); //�����¼�
		
		setSize(300, 200); //���ô��ڳߴ�
		setVisible(true); //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}

	public void paint(Graphics g) { //���ش��������paint()����
		Graphics2D g2D = (Graphics2D)g;	//��ȡͼ�λ���
		g2D.draw(gPath); //����·��
	}

	public static void main(String[] args) {
		new IrregularShapeDemo();
	}
	
	protected void processMouseEvent(MouseEvent e) { //����¼�����
		if(e.getID() == MouseEvent.MOUSE_PRESSED) {
			aPoint = e.getPoint(); //�õ���ǰ����
			gPath = new GeneralPath(); //����ʵ����GeneralPath����
			gPath.moveTo(aPoint.x,aPoint.y); //����·����
		}
	}
		
	protected void processMouseMotionEvent(MouseEvent e) { //����˶��¼�����
		if(e.getID() == MouseEvent.MOUSE_DRAGGED) {
			aPoint = e.getPoint(); //�õ���ǰ����
			gPath.lineTo(aPoint.x, aPoint.y); //����·��
			gPath.moveTo(aPoint.x, aPoint.y);
			repaint(); //�ػ����
		}
	}	
}
