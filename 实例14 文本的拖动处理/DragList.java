import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;

public class DragList extends JList implements DragGestureListener, DragSourceListener{
	
	DragSource dragSource=DragSource.getDefaultDragSource(); //�϶�Դ
	public DragList(Object[] data){  //���캯��
		super(data); 
		int action = DnDConstants.ACTION_COPY_OR_MOVE; //�϶�����
		dragSource.createDefaultDragGestureRecognizer(this,action,this); //�����϶�ʶ��
	}
	
	public void dragGestureRecognized(DragGestureEvent dge) {
		try{
			Transferable trans = new StringSelection(this.getSelectedValue().toString()); //�õ��϶���Transferable����
			dge.startDrag(DragSource.DefaultCopyNoDrop,trans,this);  //��ʼ�϶�����
		}catch(Exception err){
			err.printStackTrace();  //���������Ϣ
		}
	}
	
	public void dragEnter(DragSourceDragEvent dragSourcede) {  //�϶����봦��
		DragSourceContext dragSourceContext = dragSourcede.getDragSourceContext(); //�õ��϶������Ķ���
		int action = dragSourcede.getDropAction(); //�õ��϶�����
		if ((action&DnDConstants.ACTION_COPY)!=0)  //�ж���������
			dragSourceContext.setCursor(DragSource.DefaultCopyDrop);  //���ù������
		else
			dragSourceContext.setCursor(DragSource.DefaultCopyNoDrop);
	}
	public void dragOver(DragSourceDragEvent dragSourcede) {
	}
	public void dropActionChanged(DragSourceDragEvent dragSourcede) {
	}
	public void dragExit(DragSourceEvent dragSourcee) {
	}
	public void dragDropEnd(DragSourceDropEvent dragSourcede) {
	}
}
