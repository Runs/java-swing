import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;

// ͼ���ģ������

public class BlurAndSharpenDemo extends JFrame implements ActionListener {
	private JPanel jPanel = new JPanel(); //���jPanel��������ģ�����񻯡���ԭͼ��ť
	private JButton buttonFile; //��ͼ���ļ���ť
	private JButton buttonBlur; //ģ��ͼ��ť
	private JButton buttonSharpen; //��ͼ��ť
	private JButton buttonReset; //��ԭͼ��ť
	ImagePanel imagePanel = new ImagePanel(); //����ImagePanel�������ڻ���ͼ��

	public BlurAndSharpenDemo() {
		super("ͼ���ģ��������ʾ"); //���ø��๹�캯��
		Container contentPane = getContentPane(); //�õ�����
		buttonFile = new JButton("��ͼ���ļ�"); //ʵ�������
		buttonFile.addActionListener(this); //�����¼�����		
		buttonBlur = new JButton("ģ��ͼ��");
		buttonBlur.addActionListener(this);		
		buttonSharpen = new JButton("��ͼ��");
		buttonSharpen.addActionListener(this);		
		buttonReset = new JButton("��ԭͼ��");
		buttonReset.addActionListener(this);
		
		jPanel.add(buttonBlur);  //��������������
		jPanel.add(buttonSharpen);
		jPanel.add(buttonReset);
		contentPane.add(jPanel, BorderLayout.SOUTH); //���������������
		contentPane.add(buttonFile, BorderLayout.NORTH);
		contentPane.add(imagePanel, BorderLayout.CENTER);
		//���ô���
		this.setSize(280, 230); //���ô��ڴ�С
		this.setVisible(true); //���ô��ڿɼ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
	public void actionPerformed(ActionEvent e) {
		 JButton button = (JButton)e.getSource(); //��ȡ�¼�Դ		 
		//��ͼ���ļ���ťbuttonFile�¼�����		 
		if(button==this.buttonFile) {
			JFileChooser chooser = new JFileChooser(); //ʵ�����ļ�ѡ����
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //�����ļ���ģʽΪ�����ļ�
			chooser.setCurrentDirectory(new File(".")); //�����ļ�ѡ������ǰĿ¼
			//����ͼ���ļ�������
			chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) { //�ɽ��ܵ��ļ�����
					String name = file.getName().toLowerCase();
					return name.endsWith(".gif")
						|| name.endsWith(".jpg")
						|| name.endsWith(".jpeg")
						|| file.isDirectory();
				}
				public String getDescription() { //�ļ�����
					return "ͼ���ļ�";
				}
			});
			int result = chooser.showOpenDialog(this); //��ʾ�ļ�ѡ��Ի���
			if (result == JFileChooser.APPROVE_OPTION) { //�õ��û���Ϊ
				String fileName = chooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ����ļ���
				imagePanel.loadImage(fileName); //����ͼ����ʾ
			}		 	
		}
		//ģ��ͼ��ťbuttonBlur�¼�����
		else if(button==this.buttonBlur) {
			imagePanel.blur(); //ģ��ͼ��
			buttonReset.setEnabled(true); //���û�ԭͼ��ť����
		}
		//��ͼ��ťbuttonSharpen�¼�����		
		else if(button==this.buttonSharpen) {
			imagePanel.sharpen(); //��ͼ��
			buttonReset.setEnabled(true); //���û�ԭͼ��ť����
		}
		//��ԭͼ��ťbuttonBlur�¼�����
		else if(button==this.buttonReset) {
			imagePanel.reset(); //��ԭͼ��
			buttonReset.setEnabled(false); //���û�ԭͼ��ť������
		}		
	}

	public static void main(String[] args) {
		new BlurAndSharpenDemo();
	}
	
	public class ImagePanel extends JPanel {
		Image image;  //�������ͼ��
		BufferedImage bufImage;  //������ʾ�Ļ�����ͼ��
		BufferedImage originalBufImage;  //ԭʼ������ͼ��
		Graphics2D g2D;  //ͼ�λ���
	
		//����ͼ��
		public void loadImage(String fileName) {
			image = this.getToolkit().getImage(fileName);  //��ȡͼ��
			MediaTracker mt = new MediaTracker(this); //ʵ����ý�������
			mt.addImage(image, 0); //���Ӵ�����ͼ��ý�������
			try {
				mt.waitForAll(); //�ȴ�����ͼ��ļ������
			} catch (Exception ex) { 
				ex.printStackTrace(); //���������Ϣ
			}
			//����ԭʼ������ͼ��
			originalBufImage = new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_ARGB);
			g2D = originalBufImage.createGraphics();  //����������ͼ���ͼ�λ���
			g2D.drawImage(image, 0, 0, this); //����Դͼ�����ݵ�������ͼ����
			bufImage = originalBufImage;
			repaint();  //�ػ����
		}
	    //����ͼ��
	    public void applyFilter(float[] data) {
			if (bufImage == null)
				return; //���bufImageΪ����ֱ�ӷ���
			Kernel kernel = new Kernel(3, 3, data);  
			ConvolveOp imageOp=new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP, null);	//��������任��������
			BufferedImage filteredBufImage = new BufferedImage(image.getWidth(this),image.getHeight(this),BufferedImage.TYPE_INT_ARGB);	//���˺�Ļ�����ͼ��
			imageOp.filter(bufImage, filteredBufImage);//����ͼ��Ŀ��ͼ����filteredBufImage
			bufImage = filteredBufImage; //��������ʾ�Ļ�����ͼ��ָ����˺��ͼ��
			repaint(); //�ػ����
	
		}
		//ģ��ͼ��
	 	public void blur() {
			if (bufImage == null)
				return;
			float[] data = {
					0.0625f, 0.125f, 0.0625f,
					0.125f,	0.025f, 0.125f,
					0.0625f, 0.125f, 0.0625f 
			};
			applyFilter(data);
		}
		//��ͼ��
		public void sharpen() {
			if (bufImage == null)
				return;
			float[] data = { 
			        -1.0f, -1.0f, -1.0f,
			        -1.0f, 9.0f, -1.0f,
			        -1.0f, -1.0f, -1.0f 
			};
			applyFilter(data);
		}
		//�ָ�ͼ��
		public void reset() {
			if (bufImage == null)
				return;
			bufImage = originalBufImage;  //
			g2D.drawImage(image, 0, 0, this);
			repaint();  //����paint()�����ػ����
		}
	
		public void paint(Graphics g) {
			super.paintComponent(g);
			//���bufImage�ǿգ���������ϻ�����
			if (bufImage != null) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(bufImage,(this.getWidth() - bufImage.getWidth()) / 2,	(this.getHeight() - bufImage.getHeight()) / 2,this);
			}
		}
	}
}
