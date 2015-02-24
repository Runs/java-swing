import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//�����ļ���ʾ

public class CopyFileDemo extends JFrame{
	JFileChooser fileChooser;	//�ļ�ѡ����
	JTextField jtfSourceFile;	//Դ�ļ�·��
	JTextField jtfTargetFile;	//Ŀ���ļ�·��
	JButton selectFile1;	//ѡ���ļ���ť
	JButton selectFile2;
	JButton copyButton;	//������ť

	public CopyFileDemo(){
		super("�����ļ���ʾ");	//���ø��๹�캯��
		fileChooser=new JFileChooser();	//ʵ�����ļ�ѡ����

		Container container=getContentPane();	//�õ�����
		jtfSourceFile=new JTextField(16);	//ʵ�������
		jtfTargetFile=new JTextField(16);
		selectFile1=new JButton("ѡ��");
		selectFile2=new JButton("ѡ��");
		copyButton=new JButton("����");
		Box box=Box.createVerticalBox();	//ʵ����Box,�����������
		JPanel panel=new JPanel();
		panel.add(new JLabel("Դ  �� ��"));	//������������(panel)��
		panel.add(jtfSourceFile);
		panel.add(selectFile1);
		box.add(panel);	//���������Box��
		panel=new JPanel();
		panel.add(new JLabel("Ŀ���ļ�"));
		panel.add(jtfTargetFile);
		panel.add(selectFile2);
		box.add(panel);
		box.add(copyButton);
		container.add(box);	//���������������


		selectFile1.addActionListener(new SelectFileListener());	//����ѡ���ļ����¼�����
		selectFile2.addActionListener(new SelectFileListener());
		copyButton.addActionListener(new ActionListener(){	//������ť�¼�����
			public void actionPerformed(ActionEvent event) {
				String sourceFile=jtfSourceFile.getText();	//�õ�Դ�ļ�·��
				String targetFile=jtfTargetFile.getText();	//�õ�Ŀ���ļ�·��
				if (copy(sourceFile,targetFile)){	//�����ļ�
					JOptionPane.showMessageDialog(CopyFileDemo.this,"�����ɹ�");	//��ʾ�����ɹ���Ϣ
				}
				else{
					JOptionPane.showMessageDialog(CopyFileDemo.this,"����ʧ��");	//��������
				}
			}
		});


		setSize(340,160);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}

	class SelectFileListener implements ActionListener {	//ȡ��Ŀ¼���ݵ��¼�����
		public void actionPerformed(ActionEvent event) {
			if (fileChooser.showOpenDialog(CopyFileDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
				String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ��ľ���·��
				if (event.getSource().equals(selectFile1)){	//�ж��¼��������ĸ���ť
					jtfSourceFile.setText(fileName);	//����Դ�ļ�·��
				}
				else{
					jtfTargetFile.setText(fileName);	//����Ŀ���ļ�·��
				}
      	}
		}
	}

	public boolean copy(String file1,String file2){	//�����ļ�����
		try{
			java.io.File fileIn=new java.io.File(file1);	//��·��������Դ�ļ�
			java.io.File fileOut=new java.io.File(file2);	//��·��������Ŀ���ļ�
			FileInputStream fin=new FileInputStream(fileIn);	//�õ��ļ�������
			FileOutputStream fout=new FileOutputStream(fileOut);	//�õ��ļ������
			byte[] bytes=new byte[1024];	//��ʼ���ֽ�����,���ڻ���
			int c;
			while((c=fin.read(bytes))!=-1){	//����ļ�δ����
				fout.write(bytes,0,c);	//����ȡ���ֽ�����д��Ŀ���ļ��������
			}
			fin.close();	//�ر�������
			fout.close();	//�ر������
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	public static void main(String[] args){
		new CopyFileDemo();
	}
}
