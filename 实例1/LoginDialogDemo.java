//LoginDialogDemo.java
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginDialogDemo extends JFrame {
  JButton button = new JButton("Click Me");
  JPanel panel = new JPanel(new FlowLayout());

  public LoginDialogDemo() {
    final JFrame frame = this;
    this.getContentPane().add(panel,BorderLayout.SOUTH);
    panel.add(button);
    button.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        showLoginDialog(frame);
      }
    });
    this.setSize(300,200);
    this.setTitle("ÏÔÊ¾µÇÂ½¶Ô»°¿ò");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.show();
  }

  void showLoginDialog(JFrame frame){
    JPanel p = new JPanel(new GridLayout(0,1));
    JTextField tfUserName = new JTextField();
    JPasswordField tfPassword = new JPasswordField();
    p.add(new JLabel("Username: "));
    p.add(tfUserName);
    p.add(new JLabel("Password: "));
    p.add(tfPassword);
    if (JOptionPane.showConfirmDialog(frame // may want to pass your application frame here
                               ,p
                               ,"Login"
                               ,JOptionPane.OK_CANCEL_OPTION
                               ,JOptionPane.PLAIN_MESSAGE
                                ) == JOptionPane.OK_OPTION) {
      System.out.println("User Name:"+tfUserName.getText());
      System.out.println("Password:" + new String(tfPassword.getPassword()));
    }
  }

  public static void main(String[] args) {
    LoginDialogDemo frame = new LoginDialogDemo();
  }
}
