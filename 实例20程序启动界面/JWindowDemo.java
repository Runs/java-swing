import javax.swing.*;
import java.awt.*;
import java.net.*;

//������������

public  class JWindowDemo extends JWindow implements Runnable {
  Thread splashThread;  //�����������߳�
  JProgressBar progress; //������
  
  public JWindowDemo() {
  	 Container container=getContentPane(); //�õ�����
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  //���ù��
    URL url = getClass().getResource("login.jpg"); //ͼƬ��λ��
    if(url != null){
      container.add(new JLabel(new ImageIcon(url)),BorderLayout.CENTER);  //����ͼƬ
    }
    progress = new JProgressBar(1,100); //ʵ����������
    progress.setStringPainted(true); //�������
    progress.setString("���س�����,���Ժ�......");  //������ʾ����
    progress.setBackground(Color.white);  //���ñ���ɫ
    container.add(progress,BorderLayout.SOUTH);  //���ӽ�������������

    Dimension screen = getToolkit().getScreenSize();  //�õ���Ļ�ߴ�
    pack(); //������Ӧ����ߴ�
    setLocation((screen.width-getSize().width)/2,(screen.height-getSize().height)/2); //���ô���λ��
  }

  public void start(){
    this.toFront();  //����ǰ����ʾ
    splashThread=new Thread(this);  //ʵ�����߳�
    splashThread.start();  //��ʼ�����߳�
  }

  public void run(){
    setVisible(true); //��ʾ����
    try {
      for (int i=0;i<100;i++){
        Thread.sleep(100); //�߳�����
        progress.setValue(progress.getValue()+1); //���ý�����ֵ
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    dispose(); //�ͷŴ���
    showFrame(); //����������
  }

  static void showFrame(){
    JFrame frame = new JFrame("��������������ʾ"); //ʵ����JFrame����
    frame.setSize(300,200);  //���ô��ڳߴ�
    frame.setVisible(true);  //���ڿ���
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
  }

  public static void main(String[] args) {
    JWindowDemo splash = new JWindowDemo();
    splash.start();  //������������
  }
}
