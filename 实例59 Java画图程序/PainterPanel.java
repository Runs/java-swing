import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PainterPanel extends JPanel implements MouseListener{
	int shape=-1; //ͼ������
	Point[] point=new Point[2]; //��¼����϶�����ʼ����յ�

	public PainterPanel(){
		super();	//���ø��๹�캯��		
		this.setBackground(Color.white); //���ñ�����ɫ
	   point[0]=new Point(-1,-1); //��ʼ������
	   point[1]=new Point(-1,-1);
		addMouseListener(this); //��������¼�
	}
	

	public void mouseReleased(MouseEvent e){ //����ͷ��¼�
		point[1]=new Point(e.getX(),e.getY());	 //�����յ�λ��
		repaint(); //�ػ���Ļ
	}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){  //��갴��ʱ�¼�
		point[0]=new Point(e.getX(),e.getY());  //������ʼ��λ��
	}   

	
	public void paint(Graphics g){	
		super.paint(g);
		switch (shape){  //����shapeֵ����ͼ��
			case 0:
				g.drawLine(point[0].x,point[0].y,point[1].x,point[1].y); //����
				break;
			case 1:
				int width=point[1].x-point[0].x;
				int height=point[1].y-point[0].y;
				g.drawOval(point[0].x,point[0].y,width,height); //����Բ
				break;
			case 2:
				width=point[1].x-point[0].x;
				height=point[1].y-point[0].y;
				g.drawRect(point[0].x,point[0].y,width,height);  //�����
				break;	
		}		
	}
	
	public void drawShape(int shape){
		this.shape=shape;		
	}
}
