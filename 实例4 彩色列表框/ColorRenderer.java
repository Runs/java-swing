import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

//��ɫ�б���Renderer,��ʵ�ֽӿ�ListCellRenderer
public class ColorRenderer extends JLabel implements ListCellRenderer {

	//ʵ�ֽӿ��е�getListCellRendererComponent����
   public Component getListCellRendererComponent(JList list, Object obj, int row, boolean sel, boolean hasFocus) {
		if (hasFocus || sel) {   //����ѡ��ʱ�ı߽�
      	setBorder(new MatteBorder(2, 10, 2, 10, list.getSelectionBackground()));
      }
      else {  //����δѡ��ʱ�ı߽�
         setBorder(new MatteBorder(2, 10, 2, 10, list.getBackground()));
      }
      Color c=(Color)obj;  //�õ����е���ɫֵ
      setForeground(c);  //������ɫ
      setText(c.toString());  //�����ı�
		return this;
	}
}