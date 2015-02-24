import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;

public class JtreeDemo extends JApplet 
{
    JTree tree;
    JTextField jtf;

    public void init()
    {
       Container cp=getContentPane();
       cp.setLayout(new BorderLayout());

       DefaultMutableTreeNode top = 
	              new DefaultMutableTreeNode("TOP");
       
       DefaultMutableTreeNode a = 
	              new DefaultMutableTreeNode("A");
       
       DefaultMutableTreeNode a1 = 
	              new DefaultMutableTreeNode("A1");
       a.add(a1);
       DefaultMutableTreeNode a2 = 
	              new DefaultMutableTreeNode("A2");
       a.add(a2);
       DefaultMutableTreeNode a3 = 
	              new DefaultMutableTreeNode("A3");
       a.add(a3);
       
       DefaultMutableTreeNode b = 
	              new DefaultMutableTreeNode("B");
       
       DefaultMutableTreeNode b1 = 
	              new DefaultMutableTreeNode("B1");
       b.add(b1);
       DefaultMutableTreeNode b2 = 
	              new DefaultMutableTreeNode("B2");
       b.add(b2);
       DefaultMutableTreeNode b3 = 
	              new DefaultMutableTreeNode("B3");
       b.add(b3);
       
       top.add(a);
       top.add(b);

       tree = new JTree(top);
       

       int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
       int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

       JScrollPane  jsp = new JScrollPane(tree,v,h);
       
       cp.add(jsp, BorderLayout.CENTER);
       
       jtf=new JTextField(20);
       cp.add(jtf,BorderLayout.SOUTH); 
       tree.addMouseListener(new MouseAdapter()
	    {
	       public void mouseClicked(MouseEvent me)
		    {
		      doMouseClicked(me);
		    }
	    });
    }

    void doMouseClicked(MouseEvent me)
	{
	    TreePath tp=tree.getPathForLocation(me.getX(),me.getY());
	    if (tp!= null)
		jtf.setText(tp.toString());
	    else 
		jtf.setText("");
		
	}
}