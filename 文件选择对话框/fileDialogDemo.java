import java.awt.*;
import java.awt.event.*;

class fileDialog extends Frame
{
   fileDialog(String title)
	{
	   super(title);
	   MyWindowAdapter adapter = new MyWindowAdapter(this);
	   addWindowListener(adapter);
	}
}

class MyWindowAdapter extends WindowAdapter
{
   fileDialog sf;
   public MyWindowAdapter(fileDialog sfr)
	{
	   this.sf=sfr;
	}
   public void windowClosing(WindowEvent we)
	{
	   sf.setVisible(false);
	}
}

class fileDialogDemo extends Frame
{
   public static void main(String args[])
	{
	   Frame f=new fileDialog("File Dialog Demo!");
	   f.setVisible(true);
	   f.setSize(100,100);
	   FileDialog fd=new FileDialog(f,"File Dialog");
	   fd.setVisible(true);
	}
}