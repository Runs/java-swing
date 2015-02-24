import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class RoundButtonDemo extends JFrame{

	private int clickCount=0;  //��¼��ť�ĵ������
	private JButton button1;
	private JButton button2;

	public RoundButtonDemo()
	{
		button1 = new RoundButton("����һ��Բ�ΰ�ť");  //��ʼ����ťһ

        Dimension dim=button1.getPreferredSize();  //�õ���ťһ����ѳߴ�
        double maxsize=Math.max(dim.getHeight(),dim.getWidth());  //�õ������е����ֵ
        dim.setSize(maxsize,maxsize);  //���ĳ���Ϊ�����е����ֵ
        button1.setPreferredSize(dim);  //������ѳߴ�

        button2 = new RoundButton("�����: "+clickCount+" ��");  //��ʼ����ť��
        button1.setBackground(Color.blue);  //���ð�ť�ı�����ɫ
        button2.setBackground(Color.pink);
        getContentPane().add(button1);  //�������
        getContentPane().add(button2);
        getContentPane().setLayout(new FlowLayout());  //���ò��ֹ�����

        button2.addActionListener(new ActionListener(){  //�ť�����¼�����
            public void actionPerformed(ActionEvent e){
                clickCount++;  //����һ�ε����
                button2.setText("�����: "+clickCount+" ��");  //�������ð�ť���ı�ǩ
            }
        });

        setSize(300, 200);  //���ô��ڳߴ�
        setVisible(true);  //���ô��ڿ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}

    public static void main(String[] args) {
		new RoundButtonDemo();
    }
}