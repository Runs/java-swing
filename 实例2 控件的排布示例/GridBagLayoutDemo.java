	import java.awt.*;
	import javax.swing.*;

	public class GridBagLayoutDemo extends JFrame {

	   public GridBagLayoutDemo() {  //���캯��
	    Container contentPane = getContentPane();  //�õ�����
	    contentPane.setLayout(new GridBagLayout());  //���ò��ֹ�����

	    JLabel labelName=new JLabel("����");  //������ǩ
	    JLabel labelSex=new JLabel("�Ա�");  //�Ա��ǩ
	    JLabel labelAddress=new JLabel("סַ");  //סַ��ǩ
	    JTextField textFieldName = new JTextField();  //�����ı���
	    JTextField textFieldAddress = new JTextField(); //��ַ�ı���
	    JComboBox comboBoxSex = new JComboBox();  //�Ա���Ͽ�
	    comboBoxSex.addItem("��");   //����ѡ����
	    comboBoxSex.addItem("Ů");
	    JButton buttonConfirm=new JButton("ȷ��");  //ȷ����ť
	    JButton buttonCancel=new JButton("�˳�");  //�˳���ť

	    //���Ӹ������
	    contentPane.add(labelName,         new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
	    contentPane.add(textFieldName,      new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
	    contentPane.add(comboBoxSex,           new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0
	            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
	    contentPane.add(labelSex,        new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
	    contentPane.add(buttonConfirm,      new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 3, 0), 0, 0));
	    contentPane.add(buttonCancel,     new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 0, 3, 0), 0, 0));
	    contentPane.add(labelAddress,     new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
	    contentPane.add(textFieldAddress,     new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0
	            ,GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));

	    setTitle("GridBagLayout ��ʾ");  //���ô��ڱ���
	    setSize(300,140);  //���ô��ڳߴ�
	    setVisible(true);  //���ô��ڿɼ�
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	   }

	   public static void main(String args[])   {
	      new GridBagLayoutDemo();
	   }
	}