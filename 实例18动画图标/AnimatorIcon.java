import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//����ͼ��

public class AnimatorIcon extends JPanel implements ActionListener {

	ImageIcon[] images; //���ڶ�����ͼ������
	Timer animationTimer; 
	int currentImage = 0; //��ǰͼ����
	int delay = 500;  //ͼ���л��ӳ�
	int width; //ͼ����
	int height; //ͼ��߶�

	public AnimatorIcon() //���캯��
	{
		setBackground(Color.white);
		images = new ImageIcon[2]; //��ʼ������
		for (int i=0;i<images.length;i++)
			images[i]=new ImageIcon(getClass().getResource("image"+i+".gif")); //ʵ����ͼ��
		width = images[0].getIconWidth(); //��ʼ�����ֵ
		height = images[0].getIconHeight(); //��ʼ���߶�ֵ
	}

	public void paintComponent(Graphics g) { //����������Ʒ���
		super.paintComponent(g); //���ø��ຯ��
		images[currentImage].paintIcon(this,g,70,0); //����ͼ��
		currentImage=(currentImage+1)%2; //���ĵ�ǰͼ����
	}

	public void actionPerformed(ActionEvent actionEvent) {
		repaint();
	}

	public void startAnimation() { //��ʼ����
		if (animationTimer==null) {
			currentImage=0; 
			animationTimer=new Timer(delay, this);  //ʵ����Timer����
			animationTimer.start(); //��ʼ����
		} else if (!animationTimer.isRunning()) //���û������
			animationTimer.restart(); //��������
	}

	public void stopAnimation() { 
		animationTimer.stop();  //ֹͣ����
	}

	public static void main(String args[]) {
		AnimatorIcon animation = new AnimatorIcon(); //ʵ��������ͼ��
		JFrame frame = new JFrame("����ͼ��"); //ʵ�������ڶ���
		frame.getContentPane().add(animation);  //���������������
		frame.setSize(200, 100); //���ô��ڳߴ�
		frame.setVisible(true); //���ô��ڿ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
		animation.startAnimation(); //��ʼ����
	}

}