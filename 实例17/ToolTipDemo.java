import java.awt.*;
import javax.swing.*;

public class ToolTipDemo extends JFrame
{
   String tooltipText1="Click me!";
   String tooltipText2="<html><HTML><body style=color:red>"+
                         "Tooltip in <br> Multiline</body></html>";

   public ToolTipDemo()
   {
      JButton b1=new JButton("Button 1");
      JButton b2=new JButton("Button 2");
      b1.setToolTipText(tooltipText1);
      b2.setToolTipText(tooltipText2);
      
      Container cp=getContentPane();
      cp.setLayout(new FlowLayout());
      setSize(200,100);
      cp.add(b1);
      cp.add(b2);
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      show();
   }
   
   public static void main(String args[])
   {
      new ToolTipDemo();
   }
}