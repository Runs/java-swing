import java.awt.*;

import javax.swing.*;


public class RoundButton extends JButton {

    public RoundButton(String label) {
        super(label);  //���ø��๹�캯��
        setContentAreaFilled(false);   //�����л��ư�ť����
    }


    //����Բ�ͱ�ǩ
    protected void paintComponent(Graphics g) {
       if (getModel().isArmed()) {  //�����ʱ
            g.setColor(Color.lightGray);  //��ɫΪ��ɫ
        } else {
            g.setColor(getBackground());  //�ð�ť��ɫ
        }
        g.fillOval(0, 0, getSize().width, getSize().height);  //����Բ
        super.paintComponent(g);  //���ø��ຯ��,�������ಿ��
    }


    //���Ʊ߿�
   protected void paintBorder(Graphics g) {
       g.setColor(getForeground());  //���ñ߿���ɫ
       g.drawOval(0, 0, getSize().width-1, getSize().height-1);  //�ڱ߽��ϻ���һ����Բ
   }

}

