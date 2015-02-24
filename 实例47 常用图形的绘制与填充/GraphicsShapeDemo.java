import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

// ����ͼ�εĻ��������
public class GraphicsShapeDemo extends JFrame { //��������

	GraphicsShapeDemo() {
		super("����ͼ�εĻ��������"); //���ø��๹�������ô��ڱ�����
		DrawPanel drawPanel = new DrawPanel(); //����DrawPanel�������ڻ���ͼ��
		Container content = getContentPane(); //��ô��ڵ����ݴ���
		content.add(drawPanel, BorderLayout.CENTER); //�Ѷ���drawPanel�������ݴ���
		setSize(400, 300); //���ô��ڴ�С
		setVisible(true); //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	public static void main(String[] args) {
		new GraphicsShapeDemo(); //����GraphicsShapeDemo����
	}
	
	//��ʾͼ�ε����
	class DrawPanel extends JPanel {

		//����paintComponent()����
		public void paintComponent(Graphics g) {
	
			super.paintComponent(g); //���ø���Ļ����������
			Graphics2D g2D = (Graphics2D)g;
			setBackground(Color.white);
			setForeground(Color.black);
			
			int charH = 16; //����ַ��߶�		
			int gridW = getWidth() / 5; //��ͼ������
			int gridH = getHeight() / 4; //��ͼ����߶�
			int posX = 2; //��ͼ�λ���λ�õ�x����
			int posY = 2; //��ͼ��λ�õ�y����
			int strY = gridH - 7; //�ַ�������λ�õ�y����
			int w = gridW - 2 * posX; //ͼ�εĿ��
			int h = strY - charH - posY; //ͼ�εĸ߶�
			int cirlceD = Math.min(w, h); //Բ��ֱ��
			
			Shape[][] shape = new Shape[2][5];			
			shape[0][0] = new Line2D.Float(0,0,w,h); //ֱ��
			shape[0][1] = new Rectangle2D.Double(0, 0, w, h); //����
			shape[0][2] = new RoundRectangle2D.Float(0, 0, w, h,20,20); //Բ�Ǿ���
			shape[0][3] = new Ellipse2D.Float(0,0,cirlceD, cirlceD); //Բ
			shape[0][4] = new Ellipse2D.Float(0, 0, w, h); //��Բ
			shape[1][0] = new Arc2D.Float(0,0,w,h,45,225, Arc2D.OPEN); //����
			shape[1][1] = new Arc2D.Float(0,0,w,h,45,225, Arc2D.CHORD); //����
			shape[1][2] = new Arc2D.Float(0,0,w,h,45,225, Arc2D.PIE); //����
			shape[1][3] = new QuadCurve2D.Double(0,0,w,h/6,w,h); //��������
			shape[1][4] = new CubicCurve2D.Double(0,0,w/2,h,w, h/2,w,h); //��������
			
			//���Ƽ���ͼ�ε�����
			String[][] shapeName = {{"ֱ��","����","Բ�Ǿ���","Բ","��Բ"},
				  {"����","����","����","��������","��������"}};

			AffineTransform defaultAT = g2D.getTransform();

			for(int i=0; i<shapeName.length; i++) {
				for(int j=0; j<shape[i].length; j++) {
					g2D.setColor(Color.black);
					g2D.translate(posX,posY);  //����ƽ��
					g2D.draw(shape[i][j]);
					g2D.setColor(Color.blue);
					g2D.drawString(shapeName[i][j], 2, strY); //����˵������
					g2D.setTransform(defaultAT);
					posY += gridH;

					g2D.translate(posX,posY);
					g2D.setColor(Color.pink);
					g2D.fill(shape[i][j]);
					g2D.setColor(Color.blue);
					g2D.drawString("���"+shapeName[i][j], 2, strY); //����
					g2D.setTransform(defaultAT);
					posX += gridW;
					posY -= gridH;
				}
				posX = 2;
				posY += 2*gridH;
			}
		}
	}
}
