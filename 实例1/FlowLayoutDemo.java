import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class FlowLayoutDemo extends Applet implements ItemListener 
{
  String msg="";
  Checkbox Win98, WinNT,Linux, mac;

  public void init()
  {
	setLayout(new FlowLayout(FlowLayout.LEFT));
        
	Win98= new Checkbox("Win98",null,true);
	WinNT = new Checkbox("Win NT/2000");
	Linux = new Checkbox("Linux / Unix");
	mac = new Checkbox("MacOS");

	add(Win98);
	add(WinNT);
	add(Linux);
	add(mac);

	Win98.addItemListener(this);
	WinNT.addItemListener(this);
	Linux.addItemListener(this);
	mac.addItemListener(this);
  }

public void itemStateChanged(ItemEvent ie)
{
   repaint();
}

public void paint(Graphics g)
{
   msg="current state: ";
   g.drawString(msg, 6, 80);
   msg=" Window 98: "+Win98.getState();
   g.drawString(msg, 6, 100);
   msg=" Window NT / 2000: "+WinNT.getState();
   g.drawString(msg, 6, 120);
   msg=" Linux / Unix: "+Linux.getState();
   g.drawString(msg, 6, 140);
   msg=" MacOs "+mac.getState();
   g.drawString(msg, 6, 160);
   

}

}