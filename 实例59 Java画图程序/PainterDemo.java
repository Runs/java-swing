import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PainterDemo extends JFrame{
	
	JToggleButton[] button=new JToggleButton[3];  //��ť��
	PainterPanel painter=new PainterPanel(); //��ͼ���
	
	public PainterDemo(){
		super("Java��ͼ����"); //���ø��๹�캯��
		
		String[] buttonName={"ֱ��","��Բ","����"}; //��ť����
		DrawShapeListener buttonListener=new DrawShapeListener(); //��ť�¼�

		JToolBar toolBar=new JToolBar(); //ʵ����������
		ButtonGroup buttonGroup=new ButtonGroup(); //ʵ������ť��
		for (int i=0;i<button.length;i++){
			button[i]=new JToggleButton(buttonName[i]); //ʵ������ť
			button[i].addActionListener(buttonListener); //���Ӱ�ť�¼�����
			buttonGroup.add(button[i]); //���Ӱ�ť����ť��
			toolBar.add(button[i]);	 //���Ӱ�ť��������
		}

		Container container=getContentPane(); //�õ���������
		container.add(toolBar,BorderLayout.NORTH); //���������������
		container.add(painter,BorderLayout.CENTER);			
		
		setSize(300,200);  //���ô��ڳߴ�
		setVisible(true);  //���ô���Ϊ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //�رմ���ʱ�˳�����		
	}
	
	class DrawShapeListener implements ActionListener{  //��ť�¼�����
		public void actionPerformed(ActionEvent e){
			for (int i=0;i<button.length;i++){ 
				if (e.getSource()==button[i]){  //�ж��������ĸ���ť
					painter.drawShape(i); //����ͼ��
				}				
			}			
		}
	}
			
	public static void main(String[] args){
		new PainterDemo();
	}
}
