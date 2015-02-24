import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//��ʾ���ַ��Ĵ���

public class LookAndFeelDemo extends JFrame {

   public LookAndFeelDemo(){
     super("���ַ��Ĵ���");  //���ø��๹�캯��

     Container container=getContentPane();  //�õ�����

	 JMenu menuTheme=new JMenu("���ڷ��");  //��ʼ���˵�
     JMenuItem itemNative=new JMenuItem("ϵͳƽ̨���");  //��ʼ���˵���
     JMenuItem itemMotif=new JMenuItem("Motif���");
     JMenuItem itemMetal=new JMenuItem("��ƽ̨���");
     menuTheme.add(itemNative);  //���Ӳ˵���
     menuTheme.add(itemMotif);
     menuTheme.add(itemMetal);
     itemNative.addActionListener(new ActionListener(){  //�˵����¼�����
     	public void actionPerformed(ActionEvent event){
     		changeLookAndFeel("Native");  //���÷���,�ı䴰�ڷ��
        }
     });
     itemMotif.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent event){
     		changeLookAndFeel("Motif");
        }
     });
     itemMetal.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent event){
     		changeLookAndFeel("Metal");
        }
     });

     JMenuBar menuBar=new JMenuBar();  //��ʼ���˵���
     menuBar.add(menuTheme);  //���Ӳ˵����˵���
     setJMenuBar(menuBar);  //���ò˵�

     JPanel panel=new JPanel();  //��ʼ��һ��JPanel
     panel.setBorder(BorderFactory.createTitledBorder("�����ʽ"));  //���ñ߽�
     panel.add(new JTextField("�ı���Look and feel���� "));  //���������panel��
     panel.add(new JCheckBox("����"));
     panel.add(new JCheckBox("б��"));
     panel.add(new JCheckBox("�»���"));
     panel.add(new JButton("ȷ��"));
     panel.add(new JButton("�˳�"));
     container.add(panel);  //����panel��������

     setSize(220,200);  //���ô��ڳߴ�
     setVisible(true);  //���ô��ڿɼ�
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
   }

   //�ı䴰����ʽ
   public void changeLookAndFeel(String type){
      try{
		 if (type.equals("Native")) {  //�ж��������ĸ��˵���
		 	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  //���ý�����ʽ
		 }
		 else if (type.equals("Motif")) {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		 }
		 else if (type.equals("Metal")) {UIManager.setLookAndFeel(
			UIManager.getCrossPlatformLookAndFeelClassName());
		 }
		 javax.swing.SwingUtilities.updateComponentTreeUI(this);  //���½���
	 }
	 catch(Exception ex){  //��׽����
       ex.printStackTrace();  //�������
     }
   }

   public static void main(String[] args){
      new LookAndFeelDemo();
   }
}