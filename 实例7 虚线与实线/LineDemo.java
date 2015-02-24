import java.awt.*;
import javax.swing.*;

public class LineDemo extends JFrame{

	public LineDemo(){
		super("ʵ��������"); //���ø��๹�캯��		
		setSize(300,200); //���ô��ڳߴ�
		setVisible(true); //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
	public void paint(Graphics g){ //�����������
		Graphics2D g2=(Graphics2D)g; //�õ�2Dͼ��
		Dimension dim = this.getSize(); //�õ�����ߴ�
    	g2.setColor(Color.white); //���û�����ɫΪ��ɫ
    	g2.fillRect(0, 0, dim.width, dim.height); //����������
    	g2.setColor(Color.black); //���û�����ɫ
		g2.drawLine(40,160,280,160); //����ʵ��
		g2.drawLine(40,160,40,40);
		g2.drawString("0",30,165); //�����ַ���
		g2.drawString("100",16,50);
		g2.drawString("200",270,175);
		
		float[] dash={5,5}; //�̻���ͼ��
		BasicStroke bs = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f,dash,0.0f); //ʵ�����»�ˢ
		g2.setStroke(bs); //�����µĻ�ˢ		

		g2.drawLine(40,160,100,120); //���µĻ�ˢ��������
		g2.drawLine(100,120,160,120);
		g2.drawLine(160,120,280,40);

	}

	public static void main(String[] args){
		new LineDemo();
	} 
}