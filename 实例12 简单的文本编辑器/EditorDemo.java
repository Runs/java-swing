import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

//�򵥵��ı��༭��

public class EditorDemo extends JFrame {
	JTextPane textPane = new JTextPane(); //�ı����񣬱༭����
	JLabel statusBar = new JLabel(); //״̬��
	JFileChooser filechooser = new JFileChooser(); //�ļ�ѡ����

	public EditorDemo() { //���캯��
		super("�򵥵��ı��༭��");  //���ø��๹�캯��

		Action[] actions =  //Action����,���ֲ�������
			{
				new NewAction(),
				new OpenAction(),
				new SaveAction(),
				new CutAction(),
				new CopyAction(),
				new PasteAction(),
				new AboutAction(),
				new ExitAction()};

		setJMenuBar(createJMenuBar(actions));  //���ò˵���
		Container container = getContentPane(); //�õ�����
		container.add(createJToolBar(actions), BorderLayout.NORTH); //���ӹ�����
		container.add(textPane, BorderLayout.CENTER); //�����ı�����
		container.add(statusBar, BorderLayout.SOUTH); //����״̬��

		setSize(330, 200); //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}

	private JMenuBar createJMenuBar(Action[] actions) {  //�����˵���
		JMenuBar menubar = new JMenuBar(); //ʵ�����˵���
		JMenu menuFile = new JMenu("�ļ�"); //ʵ�����˵�
		JMenu menuEdit = new JMenu("�༭");
		JMenu menuAbout = new JMenu("����");
		menuFile.add(new JMenuItem(actions[0])); //�����²˵���
		menuFile.add(new JMenuItem(actions[1]));
		menuFile.add(new JMenuItem(actions[2]));
		menuFile.add(new JMenuItem(actions[7]));
		menuEdit.add(new JMenuItem(actions[3]));
		menuEdit.add(new JMenuItem(actions[4]));
		menuEdit.add(new JMenuItem(actions[5]));
		menuAbout.add(new JMenuItem(actions[6]));
		menubar.add(menuFile); //���Ӳ˵�
		menubar.add(menuEdit);
		menubar.add(menuAbout);
		return menubar; //���ز˵���
	}

	private JToolBar createJToolBar(Action[] actions) { //����������
		JToolBar toolBar = new JToolBar(); //ʵ����������
		for (int i = 0; i < actions.length; i++) { 
			JButton bt = new JButton(actions[i]); //ʵ�����µİ�ť
			bt.setRequestFocusEnabled(false); //���ò���Ҫ����
			toolBar.add(bt); //���Ӱ�ť��������
		}
		return toolBar;  //���ع�����
	}

	class NewAction extends AbstractAction { //�½��ļ�����
		public NewAction() {
			super("�½�");
		}
		public void actionPerformed(ActionEvent e) {
			textPane.setDocument(new DefaultStyledDocument()); //����ĵ�
		}
	}

	class OpenAction extends AbstractAction { //���ļ�����
		public OpenAction() {
			super("��");
		}
		public void actionPerformed(ActionEvent e) {
			int i = filechooser.showOpenDialog(EditorDemo.this); //��ʾ���ļ��Ի���
			if (i == JFileChooser.APPROVE_OPTION) { //����Ի����д�ѡ��
				File f = filechooser.getSelectedFile(); //�õ�ѡ����ļ�
				try {
					InputStream is = new FileInputStream(f); //�õ��ļ�������
					textPane.read(is, "d"); //�����ļ����ı�����
				} catch (Exception ex) {
					ex.printStackTrace();  //���������Ϣ
				}
			}
		}
	}

	class SaveAction extends AbstractAction {  //��������
		public SaveAction() {
			super("����");
		}
		public void actionPerformed(ActionEvent e) {
			int i = filechooser.showSaveDialog(EditorDemo.this); //��ʾ�����ļ��Ի���
			if (i == JFileChooser.APPROVE_OPTION) {  //����Ի����б��水ť
				File f = filechooser.getSelectedFile(); //�õ�ѡ����ļ�
				try {
					FileOutputStream out = new FileOutputStream(f);  //�õ��ļ������
					out.write(textPane.getText().getBytes()); //д���ļ�				
				} catch (Exception ex) {
					ex.printStackTrace(); //���������Ϣ
				}
			}
		}
	}

	class ExitAction extends AbstractAction { //�˳�����
		public ExitAction() {
			super("�˳�");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);  //�˳�����
		}
	}

	class CutAction extends AbstractAction {  //��������
		public CutAction() {
			super("����");
		}
		public void actionPerformed(ActionEvent e) {
			textPane.cut();  //�����ı�����ļ�������
		} 
	}

	class CopyAction extends AbstractAction {  //��������
		public CopyAction() {
			super("����");
		}
		public void actionPerformed(ActionEvent e) {
			textPane.copy();  //�����ı�����Ŀ�������
		}
	}

	class PasteAction extends AbstractAction {  //ճ������
		public PasteAction() {
			super("ճ��");
		}
		public void actionPerformed(ActionEvent e) {
			textPane.paste();  //�����ı������ճ������
		}
	}

	class AboutAction extends AbstractAction { //����ѡ������
		public AboutAction() {
			super("����");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(EditorDemo.this, "�򵥵��ı��༭����ʾ"); //��ʾ�����Ϣ
		}
	}

	public static void main(String[] args) {
		new EditorDemo();
	}

}