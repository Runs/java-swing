//JSplashWindowEx.java
//Demos how to show a splash window, a progress bar also shown on it

import javax.swing.*;
import java.awt.*;
import java.net.*;
public  class JSplashWindowEx extends JWindow implements Runnable {
  Thread splashThread=null;
  private JProgressBar progress;
  public JSplashWindowEx() {
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    JPanel splash = new JPanel(new BorderLayout());
    URL url = getClass().getResource("/images/winter.jpg");

    if(url != null){
      splash.add(new JButton(new ImageIcon(url)),
      BorderLayout.CENTER);
    }
    progress = new JProgressBar(1,100);
    progress.setStringPainted(true);
    progress.setBorderPainted(false);
    progress.setString("Program is Now Loading...");
    progress.setBackground(Color.white);
    splash.add(progress,BorderLayout.SOUTH);
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
    show();
    try {
      for (int i=0;i<100;i++){
        Thread.sleep(100);
        progress.setValue(progress.getValue() + 1);
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    dispose();
  }

  static void showFrame(String title){
    JFrame frame = new JFrame(title);
    frame.setSize(640,480);
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

    try {
      Thread.sleep(10000);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    frame.setVisible(true);

  }

  public static void main(String[] args) {
    JSplashWindowEx splash = new JSplashWindowEx();
    splash.start();
    showFrame("Splash window demo");
  }
}
