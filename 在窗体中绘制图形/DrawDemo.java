import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class DrawPanel extends JPanel
{
   public int chx=100, chy=100, xori=100,yori=100;
   public DrawPanel()
   {
     setBackground(Color.WHITE);
     addMouseMotionListener(new MouseMotionAdapter()
     {
        public void mouseMoved(MouseEvent me)
        {
          xori=chx;
          yori=chy;
          chx=me.getX();
          chy=me.getY();
          repaint();
        }
     }
     );
   }
   
   public void paint(Graphics g)
   {
     g.drawLine(0,0,100,100);
     g.drawLine(0,100,100,0);
     g.setColor(Color.BLUE);
     g.drawLine(40,25,250,80);
     g.drawLine(75,90,400,400);
     g.setColor(Color.GREEN);
     g.drawRect(10,10,60,50);
     g.fillRect(100,10,60,50);
     g.setColor(Color.RED);
     g.drawRoundRect(190,10,60,50,15,15);
     g.fillArc(100,100,70,90,0,270);
     g.setColor(Color.CYAN);
     g.fillOval(200,100,160,90);
     g.drawLine(150,150,400,40);
     g.drawLine(5,290,80,19);
   
     g.setColor(Color.WHITE);
     g.drawLine(xori,yori-10,xori,yori+10);
     g.drawLine(xori-10,yori,xori+10,yori);
     
     g.setXORMode(Color.BLACK);
     g.drawLine(chx,chy-10,chx,chy+10);
     g.drawLine(chx-10,chy,chx+10,chy);
     g.setPaintMode();
   }
}

public class DrawDemo extends JFrame
{
   
   public DrawDemo()
   {
     Container cp=getContentPane();
     setBackground(Color.WHITE);
     DrawPanel dp=new DrawPanel();
     
     cp.setLayout(new BorderLayout());
     cp.add(dp,BorderLayout.CENTER);
     setSize(400,250);
     show();
   }
   
   

   public static void main(String args[])
   {
     new DrawDemo();
   }
}