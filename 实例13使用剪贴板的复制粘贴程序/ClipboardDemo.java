import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*;

//��������ʾ

public class ClipboardDemo extends JFrame implements ClipboardOwner{
	Clipboard clipboard;  //������
	JTextArea jtaCopyTo=new JTextArea(5,10);	//���ڿ������ı���
	JTextArea jtaPaste=new JTextArea(5,10);	//����ճ�����ı���
	
	public ClipboardDemo(){
		super("ʹ�ü�����ĸ���/ճ������");	//���ø��๹�캯��
				
		clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();	//���ϵͳ������
		
		JButton btCopy=new JButton("����");	//������ť
		JButton btPaste=new JButton("ճ��");	//ճ����ť
		jtaCopyTo.setLineWrap(true);	//���û���
		jtaPaste.setLineWrap(true);
		jtaCopyTo.setBorder(BorderFactory.createTitledBorder("���Ƶ�ϵͳ���а�"));	//���ñ߽�
		jtaPaste.setBorder(BorderFactory.createTitledBorder("��ϵͳ���а�ճ��"));
		
		Container container=getContentPane();	//�õ�����
		JToolBar toolBar=new JToolBar();	//ʵ����������
		toolBar.add(btCopy);	//���ӹ�������ť
		toolBar.add(btPaste);		
		btCopy.addActionListener(new CopyListener());	//��ť�¼�����
		btPaste.addActionListener(new PasteListener());		
		Box box=new Box(BoxLayout.X_AXIS);	//ʵ����Box
		box.add(jtaCopyTo);	//�����ı���Box��
		box.add(jtaPaste);		
		container.add(toolBar,BorderLayout.NORTH);	//���ӹ�����������
		container.add(box,BorderLayout.CENTER);	//����Box������
	
		setSize(320,180);	//���ô��ڳߴ�
		setVisible(true);	//���ô���Ϊ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}
	
	class CopyListener implements ActionListener {	//�������ݴ���
		public void actionPerformed(ActionEvent event) {
			StringSelection contents=new StringSelection(jtaCopyTo.getText());	//�ÿ����ı����ı�ʵ����StringSelection����
			clipboard.setContents(contents, ClipboardDemo.this);	//����ϵͳ����������
		}
	}
	
	class PasteListener implements ActionListener {	//ճ�����ݴ���
		public void actionPerformed(ActionEvent event) {
			Transferable contents=clipboard.getContents(this);	//�õ�����������
				if(contents!=null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {	//�ж������Ƿ�Ϊ��,�Ƿ�Ϊ�ַ���
					try{
						String string= (String) contents.getTransferData(DataFlavor.stringFlavor);	//ת�����ݵ��ַ���
						jtaPaste.append(string);	//�����ַ�����ճ���ı���
					}catch (Exception ex){
						ex.printStackTrace();	//������
					}
			}
		}
	}
	
	public void lostOwnership(Clipboard clip,Transferable transferable) {	//ʵ��ClipboardOwner�ӿ��еķ���
	}

	public static void main(String[] args){
		new ClipboardDemo();	
	}
}