import java.io.*;
import java.awt.event.*;
import java.util.zip.CNZipOutputStream;
import java.util.zip.*;
import javax.swing.*;

//ѹ�������ļ������ļ�

public class CNZipDemo extends JFrame{
	JFileChooser fileChooser;	//�ļ�ѡ����
	JTextField jtfSourceFile;	//Դ�ļ�·��
	JTextField jtfTargetFile;	//Ŀ���ļ�·��
	JButton selectFile1;	//ѡ���ļ���ť
	JButton selectFile2;
	JButton jbZip;	//
	JButton jbUnZip;

	public CNZipDemo(){
		super("ѹ�������ļ������ļ�");	//���ø��๹�캯��
		fileChooser=new JFileChooser();	//ʵ�����ļ�ѡ����

		jtfSourceFile=new JTextField(16);	//ʵ�������
		jtfTargetFile=new JTextField(16);
		selectFile1=new JButton("ѡ��");
		selectFile2=new JButton("ѡ��");
		jbZip=new JButton("ѹ��");
		jbUnZip=new JButton("��ѹ");
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
		panel=new JPanel();
		panel.add(jbZip);
		panel.add(jbUnZip);
		box.add(panel);
		getContentPane().add(box); //����Box��������

		jbZip.addActionListener(new ActionListener(){	//ѹ����ť�¼�����
			public void actionPerformed(ActionEvent event) {
				zipFile(jtfSourceFile.getText(),jtfTargetFile.getText());  //����ѹ���ļ�����
			}
		});

		jbUnZip.addActionListener(new ActionListener(){	//��ѹ��ť�¼�����
			public void actionPerformed(ActionEvent event) {
				unZipFile(jtfSourceFile.getText(),jtfTargetFile.getText());	//���ý�ѹ�ļ�����
			}
		});

		selectFile1.addActionListener(new SelectFileListener());	//����ѡ���ļ����¼�����
		selectFile2.addActionListener(new SelectFileListener());

		setSize(330,150);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}

	public void zipFile(String source,String target){ //ѹ���ļ�
		try{
			FileInputStream fin=new FileInputStream(source);	//��Դ�ļ��õ��ļ�������
			FileOutputStream fout=new FileOutputStream(target);	//�õ�Ŀ���ļ������
			CNZipOutputStream cnout=new CNZipOutputStream(fout,"GB2312");	//�õ�ѹ�������
			String filename=jtfSourceFile.getText();
			String entryname=filename.substring(filename.lastIndexOf("\\")+1); //�õ��ļ���
			ZipEntry entry=new ZipEntry(entryname); //ʵ������Ŀ�б�
			cnout.putNextEntry(entry); //��ZIP��Ŀ�б�д�������
			byte[] buf=new byte[1024]; //�趨���뻺�����ߴ�
			int num;
			while ((num=fin.read(buf))!=-1){  //����ļ�δ����
				cnout.write(buf,0,num);	//д�뻺������
			}
			cnout.close();	//�ر�ѹ�������
			fout.close();	//�ر��ļ������
			fin.close();	//�ر��ļ�������
			showMessage("ѹ���ɹ�");	//��ʾ������Ϣ

		}
		catch (Exception ex){
			ex.printStackTrace();	//��ӡ������Ϣ
			showMessage("ѹ��ʧ��");
		}
	}

	public void unZipFile(String source,String target){	//��ѹ�ļ�
		try{
			FileInputStream fin=new FileInputStream(source);	//�����ļ�������
			GZIPInputStream gzin=new GZIPInputStream(fin);	//�õ�ѹ��������
			FileOutputStream fout=new FileOutputStream(target);	//�õ��ļ������
			byte[] buf=new byte[1024];	//��������С
			int num;
			while ((num=gzin.read(buf,0,buf.length))!=-1) { //����ļ�δ����
				fout.write(buf,0,num);	//д�뻺�����ݵ������
			}
			gzin.close();	//�ر�ѹ��������
			fout.close();  //�ر��ļ������
			fin.close();   //�ر��ļ�������
			showMessage("��ѹ�ɹ�");  //��ʾ������Ϣ
		}
		catch (Exception ex){
			ex.printStackTrace();  //��ӡ������Ϣ
			showMessage("��ѹ�ɹ�");
		}
	}

	class SelectFileListener implements ActionListener {	//�ļ�ѡ����¼�����
		public void actionPerformed(ActionEvent event) {
			if (fileChooser.showOpenDialog(CNZipDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
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

	private void showMessage(String message){
		JOptionPane.showMessageDialog(CNZipDemo.this,message); //��ʾ��Ϣ
	}

	public static void main(String[] args){
		new CNZipDemo();
	}
}
