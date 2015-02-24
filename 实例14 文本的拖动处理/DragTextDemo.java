import java.awt.*;
import javax.swing.*;
import javax.swing.JSplitPane;
import java.awt.dnd.*;

//�ı����϶�����

public class DragTextDemo extends JFrame{
	
	public DragTextDemo(){
		super("�ı����϶�����"); //���ø��๹�캯��
		
		String[] data = {"one", "two", "three", "four"}; //�ַ�����,���ڹ����б��		
		DragList list=new DragList(data); //�б��ʵ��
		JTextArea jta=new JTextArea(8,20); //�ı���ʵ��
		DragLabel label=new DragLabel("�϶�Ŀ��"); //��ǩʵ��
		
		jta.setLineWrap(true); //�����Զ�����
		jta.setDragEnabled(true); //�ı�����϶�
		new DropTarget(label,DnDConstants.ACTION_COPY,label); //ʵ�����϶�Ŀ��Ϊ��ǩ
		
		Container container=getContentPane(); //�õ�����
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT); //ʵ�����ָ����
		split.setDividerLocation(140); //���÷ָ�λ��
		split.add(list); //����������ָ����
		split.add(jta);
		container.add(split,BorderLayout.CENTER);  //���������������		
		container.add(label,BorderLayout.SOUTH);
		
		setSize(300,150);  //���ô��ڳߴ�
		setVisible(true);  //���ô���Ϊ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����
	}
	
	public static void main(String[] args){
		new DragTextDemo();
	}
}
