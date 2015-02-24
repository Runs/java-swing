import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;

//��ѹ��������ȡ�ļ�

public class ZipExtractDemo extends JFrame{
	JFileChooser fileChooser; //�ļ�ѡ����
	JTextField jtfTarget; //����ѹ�ļ�·��
	JButton jbSelected; //ѡ���ļ���ť
	JList files;  //��Ϣ��ʾ�б��
	JButton jbExtract;  //��ѹ��ť
	ZipFile zFile; 

	public ZipExtractDemo(){
		super("��ѹ��������ȡ�ļ�");	//���ø��๹�캯��

		fileChooser=new JFileChooser();  //ʵ�������
		jtfTarget=new JTextField(13);
		jbSelected=new JButton("ѡ��");
		jbExtract=new JButton("��ѹ");
		files=new JList();
		JPanel panel=new JPanel(); //ʵ�������
		panel.add(new JLabel("Ŀ���ļ�"));
		panel.add(jtfTarget);  //��������������
		panel.add(jbSelected);
		panel.add(jbExtract);
		JScrollPane jsp=new JScrollPane(files);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));  //���ñ߽�
		Container container=getContentPane();  //��������
		container.add(panel,BorderLayout.NORTH);  //���������������
		container.add(jsp,BorderLayout.CENTER);

		jbSelected.addActionListener(new ActionListener(){	//�ļ�ѡ��ť�¼�����
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(ZipExtractDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ��ľ���·��
					jtfTarget.setText(fileName);  //����Ŀ���ļ���
					showFiles();  //��ʾѹ��������
	      	}
			}
		});
		
		jbExtract.addActionListener(new ActionListener(){	//����ļ���ť�¼�����
			public void actionPerformed(ActionEvent event) { 
					extractFile(files.getSelectedValue().toString());  //��ѹָ���ļ�
	      	}
		});

		setSize(350,250);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}
	
	public void showFiles(){
		try{
		zFile=new ZipFile(jtfTarget.getText()); //�õ�ѹ���ļ�ʵ��
		ZipEntry entry;
		Vector vec=new Vector(); //�ļ�ö��
			Enumeration enu=zFile.entries(); //�õ�ѹ����Ŀ��ö�ٶ���
			while (enu.hasMoreElements()){  //����ö����Ŀ
				entry=(ZipEntry) enu.nextElement(); //�õ�ѹ����Ŀ
				vec.add(entry.getName());	//�����ļ���Vector��		
			}
			files.setListData(vec); //�����ļ��б������
			files.getSelectedValues();
		}
		catch (Exception ex){
			ex.printStackTrace();  //���������Ϣ
		}		
	}

	public void extractFile(String name){  //��ѹ�ļ�
		try{
		ZipEntry entry=zFile.getEntry(name); 
		entry.getComment();
		entry.getCompressedSize();
		entry.getCrc();
		entry.isDirectory();'
		entry.getSize();
		entry.getMethod();
		InputStream in=zFile.getInputStream(entry); //�õ��ļ�������
		File file=new File(name); //ʵ�����ļ�����
		FileOutputStream out=new FileOutputStream(file); //�õ��ļ������
		byte[] buffer=new byte[1024]; //��������С
		int length;
		while((length = in.read(buffer)) != -1) {  //ѭ����ȡ����
			out.write(buffer, 0, length);  //д���ݵ������
		}
			JOptionPane.showMessageDialog(ZipExtractDemo.this,"��ѹ�ɹ�,��ѹ����"+file.getAbsolutePath());	
		in.close(); //�ر�������
		out.close(); //�ر������	
		} catch (Exception ex){}
	}

	public static void main(String[] args){
		new ZipExtractDemo();
	}
}
