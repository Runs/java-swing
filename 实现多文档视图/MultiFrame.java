import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MultiFrame extends JFrame implements ActionListener
{
   JButton jbAdd, jbRemove;
   JInternalFrame ip[];
   JDesktopPane dp;
   JPanel jp;
   int index=0;
   
   public MultiFrame()
   {
      dp=new JDesktopPane();
      jp=new JPanel();
      ip= new JInternalFrame[12];
      jbAdd=new JButton("Add");
      jbRemove=new JButton("Remove");
      
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      jp.setLayout(new FlowLayout());
      jp.add(jbAdd);
      jp.add(jbRemove);
      cp.add(dp,BorderLayout.CENTER);
      cp.add(jp,BorderLayout.SOUTH);
      
      jbAdd.addActionListener(this);
      jbRemove.addActionListener(this);
      setSize(400,300);
      
      show();
   }	

   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource()==jbAdd)
      {
         ip[index]=new JInternalFrame();
         dp.add(ip[index],index);
         ip[index].setSize(200,200);
         ip[index].setResizable(true);
         ip[index].setVisible(true);
         ++index;
      }
      else if(ae.getSource()==jbRemove)
      {
         --index;
         ip[index].setVisible(false);
         dp.remove(ip[index]);
         
      }
   }
   
   public static void main(String args[])
   {
      new MultiFrame();
   }
}