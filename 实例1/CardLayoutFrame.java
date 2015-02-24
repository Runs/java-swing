//CardLayoutFrame.java
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CardLayoutFrame extends JFrame {
  JButton btPrevious = new JButton("前一张");
  JButton btNext = new JButton("下一张");
  JPanel flowPanel = new JPanel(new FlowLayout());
  JPanel cardPanel = new JPanel(new CardLayout());
  int currentIndex = 0;

  public CardLayoutFrame() {
    this.getContentPane().add(flowPanel,BorderLayout.SOUTH);
    this.getContentPane().add(cardPanel,BorderLayout.CENTER);
    cardPanel.add(getCard(1),"Card1");
    cardPanel.add(getCard(2),"Card2");
    flowPanel.add(btPrevious);
    flowPanel.add(btNext);
    ActionListener listener = new  ActionListener() {
      public void actionPerformed(ActionEvent e) {
        switchCard();
      }
    };
    btPrevious.addActionListener(listener);
    btNext.addActionListener(listener);

    this.setSize(300,200);
    this.setTitle("GardLayoutDemo");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.show();
  }

  JPanel getCard(int index){
    JPanel panel = new JPanel(new BorderLayout());
    JLabel label = new JLabel("<HTML><h1 style=color:red>"+
    "这是第"+index+"张卡片"+
    "</h1></HTML>"
    );
    label.setHorizontalAlignment(JLabel.CENTER);
    panel.add(label);
    return panel;
  }

  void switchCard(){
    CardLayout cl = (CardLayout)cardPanel.getLayout();
    if (currentIndex==0){
      currentIndex++;
      cl.show(cardPanel,"Card2");
    }else{
      currentIndex--;
      cl.show(cardPanel,"Card1");
    }
  }

  public static void main(String[] args) {
    CardLayoutFrame frame = new CardLayoutFrame();
  }
}
