import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

//ɭ��״�Ĺ�ϵͼ

public class JTreeDemo extends JFrame{
	JTextField jtfInfo; //�ı���,������ʾ����Ľڵ�����
	
	public JTreeDemo(){
		super("ɭ��״�Ĺ�ϵͼ");  //���ø��๹�캯��
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("����"); //���ɸ��ڵ�
		DefaultMutableTreeNode node1=new DefaultMutableTreeNode("����"); //���ɽڵ�һ
		node1.add(new DefaultMutableTreeNode("Ĭ��·��")); //�����½ڵ㵽�ڵ�һ��
		node1.add(new DefaultMutableTreeNode("����ѡ��"));
		root.add(node1);  //���ӽڵ�һ�����ڵ���
    	root.add(new DefaultMutableTreeNode("����"));    
    	root.add(new DefaultMutableTreeNode("��ʾ����"));  
    	root.add(new DefaultMutableTreeNode("��ӡ"));    
    	
		JTree tree = new JTree(root);  //�õ�JTree��ʵ��			
   	    DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)tree.getCellRenderer(); //�õ�JTree��Renderer
    	renderer.setLeafIcon(null); //����Ҷ�ӽڵ�ͼ��Ϊ��
    	renderer.setClosedIcon(null);  //���ùرսڵ��ͼ��Ϊ��
    	renderer.setOpenIcon(null); //���ô򿪽ڵ��ͼ��Ϊ��
    	
    	tree.addTreeSelectionListener(new TreeSelectionListener() {  //ѡ��ڵ���¼�����
        public void valueChanged(TreeSelectionEvent evt) {
            TreePath path = evt.getPath();  //�õ�ѡ��·��
            String info=path.getLastPathComponent().toString(); //�õ�ѡ��Ľڵ�����
			jtfInfo.setText(info);  //���ı�������ʾ����
        }
   		});


		JScrollPane jsp=new JScrollPane(tree); //����JTree����������
		jtfInfo=new JTextField(); //ʵ�����ı���
		jtfInfo.setEditable(false); //�ı��򲻿ɱ༭
		getContentPane().add(jsp,BorderLayout.CENTER);  //���������������
		getContentPane().add(jtfInfo,BorderLayout.SOUTH);
		
				
		setSize(250,200);  //���ô��ڳߴ�
		setVisible(true);  //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	
	public static void main(String[] args){
		new JTreeDemo();
	}
}