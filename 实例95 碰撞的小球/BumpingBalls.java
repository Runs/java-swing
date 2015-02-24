// ��ײ��С��
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class BumpingBalls extends MIDlet implements CommandListener {

    Display display;  // �豸����ʾ��
    BallCanvas canvas;  // ������ʾС�����Ļ 
    private Command exitCommand = new Command("Exit", Command.EXIT, 1);  //�˳�����

    public BumpingBalls() {
		display = Display.getDisplay(this);	 //ȡ���豸����ʾ��
		canvas = new BallCanvas(display);  // ʵ����canvas����
		canvas.addCommand(exitCommand); // Ϊcanvas�����˳�����
		canvas.setCommandListener(this); // Ϊcanvas�������������
    }

	// ���س�����MIDlet�ĳ��󷽷�startApp()
    public void startApp() throws MIDletStateChangeException {
		canvas.start();
    }    

	// ���س�����MIDlet�ķ���destroyApp()
    public void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		canvas.destroy();
    }
    
	// ���س�����MIDlet�ķ���pauseApp()
    public void pauseApp(){}

	// ʵ�ֽӿ�CommandListener�ķ���
    public void commandAction(Command c, Displayable s) {
			if (c == exitCommand) {
		    try {
				destroyApp(false);  // ���ٳ���
				notifyDestroyed();  // ֪ͨ���ٳ���
		    } catch (MIDletStateChangeException ex) {
		    }
		}
    }
}
