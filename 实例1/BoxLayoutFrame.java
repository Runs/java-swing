//BoxLayoutFrame.java
import java.awt.*;
import javax.swing.*;

public class BoxLayoutFrame extends JFrame {
  BoxLayoutTest panel = new BoxLayoutTest();

  public BoxLayoutFrame() {
    this.getContentPane().add(panel);
    this.setSize(500,220);
    this.setTitle("BoxLayoutDemo");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.show();
  }

  public static void main(String[] args) {
    BoxLayoutFrame frame = new BoxLayoutFrame();
  }
}

class BoxLayoutTest extends JPanel {
  BoxLayoutTest() {
    // Set the layout to a y-axis BoxLayout
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // Create three components
    TextField textField = new TextField();
    TextArea textArea = new TextArea(4, 20);
    JButton button = new JButton(
      "Click me!");

    // Add the three components to the BoxLayout
    add(new JLabel("TextField"));
    add(textField);
    add(new JLabel("TextArea"));
    add(textArea);
    add(new JLabel("Button"));
    add(button);
  }
}
