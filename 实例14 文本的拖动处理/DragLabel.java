import javax.swing.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;

public class DragLabel extends JLabel implements DropTargetListener{
	
	public DragLabel(String str){
		super(str);  //���ø��๹�캯��
	}
	
	public void dragEnter(DropTargetDragEvent evt) {
	}
	public void dragOver(DropTargetDragEvent evt) {
	}
	public void dropActionChanged(DropTargetDragEvent evt) {
	}
	public void dragExit(DropTargetEvent evt) {
	}
	public void drop(DropTargetDropEvent evt) {  //�϶���������
		try{
			Transferable trans = evt.getTransferable(); //����Transferable����
			if (evt.isDataFlavorSupported(DataFlavor.stringFlavor)){ //�Ƿ�֧���϶�
				evt.acceptDrop(evt.getDropAction()); //�����϶�
				String s = (String) trans.getTransferData(DataFlavor.stringFlavor); //�õ��϶�����
				setText(s); //���ñ�ǩ���ı�
				evt.dropComplete(true); //�����϶�
			}else{
				evt.rejectDrop(); //�ܾ�����

			}
		}catch(Exception err){
			err.printStackTrace(); //���������Ϣ
		}
	}

}
