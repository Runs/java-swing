import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class JNIFrame extends JFrame implements ActionListener
{
   int hwnd;
   String title="testJNI";
   JButton jb1,jb2,jb3,jb4;
   
   static 
   {
      System.loadLibrary("WinLib");
   }
   
   public static native int getHwnd(String title);
   public static native void toFront(int hwnd);
   public static native void flash(int hwnd);
   public static native void noSizeChange(int hwnd);
   public static native void restore(int hwnd);
   
   public JNIFrame()
   {
      JPanel jp=new JPanel();
      Container cp=getContentPane();
      jb1=new JButton("总在最前");
      jb2=new JButton("闪烁标题");
      jb3=new JButton("只显示标题栏");
      jb4=new JButton("恢 复");
      
      jp.setLayout(new FlowLayout());
      jp.add(jb1);
      jp.add(jb2);
      jp.add(jb3);
      jp.add(jb4);
      cp.add(jp);
      setTitle(title);
      setSize(250,100);
      jb1.addActionListener(this);
      jb2.addActionListener(this);
      jb3.addActionListener(this);
      jb4.addActionListener(this);
      
      this.addWindowListener(new WindowAdapter()
      {
         
          public void windowOpened(WindowEvent we)
          {
             hwnd=getHwnd(title);
             System.out.println(hwnd);
          }
         
      });
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      show();
      
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource()==jb1)
        toFront(hwnd);
      else if(ae.getSource()==jb2)
        flash(hwnd);
      else if(ae.getSource()==jb3)
        noSizeChange(hwnd);
      else
        restore(hwnd); 
        
   }
   
   public static void main(String args[])
   {
      new JNIFrame();
   }
}


