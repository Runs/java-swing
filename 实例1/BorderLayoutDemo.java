import java.awt.*;
import java.applet.*;
import java.util.*;

public class BorderLayoutDemo extends Applet
{
   public void init()
   {
      setLayout(new BorderLayout());
      
      add(new Button("This is arross the top"), 
	      BorderLayout.NORTH);
      add(new Label("The message is on the bottom"),
	      BorderLayout.SOUTH);
      add(new Button("Left"),BorderLayout.WEST);
      add(new Button("Right"),BorderLayout.EAST);
      String message="This is the message in the Center!";
      add(new TextArea(message),BorderLayout.CENTER);
   }
}