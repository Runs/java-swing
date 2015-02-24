import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SplitDemo extends JFrame implements ActionListener
{
   int orie;
   JSplitPane jsp;
   JPanel p1;
   JPanel p2;
   JPanel p3;
   JButton bt;
   public SplitDemo()
   {
     
      Container cp=getContentPane();
      p1=new JPanel();
      p2=new JPanel();
      p3=new JPanel();
      bt=new JButton("Change Orientation");
      
      cp.setLayout(new BorderLayout());
      p3.setLayout(new FlowLayout());
     
      jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,p1,p2);
      jsp.setOneTouchExpandable(true);
      
      cp.add(jsp,BorderLayout.CENTER);
      p3.add(bt);
      cp.add(p3,BorderLayout.SOUTH);
      bt.addActionListener(this);
      p1.setBackground(Color.WHITE);
      p2.setBackground(Color.WHITE);

      orie=0;
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400,300);
      show();
   }
  
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource()==bt)
      {
         if(orie==0){
		 jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	         orie=1; 
		 } 
	 else    {
		 jsp.setOrientation(JSplitPane.VERTICAL_SPLIT);
	         orie=0;
		 } 
      }
   }

   public static void main(String args[])
   {
      new SplitDemo();	    
   }
}