import java.awt.*;
import java.applet.*;

public class GridLayoutDemo extends Applet
{
   static final int n=4;
   public void init()
   {
       setLayout(new GridLayout(n,n));
       setFont(new Font("Times New Roman", Font.BOLD, 24));

       for (int i=0; i<n; i++){
         for(int j=0; j<n; j++)
	       {
	          int k=i*n+j;
		  if (k>0)
		      add(new Button(""+k));
	       }
       }
   }
}