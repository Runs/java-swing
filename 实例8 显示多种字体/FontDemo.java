import java.awt.*;
import javax.swing.*;

//��ʾ��������,��JLabelʵ��

public class FontDemo extends JFrame {

   public FontDemo()
   {
      super("��ʾ��������");  //���ø��๹�캯��

   	  Font[] fonts={new Font("Serif",Font.BOLD,12),
   	                new Font("Monospaced",Font.ITALIC,24),
   	                new Font("����",Font.PLAIN,18),
   	                new Font("����",Font.PLAIN,20),
   	                new Font("Serif",Font.BOLD + Font.ITALIC,18 )
   	  };  //��������
   	  String[] text={"Font Demo","Monospaced,б��,24��","������ʾ��","����","Serif�����壬б�壬18��"};  //��ʾ���ı�

      Container container=getContentPane();  //�õ�����
      Box boxLayout=Box.createVerticalBox();  //����һ����ֱ���е�Box
      boxLayout.setBorder(BorderFactory.createEmptyBorder(10,20,5,5));  //���ñ߽�
      container.add(boxLayout);  //���������������
      for (int i=0;i<5;i++){
      	JLabel fontLabel=new JLabel();  //�õ�һ��JLabel��ʵ��
      	fontLabel.setFont(fonts[i]);  //��������
      	fontLabel.setText(text[i]);  //������ʾ�ı�
      	boxLayout.add(fontLabel);  //���������Box��
      }

      setSize(380,180);  //���ô��ڳߴ�
      setVisible(true);  //���ô��ڿ�ub��
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
   }


   public static void main(String args[]){
      new FontDemo();
   }
}