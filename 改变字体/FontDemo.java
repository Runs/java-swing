import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class FontDemo extends Applet
             implements ActionListener
{
   Font f,fo;
   String msg="";
   int i;
   Button b1,b2,b3,b4;
   
   public void init()
	{
	    b1=new Button("Dialog");
	    b2=new Button("Times New Roman");
	    b3=new Button("Sans Serif");
	    b4=new Button("Serif");

            add(b1);
	    add(b2);
	    add(b3);
	    add(b4);

	    b1.addActionListener(this);
	    b2.addActionListener(this);
	    b3.addActionListener(this);
	    b4.addActionListener(this);
            
	    fo=new Font("Dialog", Font.PLAIN,12);
        }
   
   public void actionPerformed (ActionEvent ae)
	{
	   if(ae.getSource()==b1)
		{
		  f=new Font("Dialog", Font.PLAIN,26);
		  setFont(f);
		  b1.setFont(fo);
		  msg="This is the Font Dialog";
		}
	   else if(ae.getSource()==b2)
		{
		  f=new Font("Times New Roman", Font.BOLD,26);
		  setFont(f);
		  b2.setFont(fo);
		  msg="This is the Font Times New Roman";
		}
	   else if(ae.getSource()==b3)
		{
		  f=new Font("Sans Serif", Font.ITALIC,26);
		  setFont(f);
		  b3.setFont(fo);
		  msg="This is the Font Sans Serif";
		}
	   else
	        {
		  f=new Font("Serif", Font.ITALIC|Font.BOLD,26);
		  setFont(f);
		  b4.setFont(fo);
		  msg="This is the Font Sans Serif";
		}
	  
	
	}
   public void paint(Graphics g)
	{
	    g.drawString(msg,10,100);
	}
   
}