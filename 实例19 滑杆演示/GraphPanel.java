import java.awt.*;
import javax.swing.*;

public class GraphPanel extends JPanel {
   int width=10;  //��Բ�߶�ֵ
   int hight=10;  //��Բ���ֵ

   public void paintComponent(Graphics g) {
      super.paintComponent(g);  //���ø��෽��
      g.fillOval(5,5,width,hight);  //������Բ
   }

   public void setW(int length){ //���ÿ��
      width=(length>=0?length:10 ); 
      repaint(); //�ػ����
   }
   
   public void setH(int length){ //���ø߶�
      hight=(length>=0?length:10 );  
      repaint();  //�ػ����
   }   
}  