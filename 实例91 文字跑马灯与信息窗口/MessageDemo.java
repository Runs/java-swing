import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

// �������������Ϣ����

public class MessageDemo extends MIDlet implements CommandListener {

	private Display display; // �豸����ʾ��
	private Form form; // ��
	private Command cancel; // ȡ������
	private String message="����Խ��Խ������绰�͸�����������ʼ���뵽��Ϣ���ٹ�·֮�ϣ����ƶ��豸�Ϸ���Webվ����Խ��Խ��Ҫ��Java�����������豸��С�͵Ĵ����������Ⱥӣ��������ڿ����ֻ���������������΢���豸Ӧ�ó�����������ԡ�";
	private StringItem messageItem; //��Ϣ��ʾItem


	public MessageDemo() {
		form = new Form("��Ϣ��ʾ");
		cancel = new Command("ȡ��", Command.CANCEL, 1);
		messageItem=new StringItem("",message);
	}

	// ���س�����MIDlet�ĳ��󷽷�startApp()
	protected void startApp() {
		display = Display.getDisplay(this); //ȡ���豸����ʾ��
		Ticker ticker = new Ticker("ʹ��J2ME�����������Ӧ�ó���"); // ����������
		form.setTicker(ticker); // �ѹ������ӵ�����
		messageItem.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_PLAIN,Font.SIZE_LARGE)); //������ʾ����
		form.append(messageItem); // ����ʾ��Ϣ��ӵ�����
		form.addCommand(cancel); // Ϊ������ȡ������
		form.setCommandListener(this); // Ϊ���������������
		display.setCurrent(form); // ��ʾ��
	}

	// ���س�����MIDlet�ĳ��󷽷�pauseApp()
	protected void pauseApp() {
	}

	// ���س�����MIDlet�ĳ��󷽷�destroyApp()
	protected void destroyApp(boolean u) {
		notifyDestroyed();
	}

	// ʵ�ֽӿ�CommandListener�ķ���
	public void commandAction(Command c, Displayable d) {
		if (c == cancel) {
			destroyApp(false); // ���ٳ���
			notifyDestroyed();
		}

	}
}
