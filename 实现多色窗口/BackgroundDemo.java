import java.awt.*;
import java.util.*;
import java.net.*;
import java.awt.image.*;

class myFrame extends Frame 
{
   Image bg;   
   Button bt;
   URL pos; 
   public myFrame()
   {
      Panel p1,p2,p3,p4;
      setLayout(new BorderLayout());
      p1=new Panel();
      p2=new Panel();
      p3=new Panel();
      p4=new Panel();
      bt=new Button("Click Me");
      p1.setBackground(Color.BLUE);
      p2.setBackground(Color.RED);
      p3.setBackground(Color.YELLOW);
      p4.setBackground(Color.GRAY);
      
      setSize(400,300);
      add(p1,BorderLayout.CENTER);
      add(p2,BorderLayout.WEST);
      p1.setLayout(new GridLayout(2,1));
      p1.add(p3);
      p1.add(p4);
      p2.add(bt);
      show();
   }	
   
}

public class BackgroundDemo
{
   public static void main(String args[])
   {
      new myFrame();
   }	
}