import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import javax.swing.event.*;

//ͼ����ת��ʾ

public class RotateImageDemo extends JFrame{
	JSlider jSlider = new JSlider(JSlider.HORIZONTAL,0,180,0);  //�趨��ת�Ƕ�
	ImagePane imagePane=new ImagePane(); //����ͼ������
	
	public RotateImageDemo(){
		super("ͼ����ת��ʾ"); //���ø��๹�캯��
		jSlider.setPaintTicks(true);  //���Ʊ�־λ	
		jSlider.setMajorTickSpacing(45); //���ñ�־�ߴ�
		jSlider.setMinorTickSpacing(5);
		jSlider.setPaintLabels(true); //���Ƴ�����
		jSlider.setBorder(new javax.swing.border.TitledBorder(BorderFactory.createEmptyBorder(), "ͼ�����ת��")); //���ñ߿�
		jSlider.addChangeListener(new ChangeListener() { //������jSlider�¼�����
			public void stateChanged(ChangeEvent ce) {
				int value=((JSlider) ce.getSource()).getValue(); //��ȡ����ֵ
				double angle =(float)value/180*Math.PI ; //�õ���ת�Ƕȣ������ƣ�
				imagePane.ratoteImage((float)angle); //��תͼ��
			}
		});		
		
		Container container=getContentPane(); //�õ���������
		imagePane.loadImage("image0.jpg");	//װ��ͼƬ
		container.add(imagePane,BorderLayout.CENTER); //���������������
		container.add(jSlider,BorderLayout.SOUTH);
		
		setSize(250,250); //���ô��ڳߴ�
		setVisible(true); //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 //�رմ���ʱ�˳�����		
	}
	
	public static void main(String[] args){
		new RotateImageDemo();
	}
	
	class ImagePane extends JPanel {
		Image image;
		BufferedImage bufImage; //������ʾ�Ļ�����ͼ��
		BufferedImage originalBufImage; //ԭʼ������ͼ��
		Graphics2D bufImageG; //������ͼ���ͼ�λ���	
	
		public void loadImage(String fileName) {
			image = this.getToolkit().getImage(fileName); //ȡ��ͼ��
			MediaTracker mt = new MediaTracker(this); //ʵ����ý�������
			mt.addImage(image, 0); //����ͼ�񵽼�������
			try {
				mt.waitForAll(); //�ȴ�ͼƬ����
			} catch (Exception ex) {
				ex.printStackTrace();  //���������Ϣ
			}
			originalBufImage =	new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_ARGB); 	//����ԭʼ������ͼ��
			bufImage = originalBufImage;
			bufImageG = bufImage.createGraphics(); //����bufImage��ͼ�λ���
			bufImageG.drawImage(image, 0, 0, this); //����Դͼ�����ݵ�������ͼ����
			repaint(); //�ػ����
		}
	    //����ͼ��
	    public void ratoteImage(double angle) {
			if (bufImage == null)
				return; //���bufImageΪ����ֱ�ӷ���
			BufferedImage filteredBufImage =new BufferedImage(image.getWidth(this) ,image.getHeight(this),BufferedImage.TYPE_INT_ARGB); //���˺��ͼ��
			AffineTransform transform = new AffineTransform(); //����任����
			transform.rotate(angle,125,75); //��תͼ��
			AffineTransformOp imageOp = new AffineTransformOp(transform, null);//��������任��������			
			imageOp.filter(originalBufImage, filteredBufImage);//����ͼ��Ŀ��ͼ����filteredBufImage
			bufImage = filteredBufImage; //��������ʾ�Ļ�����ͼ��ָ����˺��ͼ��
			repaint(); //�ػ����
		}
		
	    //����������paintComponent()����
		public void paint(Graphics g) {
			super.paintComponent(g);
			if (bufImage != null) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(bufImage,0,0,this);	//����ͼƬ
			}
		}
	}
}
