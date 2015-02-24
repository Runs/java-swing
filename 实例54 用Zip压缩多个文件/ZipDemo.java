import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;

//��ZIPѹ������ļ�

public class ZipDemo extends JFrame{
	JFileChooser fileChooser; //�ļ�ѡ����
	JList fileList;	//��ѹ�����ļ��б�
	Vector files;	//�ļ�����(��ѹ���ļ�)
	JButton jbAdd;	//�����ļ���ť
	JButton jbDelete; //ɾ���ļ���ť
	JButton jbZip; //ѹ����ť
	JTextField target; //Ŀ���ļ��ı���

	public ZipDemo(){
		super("��ZIPѹ������ļ�");	//���ø��๹�캯��
		fileChooser=new JFileChooser();	//ʵ�����ļ�ѡ����
		files=new Vector(); //ʵ�����ļ�����Vector
		fileList=new JList(files); //ʵ������ѡ���ļ��б�
		jbAdd=new JButton("����"); //ʵ������ť���
		jbDelete=new JButton("ɾ��");
		jbZip=new JButton("ѹ��");
		target=new JTextField(18);
		JPanel panel=new JPanel(); //ʵ�������,�������ɰ�ť
		panel.add(jbAdd);	//��������������
		panel.add(jbDelete);
		panel.add(jbZip);
		JPanel panel2=new JPanel();
		panel2.add(new JLabel("Ŀ���ļ�"));
		panel2.add(target);
		JScrollPane jsp=new JScrollPane(fileList);
		Container container=getContentPane(); //�õ�����
		container.add(panel2,BorderLayout.NORTH); //�������������
		container.add(jsp,BorderLayout.CENTER);
		container.add(panel,BorderLayout.SOUTH);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));  //���ñ߽�

		jbAdd.addActionListener(new ActionListener(){	//�����ļ���ť�¼�����
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(ZipDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ��ľ���·��
					files.add(fileName);  //�����ļ���Vector
					fileList.setListData(files); //�����ļ�ѡ���б������
      		}
			}
		});


		jbDelete.addActionListener(new ActionListener(){	//ɾ���ļ���ť�¼�����
			public void actionPerformed(ActionEvent event) {
				files.remove(fileList.getSelectedValue());	//��Vector���Ƴ�ѡ���ļ�
				fileList.setListData(files); //�����ļ�ѡ���б������
			}
		});

		jbZip.addActionListener(new ActionListener(){	//ѹ����ť�¼�����
			public void actionPerformed(ActionEvent event) {
				zipFiles(files.toArray(),target.getText()); //����ѹ���ļ�����
			}
		});

		setSize(330,250);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}

	public void zipFiles(Object[] sources,String target){ //ѹ���ļ�
		try{
			FileOutputStream fout=new FileOutputStream(target);	//�õ�Ŀ���ļ������
			ZipOutputStream zout=new ZipOutputStream(fout);	//�õ�ѹ�������
			byte[] buf=new byte[1024];//�趨���뻺�����ߴ�
			int num;
			FileInputStream fin=null;
			ZipEntry entry=null;
			for (int i=0;i<sources.length;i++){
				String filename=sources[i].toString(); //�õ���ѹ���ļ�·����
				String entryname=filename.substring(filename.lastIndexOf("\\")+1); //�õ��ļ���
				entry=new ZipEntry(entryname); //ʵ������Ŀ�б�
				zout.putNextEntry(entry); //��ZIP��Ŀ�б�д�������
				fin=new FileInputStream(filename); //��Դ�ļ��õ��ļ�������
				while ((num=fin.read(buf))!=-1){  //����ļ�δ����
					zout.write(buf,0,num);	//д�뻺������
				}
			}
			zout.close();	//�ر�ѹ�������
			fout.close();	//�ر��ļ������
			fin.close();	//�ر��ļ�������
			showMessage("ѹ���ɹ�");	//��ʾ������Ϣ

		}
		catch (Exception ex){
			ex.printStackTrace();	//��ӡ������Ϣ
			showMessage("ѹ��ʧ��");
		}
	}


	class SelectFileListener implements ActionListener {	//�ļ�ѡ����¼�����
		public void actionPerformed(ActionEvent event) {
			if (fileChooser.showOpenDialog(ZipDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
				String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ��ľ���·��
      	}
		}
	}

	private void showMessage(String message){
		JOptionPane.showMessageDialog(ZipDemo.this,message); //��ʾ��Ϣ
	}

	public static void main(String[] args){
		new ZipDemo();
	}
}