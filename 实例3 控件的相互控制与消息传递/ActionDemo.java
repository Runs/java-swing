import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//�ؼ����໥��������Ϣ����

public class ActionDemo extends JFrame{
	JTextField jtfName;  //����������
	JTextArea jtaChat;  //��ʾ������Ϣ
	JTextArea jtaInput;  //������Ϣ
	JButton jbSend;  //������Ϣ��ť
	JButton jbClear;  //���������Ϣ��

	public ActionDemo(){
		super("�ؼ����໥����");  //���ø��๹�캯��

		Container container=this.getContentPane();  //�õ�����
		JPanel p=new JPanel();  //��ʼ��һ�����
		jtfName=new JTextField(10);  //��ʼ������������
		p.add(new JLabel("���⣺Write Once, Run Anywhere.     "));  //�����������ǩ
		p.add(new JLabel("�ǳ�"));  //�����ǳƱ�ǩ
		p.add(jtfName);  //��������������
      container.add(p,BorderLayout.NORTH);  //���������������

      jtaChat=new JTextArea();  //��ʼ����Ϣ��ʾ�ı���
   	container.add(new JScrollPane(jtaChat),BorderLayout.CENTER);  //��������������Ϣ��ʾ�ı���

		Box box=new Box(BoxLayout.X_AXIS);  //��ʼ��һ��Box
		jtaInput=new JTextArea(3,20);  //��ʼ����Ϣ������
		jbSend=new JButton();  //��ʼ�����Ͱ�ť
		jbClear=new JButton();   //��ʼ�������ť
		jbClear.setText("���");  //���ð�ť�ı�
		box.add(new JScrollPane(jtaInput));  //������Ϣ������
		box.add(jbClear);  //���Ӱ�ť
		box.add(jbSend);
		container.add(box,BorderLayout.SOUTH);  //������������box

		jbClear.addActionListener(new ActionListener(){  //�����ť�¼�����
      	public void actionPerformed(ActionEvent e){
      		jtaInput.setText("");  //��������ťʱ������������Ϣ
         }
      });

	   Action sendMessage = new AbstractAction() {  //������ϢAction
	   	public void actionPerformed(ActionEvent e){
	   		replaceMessage();  //������Ϣ��ʾ��
	    	}
		};
		jtaInput.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"send");  //�����¼�����,���ܻس��¼�
		jtaInput.getActionMap().put("send",sendMessage);  //�س�ʱ�Ĵ���(���÷�����ϢAction)

		jbSend.setAction(sendMessage);  //��������Ϊ������Ϣ
		jbSend.setText("����");  //���ð�ť�ı�

	   setSize(400,200);  //���ô��ڳߴ�
	   setVisible(true);  //���ô��ڿ���
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}

	private void replaceMessage(){
	   String message=jtfName.getText()+"> "+jtaInput.getText()+"\n";  //�õ���Ϣ�ı�
	   jtaChat.insert(message,jtaChat.getDocument().getLength());  //������Ϣ����ʾ��δ��
	   jtaInput.setText("");  //���������Ϣ��
	}

   public static void main(String[] args){
      new ActionDemo();
   }
}