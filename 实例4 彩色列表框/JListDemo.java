import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

//��ɫ�б��ʾ��

public class JListDemo extends JFrame{
	Container container;  //����
	JTextField selectedText;  //�ı���,��ӳѡ�����ɫֵ
	JList list;  //�б��
	JPanel selectedColor;  //Panel,��ѡ�����ɫΪ��������

   public JListDemo(){  //���캯��
   	container=getContentPane();  //�õ�����
   	container.setLayout(new BorderLayout());  //���ò��ֹ�����,���Ǳ����,ContainerĬ��ΪBorderLayout

   	Color[] colors={Color.orange,Color.pink,Color.red,Color.black,Color.blue,Color.cyan,Color.green,Color.lightGray};  //�б������
   	list=new JList(colors);
		JScrollPane scrollPane = new JScrollPane(list);  //��list��ʼ����������
		selectedText=new JTextField(20);
		selectedColor=new JPanel();
		selectedColor.setPreferredSize(new Dimension(20,20));  //����panel����ѡ�ߴ�
		container.add(selectedText,BorderLayout.NORTH);  //���������������
   	container.add(scrollPane,BorderLayout.CENTER);
   	container.add(selectedColor,BorderLayout.SOUTH);

      list.setCellRenderer(new ColorRenderer());  //����Renderer
      list.addListSelectionListener(  //�¼�����
      	new ListSelectionListener(){
      	   public void valueChanged(ListSelectionEvent event){  //ѡ��ֵ�иı�
      	   	Color c=(Color)list.getSelectedValue();  //�õ�ѡ�����ɫ
      	      selectedText.setText("ѡ����ɫ��"+" R="+c.getRed()+" G ="+c.getGreen()+" B="+c.getBlue());  //�����ı����ı�
      	      selectedColor.setBackground(c);  //����panel����ɫ
      	   }
      });

   	setSize(300,200);  //���ô��ڳߴ�
   	setVisible(true);  //���ô��ڿ���
   	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
   }


   public static void main(String[] args){
      new JListDemo();
   }
}