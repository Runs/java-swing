	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;

	public class JColorChooserDemo extends JFrame {
	   private Container container;  //����
	   private JPanel colorPanel; //���ڷ�ӳ��ɫ�仯�����

	   public JColorChooserDemo() {  //���캯��
	      super( "��ɫ����ʾ" );  //����JFrame�Ĺ��캯��
	      container = getContentPane();  //�õ�����
	      colorPanel=new JPanel();  //��ʼ�����

	      JButton selectColorButton = new JButton( "ѡȡ��ɫ" );  //��ʼ����ɫѡ��ť
	      selectColorButton.addActionListener(  //Ϊ��ɫѡ��ť�����¼�����
	         new ActionListener() {
	            public void actionPerformed( ActionEvent event )
	            {
	            	JColorChooser chooser=new JColorChooser();	//ʵ������ɫѡ����
	               Color color=chooser.showDialog(JColorChooserDemo.this,"ѡȡ��ɫ",Color.lightGray );  //�õ�ѡ�����ɫ
	               if (color==null)  //���δѡȡ
	                  color=Color.gray;  //��������ɫΪ��ɫ
	               colorPanel.setBackground(color);  //�ı����ı���ɫ
				}

	      });
	      container.add(selectColorButton,BorderLayout.NORTH);  //�������
	      container.add(colorPanel,BorderLayout.CENTER);  //�������
	      setSize( 400, 130 );  //���ô��ڳߴ�
	      setVisible(true);  //���ô��ڿɼ�
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );  //�رմ���ʱ�˳�����
	   }

	   public static void main(String args[]) {
	      new JColorChooserDemo();
	   }
	}