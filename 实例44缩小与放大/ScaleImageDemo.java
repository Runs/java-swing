import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.color.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;

// ͼ����С��Ŵ���ʾ

public class ScaleImageDemo extends JFrame {
	private JPanel panel = new JPanel(); //���panel��������ͼ��Ŵ���С����ԭ��ť
	private JButton jbFile = new JButton("��ͼ���ļ�"); //��ͼ���ļ���ť
	private JButton jbZoomIn = new JButton("�Ŵ�"); //ͼ��Ŵ�ť
	private JButton jbZoomOut = new JButton("��С"); //ͼ����С��ť
	private JButton jbReset = new JButton("��ԭ"); //ͼ��ԭ��ť
	ScalePane showImagePane = new ScalePane(); //����showImagePane�������ڻ���ͼ��
	Container content = getContentPane(); //��ô��ڵ�����

    //���캯��
	public ScaleImageDemo() {
		super("ͼ�����С��Ŵ�"); //���ø��๹�������ô��ڱ�����
		//Ϊ��ť��Ӷ���������
		jbFile.addActionListener(new ButtonActionListener());
		jbZoomIn.addActionListener(new ButtonActionListener());
		jbZoomOut.addActionListener(new ButtonActionListener());
		jbReset.addActionListener(new ButtonActionListener());		

		//��ͼ��Ŵ�ť��ͼ����С��ť��ͼ��ԭ��ť����panel���
		panel.add(jbZoomIn);
		panel.add(jbZoomOut);
		panel.add(jbReset);
		//��showImagePane�ļ�ѡ����Ͽ򡢿�����塢״̬����ǩ���뵽�������ݴ���
		content.add(showImagePane, BorderLayout.CENTER);
		content.add(jbFile, BorderLayout.NORTH);
		content.add(panel, BorderLayout.SOUTH);		
		
		setSize(260, 200); //���ô��ڴ�С
		setVisible(true); //���ô��ڿɼ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
    //ʹ���ļ�ѡ��������ͼ��
	public void fileSelect() {
		JFileChooser chooser = new JFileChooser(); //ʵ�����ļ�ѡ����
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//ģʽΪ�����ļ�
		chooser.setCurrentDirectory(new File(".")); //�����ļ�ѡ������ǰĿ¼
		//����ͼ���ļ�������
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			public boolean accept(File file) {  //�ɽ��ܵ��ļ�����
				String name = file.getName().toLowerCase(); //��ȡ�ļ���
				return name.endsWith(".gif")
					|| name.endsWith(".jpg")
					|| name.endsWith(".jpeg")
					|| file.isDirectory();
			}
			public String getDescription() {
				return "ͼ���ļ�"; //�ļ�����
			}
		});
		int result = chooser.showOpenDialog(this); //��ʾ�ļ�ѡ��Ի���
		if (result == JFileChooser.APPROVE_OPTION) { //�õ��û���Ϊ
			String fileName = chooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ����ļ���
			showImagePane.loadImage(fileName); //����ͼ����ʾ
		}		
	}
	
	public static void main(String[] args) {
		new ScaleImageDemo();
	}
	
	//��ť�¼�������
	class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			if(button==jbFile) {
				fileSelect();			
			} else if(button==jbZoomIn) {
				showImagePane.scaleX *= 1.25; //ͼ��x�᷽��Ŵ�����
				showImagePane.scaleY *= 1.25; //ͼ��y�᷽��Ŵ�����
				showImagePane.applyFilter(); //����ͼ��
				showImagePane.repaint(); //�ػ�showImagePane���	
				jbReset.setEnabled(true);				
			} else if(button==jbZoomOut) {
				showImagePane.scaleX *= 0.8; //ͼ��x�᷽����С����
				showImagePane.scaleY *= 0.8; //ͼ��y�᷽����С����
				showImagePane.applyFilter(); //����ͼ��
				showImagePane.repaint(); //�ػ�showImagePane���
				jbReset.setEnabled(true);
			} else if(button==jbReset) {
				showImagePane.scaleX = 1.0; //ͼ��x�᷽��Ŵ����ӻ�ԭΪ1.0
				showImagePane.scaleY = 1.0; //ͼ��y�᷽��Ŵ����ӻ�ԭΪ1.0
				showImagePane.applyFilter(); //����ͼ��
				showImagePane.repaint(); //�ػ�showImagePane���
				jbReset.setEnabled(false);
			}
		}
	}
		
	//��ʾͼ������
	class ScalePane extends JPanel {
		Image image;
		BufferedImage bufImage; //������ʾ�Ļ�����ͼ��
		BufferedImage originalBufImage; //ԭʼ������ͼ��
		Graphics2D bufImageG; //������ͼ���ͼ�λ���	
		double scaleX = 1.0; //ͼ��ˮƽ�������������
		double scaleY = 1.0; //ͼ����ֱ�������������
	
		//����ͼ��
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
	    public void applyFilter() {
			if (bufImage == null)
				return; //���bufImageΪ����ֱ�ӷ���
			BufferedImage filteredBufImage =new BufferedImage((int) (image.getWidth(this) * scaleX),(int) (image.getHeight(this) * scaleY),BufferedImage.TYPE_INT_ARGB); //���˺��ͼ��
			AffineTransform transform = new AffineTransform(); //����任����
			transform.setToScale(scaleX, scaleY); //���÷���任�ı�������	
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
				g2.drawImage(bufImage,(this.getWidth() - bufImage.getWidth()) / 2,(this.getHeight() - bufImage.getHeight()) / 2,this);	//����ͼƬ
			}
		}
	}
}

