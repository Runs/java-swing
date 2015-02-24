import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import javax.swing.*;

//��ȡĿ¼/�ļ���Ϣ

public class FileInfoDemo extends JFrame{
	JTextField jtfPath;	//�ļ�·�������
	JTextArea jtaInfo;	//��ʾ�ļ������ı���

	public FileInfoDemo(){
		super("ȡ��Ŀ¼/�ļ���Ϣ");	//���ø��๹�캯��

		jtfPath=new JTextField(16);	//ʵ�����ļ������
		JButton jbSelectedFile=new JButton("ѡ��");	//ʵ�����ļ�ѡ��ť
		JPanel panel=new JPanel();	//���,���������������ļ�ѡ��ť
		jtaInfo=new JTextArea();	//ʵ�����ļ���Ϣ��ʾ��
		panel.add(jtfPath);	//������������
		panel.add(jbSelectedFile);
		Container container=getContentPane();	//�õ�����
		container.add(panel,BorderLayout.NORTH);	//���������������
		JScrollPane jsp=new JScrollPane(jtaInfo);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	//���ñ߽�
		container.add(jsp,BorderLayout.CENTER);

		jbSelectedFile.addActionListener(new ActionListener(){	//ѡ���ļ���ť�¼�����
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser=new JFileChooser();		//ʵ�����ļ�ѡ����
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  //�����ļ�ѡ��ģʽ,�˴�Ϊ�ļ���Ŀ¼����
				if (fileChooser.showOpenDialog(FileInfoDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ���Ŀ¼�ľ���·��
					jtfPath.setText(fileName);	
					showFileInfo(jtfPath.getText());	//��ʾ�ļ���Ϣ
				}
			}
		});

		jtfPath.addActionListener(new ActionListener(){	//�ļ�·��������¼�����
			public void actionPerformed(ActionEvent event) {
				showFileInfo(jtfPath.getText());	//��ʾ�ļ���Ϣ
			}
		});

		setSize(300,200);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}


	public void showFileInfo(String filename){
		jtaInfo.setText("");	//�����Ϣ��ʾ��
		File f=new File(filename);	//�Եõ���·��ʵ�����ļ�����
		jtaInfo.append(filename+":\n");	//����Ϣ��ʾ����������ʾ�ı�
		if (f.isDirectory()){	//�Ƿ�ΪĿ¼
			jtaInfo.append("��һ��Ŀ¼");
		}
		else if (f.isFile()){	//�Ƿ�Ϊ�ļ�
			jtaInfo.append("��һ���ļ�");
		}
		jtaInfo.append("\n �ɶ�: "+f.canRead()); //�õ��ɶ�����
		jtaInfo.append("\n ��д: "+f.canWrite());	//�õ���д����
		jtaInfo.append("\n ����: "+f.isHidden());  //�Ƿ��������ļ�
		jtaInfo.append("\n ֻ��: "+f.setReadOnly());  //�Ƿ���ֻ���ļ�
		long modifyDate = f.lastModified();	//�õ�����޸�����
		if (modifyDate!=0){
			jtaInfo.append("\n ����޸�����: "+new Date(modifyDate));
		}
		long length=f.length();	//�õ��ļ�����(�����Ŀ¼,��Ϊ0)
		if (length!=0){
			jtaInfo.append("\n �ļ�����: "+length);
		}
	}

	public static void main(String[] args){
		new FileInfoDemo();
	}
}
