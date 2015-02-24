import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//������ʾ

public class JSliderDemo extends JFrame {
   JSlider xSlider;  //���ڸı���Բ���ֵ
   JSlider ySlider;  //���ڸı���Բ�߶�ֵ
   GraphPanel panel; //��Բ�����

   public JSliderDemo() 
   {
      super("������ʾ");  //���ø��๹�캯��
      panel=new GraphPanel();  //ʵ�������
      panel.setBackground(Color.orange); //������屳��ɫΪ��ɫ

      xSlider=new JSlider( SwingConstants.HORIZONTAL,0,200,10); //ʵ��������
      xSlider.setMajorTickSpacing(10); //���ÿ̶�ֵ
      xSlider.setPaintTicks(true); //���̶�
      ySlider=new JSlider( SwingConstants.VERTICAL,0,200,10); 
      ySlider.setMajorTickSpacing(10);
      ySlider.setPaintTicks(true);
      ySlider.setInverted(true);  //�����϶�����
      ValueChangeListener myListener=new ValueChangeListener(); //ʵ���������¼�����
      xSlider.addChangeListener(myListener); //���ӻ��˵��¼�����
      ySlider.addChangeListener(myListener);

      Container container=getContentPane(); //�õ�����
      container.add(xSlider,BorderLayout.SOUTH); //���������������
      container.add(ySlider,BorderLayout.EAST);
      container.add(panel,BorderLayout.CENTER);

      setSize(220,200);  //���ô��ڳߴ�
      setVisible(true);  //���ô���Ϊ����
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );  //�رմ���ʱ�˳�����
   }
   
   class ValueChangeListener implements ChangeListener{  //�¼�����
   	  public void stateChanged(ChangeEvent e){
   	  	if (e.getSource()==xSlider){ //�ж��¼�Դ
            panel.setW(xSlider.getValue()); //������Բ���
        }
        else{
        	panel.setH(ySlider.getValue()); //������Բ�߶�
        }
      }
   }

   public static void main(String args[]) {
      new JSliderDemo();
   }

}  