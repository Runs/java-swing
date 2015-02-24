import java.io.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

// ����Http����

public class HttpConnectionDemo extends MIDlet {
	private Display display; //��ʾ��
	String url = "http://www.baidu.com/index.htm"; //�����ʵĵ�ַ
	
	public HttpConnectionDemo() {
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
		try{	
			ContentConnection connection = (ContentConnection) Connector.open(url); //��ȡ����
			TextBox tb = null; //��ʾ�ı���TextBox����
			StringBuffer sb = new StringBuffer(); //�ַ�������			
			InputStream is = connection.openInputStream(); //��ȡ������
			int ch; 
			while((ch = is.read()) != -1) { //��ȡ�ַ�
				sb.append((char)ch); //�����ַ���������
			}
			tb = new TextBox("ȡ���ı���Ϣ", sb.toString(), 1024, 0); //��ʾ�ı���Ϣ
			display.setCurrent(tb); //���õ�ǰ����ʾ��Ļ
		}
		catch (Exception ex){}
	}

}