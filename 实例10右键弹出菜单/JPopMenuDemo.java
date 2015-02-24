import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//��������˵�

public class JPopMenuDemo extends JFrame {
   JRadioButtonMenuItem items[]; //�˵���
   Color[] colors={Color.blue,Color.pink,Color.yellow,Color.red,Color.orange}; //��ɫ����
   JPopupMenu popupMenu; //�����˵�

   public JPopMenuDemo()
   {
      super( "�Ҽ������˵�" ); //���ø��๹�캯��

      ChangeColorAction action = new ChangeColorAction(); //�˵����¼�����
      String[] str = {"Blue","Pink","Yellow","Red","Orange"}; //�˵�������
      ButtonGroup colorGroup=new ButtonGroup(); //ʵ������ť��
      popupMenu=new JPopupMenu(); //ʵ���������˵�
      items=new JRadioButtonMenuItem[5]; //��ʼ������
      for (int i=0;i<items.length;i++) { 
         items[i]=new JRadioButtonMenuItem(str[i]); //ʵ�����˵���
         popupMenu.add(items[i]); //���Ӳ˵���˵���
         colorGroup.add(items[i]); //���Ӳ˵����ť��
        items[i].addActionListener(action); //�˵����¼�����
      }     

      addMouseListener(new MouseAdapter(){  //���ڵ�����¼�����
        public void mousePressed( MouseEvent event ) {  //������
           triggerEvent(event);  //����triggerEvent���������¼�
        } 

        public void mouseReleased( MouseEvent event ) { //�ͷ����
           triggerEvent(event); 
        } 

        private void triggerEvent(MouseEvent event) { //�����¼�
           if (event.isPopupTrigger()) //����ǵ����˵��¼�(����ƽ̨��ͬ���ܲ�ͬ)
              popupMenu.show(event.getComponent(),event.getX(),event.getY());  //��ʾ�˵�
        }
    }); 

	getContentPane().setBackground(Color.white); //���ڵ�Ĭ�ϱ���ɫΪ��ɫ
    setSize(230,160); //���ô��ڴ�С
    setVisible(true); //���ô���Ϊ����
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE ); //�رմ���ʱ�˳�����
   }

   class ChangeColorAction implements ActionListener { //�˵����¼�����
      public void actionPerformed(ActionEvent event)   {
         for (int i=0;i<items.length;i++)
            if (event.getSource()==items[i]) { //�ж��¼��������ĸ��˵���
               getContentPane().setBackground(colors[i]); //���ô��ڱ���
               repaint(); //�ػ洰��
               return;
         }
      }
   }  
   
   public static void main( String args[])   {
      new JPopMenuDemo();      
   }
} 

