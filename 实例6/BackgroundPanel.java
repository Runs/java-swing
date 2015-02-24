import java.awt.*;
import javax.swing.*;
public class BackgroundPanel extends JFrame
{
   public BackgroundPanel()
   {
       setSize(400,300);
       Container cp=getContentPane();
       cp.setLayout(new BorderLayout());
       
       NewPanel p=new NewPanel();
       p.setLayout(new FlowLayout());
       cp.add(p,BorderLayout.CENTER);
       JButton btt=new JButton("button");
       cp.add(btt,BorderLayout.SOUTH);
       
       p.add(new JTextField(10));
       p.add(new JButton("Click me"));
       p.add(new JComboBox());
       p.add(new JButton("Hello, world!"));

       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       show();
   }
   public static void main(String arg[])
   {new BackgroundPanel();}
}
class NewPanel extends JPanel
{    
   public NewPanel(){}
   public void paintComponent(Graphics g)
   {
       int x=0,y=0;
       java.net.URL imgURL = getClass().getResource("test.gif");         
       ImageIcon icon = new ImageIcon(imgURL);                     
       g.drawImage(icon.getImage(),x,y,getSize().width,getSize().height,this);
       while(true)
       {   
           g.drawImage(icon.getImage(),x,y,this);            
           if(x>getSize().width&&y>getSize().height)break;            
           if(x>getSize().width)
	   {    
		   x=0;
		   y+=icon.getIconHeight(); 
	   }               
           else    x+=icon.getIconWidth();
       }
   }   
}
