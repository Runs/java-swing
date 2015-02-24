import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class ScreenDemo extends MIDlet implements CommandListener {

	private Display display; // �豸����ʾ��
	private TextField userName; // ������ʾ�û������ı���
	private TextField password; // ������ʾ������ı���
	private Form form; // ��
	private Command cancel; // ȡ������
	private Command login; // ��¼����

	public ScreenDemo() {
		userName = new TextField("�û���", "", 10, TextField.ANY);  //ʵ�����ı���
		password = new TextField("��   ��", "", 10, TextField.PASSWORD);
		form = new Form("�û���¼"); //ʵ������
		cancel = new Command("ȡ��", Command.CANCEL, 1); //ʵ��������
		login = new Command("��¼", Command.OK, 2);
	}

	// ���س�����MIDlet�ĳ��󷽷�startApp()
	protected void startApp() {
		display = Display.getDisplay(this); //ȡ���豸����ʾ��
		Ticker ticker = new Ticker("�����û���������"); // ����������
		form.setTicker(ticker); // �ѹ������ӵ�����
		form.append(userName); // ���û����ı�����ӵ�����
		form.append(password); // �������ı�����ӵ�����
		form.addCommand(cancel); // Ϊ������ȡ������
		form.addCommand(login); // Ϊ�����ϵ�¼����
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

	// ��¼����
	protected void validateUser(String name, String password) {
		if (name.equals("1") && password.equals("1")) {
			passed();
		} else {
			tryAgain();
		}
	}

	// ��¼�ɹ�ʱ����ʾ��¼�ɹ���Ϣ
	protected void passed() {
		// ��¼�ɹ�����
		Alert pass = new Alert("��¼��Ϣ", "���ѳɹ���¼", null, AlertType.ERROR);
		pass.setTimeout(Alert.FOREVER); // ���þ���Ϊģʽ����
		display.setCurrent(pass, form); // ��ʾ�ǵ�¼�ɹ�������Ȼ����ʾ��¼����
	}

	// ��¼����ʱ����ʾ������Ϣ�����ص�¼����
	protected void tryAgain() {
		// ��¼���󾯱�
		Alert error = new Alert("��¼����", "�����������û���������", null, AlertType.ERROR);
		error.setTimeout(Alert.FOREVER); // ���þ���Ϊģʽ����
		userName.setString(""); // �����û���
		password.setString(""); // ��������
		display.setCurrent(error, form); // ��ʾ��¼���󾯱���Ȼ����ʾ��¼����
	}

	// ʵ�ֽӿ�CommandListener�ķ���
	public void commandAction(Command c, Displayable d) {
		if (c == cancel) {
			destroyApp(false); // ���ٳ���
			notifyDestroyed();
		} else if (c == login) {
			validateUser(userName.getString(), password.getString());
		}
	}
}
