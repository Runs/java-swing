//JSplashWindow.java
//Demos how to show a splash window

import javax.swing.*;
import java.awt.*;
import java.net.*;

public  class JSplashWindow extends JWindow implements Runnable {
  Thread splashThread=null;
  public JSplashWindow() {
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    JPanel splash = new JPanel(new BorderLayout());
    URL url = getClass().getResource("/images/winter.jpg");

    if(url != null){
      splash.add(new JLabel(new ImageIcon(url)),
      BorderLayout.CENTER);
    }

    setContentPane(splash);

    Dimension screen = getToolkit().getScreenSize();
    pack();
    setLocation((screen.width - getSize().width) / 2,
	(screen.height - getSize().height) / 2);
  }

  public void start(){
    this.toFront();
    splashThread=new Thread(this);
    splashThread.start();

  }

  public void run(){
    try {
      show();
      Thread.sleep(5000);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    this.dispose();
  }

  static void showFrame(String title){
    JFrame frame = new JFrame(title);
    frame.setSize(400,300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

    frame.setVisible(true);

  }

  public static void main(String[] args) {
    showFrame("Demo splash window");
    JSplashWindow splash = new JSplashWindow();
    splash.start();
    
  }
}
