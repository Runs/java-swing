import java.awt.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.image.*;

// ��͸��ͼƬ

public class HalfTransparentImageDemo extends JFrame {
	private Container content = getContentPane(); //��ô��ڵ�����
 	private JSlider jSlider = new JSlider(JSlider.HORIZONTAL,0,100,75); //�ı�ͼ���͸����	
	DisplayPanel displayPanel = new DisplayPanel(); //��ʾͼ�����

	public HalfTransparentImageDemo() {
		super("��͸��ͼƬ"); //���ø��๹����
		jSlider.setPaintTicks(true);  //���Ʊ�־λ	
		jSlider.setMajorTickSpacing(25); //���ñ�־�ߴ�
		jSlider.setMinorTickSpacing(5);
		jSlider.setPaintLabels(true); //���Ƴ�����
		jSlider.setBorder(new TitledBorder(BorderFactory.createEmptyBorder(), "ͼ���͸����(%)")); //���ñ߿�
		jSlider.addChangeListener(new ChangeListener() { //������jSlider�¼�����
			public void stateChanged(ChangeEvent ce) {
				float alpha =((float) ((JSlider) ce.getSource()).getValue()) / 100;
				displayPanel.alpha = alpha; //�ı�ͼ���͸����
				displayPanel.repaint(); //�ػ�displayPanel
			}
		});
		content.add(jSlider, BorderLayout.SOUTH); //���������������
		content.add(displayPanel, BorderLayout.CENTER);
		setSize(300, 300); //���ô��ڳߴ�
		setVisible(true); //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
	public static void main(String[] args) {
		new HalfTransparentImageDemo();
	}
	
	//��ʾͼ�ε����
	class DisplayPanel extends JPanel {
		String[] imageName = { "back.jpg", "girl.gif" }; //ͼ���ļ�������
		BufferedImage bufImage1, bufImage2; //������ͼ��
		float alpha = 0.75f; // ͼ��ϳɵ�alpha����ֵ
		
		//����Image���󴴽�һ��������ͼ��
		public BufferedImage loadBufImage(String fileName) {
			Image image = getToolkit().getImage(fileName); //��ȡImage����
			MediaTracker mt = new MediaTracker(this); //ʵ����ý�������
			mt.addImage(image, 0); //���Ӵ�����Image����
			try {
				mt.waitForAll(); //�ȴ�ͼƬ����
			} catch (Exception e) {
				e.printStackTrace(); //���������Ϣ
			}					
			BufferedImage bufImage =new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_ARGB); //����������ͼ��	
     		Graphics bufImageG2D = bufImage.createGraphics(); //����������ͼ���ͼ�λ���	
			bufImageG2D.drawImage(image, 0, 0, this); //��ͼ�λ�������ͼ��
			return bufImage; //���ػ�����ͼ�����
		}
	
		public DisplayPanel() {
			bufImage1=loadBufImage(imageName[0]);  //����������ͼ��1
			bufImage2=loadBufImage(imageName[1]);  //����������ͼ��2
		}
	
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(bufImage1, 0, 0, this); //��ͼ�λ������ƻ�����ͼ��1
			g2d.drawString("Destination",5,20);  //��������			
			int compositeRule = AlphaComposite.SRC_OVER; //Դ�ų�Ŀ�귨�ϳɹ���			
			AlphaComposite alphaComposite=AlphaComposite.getInstance(compositeRule,alpha); //����AlphaComposite����
			g2d.setComposite(alphaComposite); //����ͼ�λ����ĺϳɷ�ʽ			
			g2d.drawImage(bufImage2, 0, 0, this); //��ͼ�λ������ƻ�����ͼ��2
			g2d.drawString("Source",150,20);  //��������
		}	
	}
}


