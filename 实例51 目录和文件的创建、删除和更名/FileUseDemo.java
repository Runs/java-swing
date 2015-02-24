import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

//Ŀ¼���ļ��Ĵ�����ɾ���͸���

public class FileUseDemo extends JFrame{

	JTextField jtfPath;

	public FileUseDemo(){
		super("Ŀ¼���ļ��Ĵ�����ɾ���͸���");	//���ø��๹�캯��

		jtfPath=new JTextField(16);	//ʵ�����ļ�·�������
		JButton jbFile=new JButton("ѡ��");	//ʵ�����ļ�ѡ��ť
		JPanel panel=new JPanel();	//ʵ�������,�������������Ͱ�ť
		panel.add(new JLabel("�ļ���: "));	//������������
		panel.add(jtfPath);
		panel.add(jbFile);
		JPanel panel2=new JPanel();
		panel2.add(new JButton(new CreateFileAction()));  //ʵ������ť,�����ӵ����2��
		panel2.add(new JButton(new RenameFileAction()));
		panel2.add(new JButton(new DeleteFileAction()));

		jbFile.addActionListener(new ActionListener(){	//ѡ���ļ���ť�¼�����
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser=new JFileChooser();		//ʵ�����ļ�ѡ����
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);  //�����ļ�ѡ��ģʽ,�˴�Ϊ�ļ���Ŀ¼����
				if (fileChooser.showOpenDialog(FileUseDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ���Ŀ¼�ľ���·��
					jtfPath.setText(fileName);
				}
			}
		});

		Container container=getContentPane();	//�õ�����
		container.add(panel,BorderLayout.NORTH);	//�������������
		container.add(panel2,BorderLayout.CENTER);

		setSize(330,120);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
	}

	class CreateFileAction extends AbstractAction {	//�������ļ�
		public CreateFileAction() {
		    super("����");	//���ø��๹�캯��
		}
		public void actionPerformed(ActionEvent e) {
			String filename=jtfPath.getText();	//�������õ��ļ���
			File sfile=new File(filename);	//ʵ����һ���ļ�
			try{
				if (!sfile.exists()){	//����ļ�������
					if (sfile.createNewFile()==true){	//�����ļ��ɹ�
						showMessage(filename+" ���ļ������ɹ�.");	//��ʾ��Ϣ
					}
					else{
						showMessage(filename+" ���ļ�����ʧ��.");
					}
				}
				else{
					showMessage(filename+" ԭ�ļ��Ѵ���.");
				}
			}
			catch (Exception ex){
				ex.printStackTrace();	//��ӡ������Ϣ
			}
		}
	}

	class RenameFileAction extends AbstractAction {	//�������ļ�
		public RenameFileAction() {
		    super("������");
		}
		public void actionPerformed(ActionEvent e) {
			String filename=JOptionPane.showInputDialog("�������ļ���"); //�������ļ����Ի���
			File sfile=new File(jtfPath.getText()); //Դ�ļ�
			File f=new File(filename);	//���ļ�
			sfile.renameTo(f);	//������
			showMessage(sfile.getName()+" �������ɹ�."); //��ʾ��Ϣ
			jtfPath.setText(f.getAbsolutePath());	//�����������ļ���
		}
	}

	class DeleteFileAction extends AbstractAction {	//ɾ���ļ�
		public DeleteFileAction() {
		    super("ɾ��");
		}
		public void actionPerformed(ActionEvent e) {
			File sfile=new File(jtfPath.getText()); //Դ�ļ�
			try{
				sfile.delete();	//ɾ���ļ�
				showMessage(sfile.getName()+" ɾ���ɹ�.");	//��ʾ��Ϣ

			}
			catch(Exception ex){
				showMessage(sfile.getName()+" ɾ��ʧ��.");
				ex.printStackTrace();
			}
		}
	}

	private void showMessage(String message){
		JOptionPane.showMessageDialog(FileUseDemo.this,message); //��ʾ��Ϣ
	}

	public static void main(String[] args){
		new FileUseDemo();
	}
}
