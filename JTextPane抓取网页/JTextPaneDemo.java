import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class JTextPaneDemo extends JFrame 
{
  public JTextPaneDemo()
  {
     JTextPane tp=new JTextPane();
     
     int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
     int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
     JScrollPane jsp=new JScrollPane(tp,v,h);
     
     Container cp=getContentPane();
     cp.add(jsp);
     setSize(400,300); 
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     show();
     
     try 
     {
       URL myurl = new URL("http://www.sohu.com.cn");
       tp.setPage(myurl);
     }catch (Exception e)
     {
        e.printStackTrace();
     }
  }
  
  public static void main(String args[])
  {
     new JTextPaneDemo();	
  }
}