	import java.awt.*;

	import javax.swing.*;

	public class BorderLayoutDemo extends JFrame{

	   public static void main( String args[] ){  //���캯��
	      Container container = getContentPane();  //�õ�����
	      container.setLayout( new BorderLayout() ); //���ò��ֹ�����ΪBorderlayout

	      container.add(new JButton("North"), BorderLayout.NORTH);  //���Ӱ�ť
	      container.add(new JButton("South"), BorderLayout.SOUTH);
	      container.add(new JButton("East"), BorderLayout.EAST);
	      container.add(new JButton("West"), BorderLayout.WEST);
	      container.add(new JButton("Center"), BorderLayout.CENTER);

	      setTitle("BorderLayout ��ʾ");  //���ô��ڱ���
	      setSize(280,200);  //���������ڳߴ�
	      setVisible(true);  //���������ڿ���
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	   }

	   public static void main( String args[] ){
	     new BorderLayoutDemo();
	   }
	}