import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//�ļ�ѡ������ʾ

public class JFileChooserDemo extends JFrame {
	private JFileChooser chooser;  //�ļ�ѡ����
	private JButton button;  //ѡ���ļ���ť
	private JComboBox comboBox;  //�����趨�ļ��Ի�������(�򿪻��Ǳ����ļ�)

	public JFileChooserDemo() {
		super("JFileChooser ��ʾ");  //���ø��๹�캯��
		Container contentPane = getContentPane();  //�õ�����
		contentPane.setLayout(new FlowLayout());  //���ò��ֹ�����ΪFlowlayout
        chooser=new JFileChooser();  //��ʼ���ļ�ѡ����
        button = new JButton("ѡ���ļ�");  //��ʼ����ť
        comboBox=new JComboBox();  //��ʼ����Ͽ�
        comboBox.addItem("��");  //������Ͽ��б�����
        comboBox.addItem("����");
        contentPane.add(comboBox);  //�������������
		contentPane.add(button);

		button.addActionListener(new ActionListener() {  //��ť�¼�����
		    public void actionPerformed(ActionEvent e) {
			    int state;  //�ļ�ѡ��������״̬
			    chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());  //��ȥ�����ļ�������
			    chooser.addChoosableFileFilter(new MyFileFilter("gif","ͼ���ļ�"));   //�����ļ�������,�Ӱ�gif�ļ�

			    if (comboBox.getSelectedIndex()==0)  //��Ͽ�Ϊ"��"
			       state=chooser.showOpenDialog(null);   //��ʾ���ļ��Ի���
			    else
			       state=chooser.showSaveDialog(null);  //��ʾ�����ļ��Ի���
			    File file = chooser.getSelectedFile();  //�õ�ѡ����ļ�

			    if(file != null && state == JFileChooser.APPROVE_OPTION) {  //ѡ�����ļ�������˴򿪿ɱ��水ť
			       JOptionPane.showMessageDialog(null, file.getPath()); //��ʾ��ʾ��Ϣ
			    }
			    else if(state == JFileChooser.CANCEL_OPTION) {  //����˳�����ť
			      JOptionPane.showMessageDialog(null, "�˳�!");  //��ʾ��ʾ��Ϣ
			    }
			    else if(state == JFileChooser.ERROR_OPTION) {
			      JOptionPane.showMessageDialog(null, "����!");  //��ʾ��ʾ��Ϣ
			    }
		}
		});

		this.setSize(200,100);  //���ô��ڴ�С
		this.setVisible(true);  //���ô��ڿɼ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}

	public static void main(String args[]) {
		new JFileChooserDemo();
	}
}
