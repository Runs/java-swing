import java.awt.*;
import javax.swing.*;

public class JtableDemo extends JApplet
{
   public void init()
	{
	   Container cp=getContentPane();
	   cp.setLayout(new BorderLayout());

	   final String[] colHeads={"Name","Phone","Age"};
	   
	   final Object[][] data ={
		   {"Lin Song", "87934656","22"},
		   {"Jin Tao","67843522","32"},
		   {"H.J.King","67232432","56"},
		   {"Jaychou Low","87934810","22"},
		   {"Ken","76908435","32"},
		   {"Martin","87957547","43"},
		   {"Jill Bert","87964673","25"},
	           {"Ling Feng", "56734656","28"},
		   {"W.L Wang","35226546","32"},
		   {"H.J.Mark","67289432","56"},
		   {"Jay chou","87934810","22"},
		   {"Keny","76908435","32"},
		   {"Karter","79575472","33"},
		   {"Paul","94664673","42"}
	   };
	  
	  JTable jt=new JTable(data,colHeads);

	  int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	  int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

	  JScrollPane jsp=new JScrollPane(jt,v,h);
	  cp.add(jsp,BorderLayout.CENTER);
	}

}