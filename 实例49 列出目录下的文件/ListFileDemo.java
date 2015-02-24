import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//�г�Ŀ¼�µ��ļ�

public class ListFileDemo extends JFrame{
	JTextField jtfPath;	//·�������ı���
	JTextArea jtfShow;	//��ʾĿ¼�µ�����

	public ListFileDemo(){
		super("�г�Ŀ¼�µ��ļ�");
		Container container=getContentPane();	//�õ�����
		jtfPath=new JTextField(16);	//ʵ����·�������ı���
		JButton jbGo=new JButton("ת��");	//ʵ����"ת��"��ť
		jtfShow=new JTextArea();	//ʵ������ʾ�����ı���
		jtfPath.addActionListener(new ShowDirListener());	//�����¼�����
		jbGo.addActionListener(new ShowDirListener());

		JPanel panel=new JPanel();	//ʵ�������,��������·������Ͱ�ť
		panel.add(jtfPath);
		panel.add(jbGo);
		container.add(panel,BorderLayout.NORTH);	//�������������
		JScrollPane jsp=new JScrollPane(jtfShow);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//���ñ߽�
		container.add(jsp,BorderLayout.CENTER);

		setSize(300,200);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}

	class ShowDirListener implements ActionListener {	//ȡ��Ŀ¼���ݵ��¼�����
		public void actionPerformed(ActionEvent event) {
			showDirContent(jtfPath.getText());	//������ʾĿ¼���ݷ���
		}
	}

	public void showDirContent(String path){	//�÷���ʵ��ȡ��Ŀ¼����
		File file=new File(path);	//��·��ʵ����һ���ļ�����
		File[] files=file.listFiles();	//�ص�:ȡ��Ŀ¼�������ļ��б�
		StringBuffer message=new StringBuffer();	//ʵ����һ��StringBuffer,���ڴ�����ʾ���ַ���
		message.append(path);	//������Ϣ
		message.append(" �������£�\n");
		for (int i=0;i<files.length;i++){
			if (files[i].isDirectory()){	//�������һ��Ŀ¼
				message.append("<dir>\t");	//����Ŀ¼��ʶ
			}
			else{
				message.append("\t");
			}
			message.append(files[i].getName());	//�����ļ���Ŀ¼��
			message.append("\n");
		}
		jtfShow.setText(new String(message));	//��ʾ��Ϣ
	}

	public static void main(String[] args){
		new ListFileDemo();
	}
}