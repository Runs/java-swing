import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.util.*;

//�ֻ�����

public class MobileCalendar  extends MIDlet implements CommandListener
{
	// �˳�����
    private final static Command EXIT = new Command("Exit", Command.EXIT, 1);
    private Form form;  // �����������ں�ʱ����ı�
    
    public MobileCalendar() {
        form = new Form("���ں�ʱ����Ϣ");
    }

	// ���س�����MIDlet�ĳ��󷽷�startApp()
    protected void startApp() {	    
	    Date dd = new Date();  // ��ʾ��ǰ�����ں�ʱ��	
	    TimeZone tz = TimeZone.getTimeZone("GMT+08:00");   // ָ��ʱ��Ϊ������	 
	    DateField dateAndTime = new DateField("���ں�ʱ��", DateField.DATE_TIME,tz);  // �������������ֶκ�ʱ���ֶε�DateField����dateAndTime   	      	    
	    dateAndTime.setDate(dd);   // ���ó�ʼ���ں�ʱ��
	    form.append(dateAndTime);  // ���dateAndTime����form
	   
		DateField date = new DateField("����", DateField.DATE);  // �������������ֶε�DateField����date
		date.setDate(dd);   // ����dateAndTime�ĳ�ʼ����
	    form.append(date);  // ���date����form
	    
	    DateField time = new DateField("ʱ��", DateField.TIME); // ��������ʱ���ֶε�DateField����time
	    form.append(time);  // ���time����form
	            
	    form.addCommand(EXIT);  // ����˳������form
	    form.setCommandListener(this);  // Ϊ��form�������������
        Display.getDisplay(this).setCurrent(form);  // ��ʾ��form
    }    

	// ���س�����MIDlet�ĳ��󷽷�destroyApp()
    protected void destroyApp(boolean unconditional) { }    

	// ���س�����MIDlet�ĳ��󷽷�pauseApp()
    protected void pauseApp() {}   
    
	// ʵ�ֽӿ�CommandListener�ķ���
    public void commandAction(Command c, Displayable d) {
        if (c == EXIT) {
            destroyApp(false);  // ���ٳ���
            notifyDestroyed();
        }
    }    
}
