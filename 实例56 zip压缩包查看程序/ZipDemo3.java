import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;

//zipѹ�����鿴����

public class ZipDemo3 extends JFrame{
	JFileChooser fileChooser; //�ļ�ѡ����
	JTextField jtfTarget; //���鿴�ļ�·��
	JButton jbSelected; //ѡ���ļ���ť
	JTextArea jtaInfo;  //��Ϣ��ʾ�ı���

	public ZipDemo3(){
		super("zipѹ�����鿴����");	//���ø��๹�캯��

		fileChooser=new JFileChooser();  //ʵ�������
		jtfTarget=new JTextField(18);
		jbSelected=new JButton("ѡ��");
		jtaInfo=new JTextArea();
		JPanel panel=new JPanel(); //ʵ�������
		panel.add(new JLabel("Ŀ���ļ�"));
		panel.add(jtfTarget);  //��������������
		panel.add(jbSelected);
		JScrollPane jsp=new JScrollPane(jtaInfo);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));  //���ñ߽�
		Container container=getContentPane();  //��������
		container.add(panel,BorderLayout.NORTH);  //���������������
		container.add(jsp,BorderLayout.CENTER);

		jbSelected.addActionListener(new ActionListener(){	//�ļ�ѡ��ť�¼�����
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(ZipDemo3.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ��ľ���·��
					jtfTarget.setText(fileName);  //����Ŀ���ļ���
					showFileInfo();  //��ʾ�ļ�����
	      	}
			}
		});
		jtfTarget.addActionListener(new ActionListener(){	//ѹ����ť�¼�����
			public void actionPerformed(ActionEvent event) {
					showFileInfo();  //��ʾ�ļ�����
	      	}
		});

		setSize(350,250);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}

	public void showFileInfo(){  //��ʾѹ���ļ�����
		try{
			jtaInfo.append("�ļ���\t�ļ���С\t��������\n"); //������Ϣ����ʾ�ı���
			ZipFile zfile=new ZipFile(jtfTarget.getText());  //ʵ����ѹ���ļ�
			ZipEntry entry;
			Enumeration enu=zfile.entries(); //�õ�ѹ����Ŀ��ö�ٶ���
			while (enu.hasMoreElements()){  //����ö����Ŀ
				entry=(ZipEntry) enu.nextElement(); //�õ�ѹ����Ŀ
				jtaInfo.append(entry.getName()+"\t"); //������ʾ��Ϣ
				jtaInfo.append(entry.getSize()+"\t");
				jtaInfo.append(new Date(entry.getTime())+"\n");
			}
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args){
		new ZipDemo3();
	}
}