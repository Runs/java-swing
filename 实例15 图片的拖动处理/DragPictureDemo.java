import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//ͼƬ���϶�Ч��

public class DragPictureDemo extends JFrame {

   JLabel jlPic; //ͼƬ�������϶�

   public DragPictureDemo() {
   	super("ͼƬ���϶�Ч��");  //���ø��๹�캯��
   	Icon image=new ImageIcon(this.getClass().getResource("1.jpg"));  //ʵ����ͼ��
		jlPic = new JLabel(image);  //ʵ������ͼƬ�ı�ǩ
      getContentPane().add(jlPic);  //���ӱ�ǩ��������
      
      DragPicListener listener=new DragPicListener();  //����¼�����
      jlPic.addMouseListener(listener);  //���ӱ�ǩ���¼�����
      jlPic.addMouseMotionListener(listener);       
      
      setSize(300,200);  //���ô��ڳߴ�
      setVisible(true);  //���ô���Ϊ����
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
   }
   
   class DragPicListener implements MouseInputListener{  //����¼�����
      Point p=new Point(0,0); //�����
   	public void mouseMoved(MouseEvent e){}
   	public void mouseDragged(MouseEvent e){
       	Point newP=SwingUtilities.convertPoint(jlPic,e.getPoint(),jlPic.getParent()); //ת������ϵͳ
       	jlPic.setLocation(jlPic.getX()+(newP.x-p.x),jlPic.getY()+(newP.y-p.y)); //���ñ�ǩ����λ��
      	p=newP; //���������
   	}
   	public void mouseReleased(MouseEvent e){}
   	public void mouseEntered(MouseEvent e){}
   	public void mouseExited(MouseEvent e){}
   	public void mouseClicked(MouseEvent e){}
   	public void mousePressed(MouseEvent e){
       	p=SwingUtilities.convertPoint(jlPic,e.getPoint(),jlPic.getParent()); //�õ���ǰ�����
   	}   
   }

    public static void main(String[] args) {
		new DragPictureDemo();
   } 
}
