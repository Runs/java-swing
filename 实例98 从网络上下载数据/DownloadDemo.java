import java.io.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

// ����������������

public class DownloadDemo extends MIDlet {
	private Display display; //��ʾ��
	String url = "http://women.sohu.com/xingxiang/newxz/mz/images/kuhua.jpg"; //�����ʵĵ�ַ
	
	public DownloadDemo() {
		display = Display.getDisplay(this); //��ȡ��ʾ��
	}
	
	public void startApp() {
			getConnection(url);
	}
	
	public void pauseApp() {
	}
	
	public void destroyApp(boolean unconditional) {
	}
	
	public void getConnection(String url){ 
		InputStream in;
		try{	
			in = (InputStream) Connector.openInputStream(url); //��������
			Image img = null; //Image����
  			ByteArrayOutputStream bos = new ByteArrayOutputStream(); //�ֽ�������
  			int ch;
  			while ((ch = in.read()) != -1){ //��ȡ�ֽ���
    			bos.write(ch); //д�ֽڵ��������
    		}  
  			byte imageData[] = bos.toByteArray(); // ��ȡͼ������
      		in.close();  //�ر�������
  			img = Image.createImage(imageData, 0, imageData.length); //����Image����
  			ImageItem imageItem = new ImageItem(null,img,ImageItem.LAYOUT_CENTER,"img"); //����ImageItem����

			Form form = new Form("����ͼ��");  // ������
			form.append(imageItem); //������ʾͼ�񵽱���
			display.setCurrent(form); //���õ�ǰ��ʾΪ��
		}
		catch (Exception ex){}
	}
}