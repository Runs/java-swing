// ��RMS��¼������Ϣ
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.*;
import java.util.Enumeration;
import java.util.*;

public class PersonalInfo extends MIDlet implements CommandListener {
	RecordStore recordStore =null;
	Display display = null;  // �豸����ʾ��
	List list = null;	
	TextField nameField;  // �����ı���
	TextField phoneField;  //�绰�����ı���
	TextField emailField;  //�����ʼ��ı���
	
	String name, phone, email;
	private int[] recID;
	boolean isAdd;  // ֵΪtrueʱΪ���Ӽ�¼������Ϊ�޸ļ�¼
	
	Ticker ticker = new Ticker("������Ϣ  ");  //ʵ����Ticker����
	static final Command EXIT = new Command("�˳�",Command.STOP,1); //ʵ��������
	static final Command BACK = new Command("����",Command.BACK,2);
	static final Command VIEW = new Command("�鿴���޸ļ�¼",Command.OK,3);
	static final Command ADD = new Command("����¼�¼",Command.OK,3);
	static final Command DEL = new Command("ɾ����ǰ��¼",Command.OK,3);
	static final Command DEL_ALL = new Command("ɾ�����м�¼",Command.OK,3);
	static final Command SAVE = new Command("����",Command.OK,3);
	
	public PersonalInfo() {
	}

	// ���س�����MIDlet�ĳ��󷽷�startApp()
	public void startApp() {
		display = Display.getDisplay(this); //��ȡ��ʾ��
		try {
			recordStore = RecordStore.openRecordStore("PersonalInfo", true); //ʵ������¼�洢��
		} catch(Exception e) {
		}
        list = new List("������Ϣ",Choice.IMPLICIT); //ʵ�����б�
		list.addCommand(EXIT); //���ӿ�������
		list.addCommand(ADD);
		list.setCommandListener(this);
		list.setTicker(ticker);        
        list.setCommandListener(this);
		listRec();
	}
	
	// ���س�����MIDlet�ķ���pauseApp()
	public void pauseApp() {
	}
	
	// ���س�����MIDlet�ķ���destroyApp()
	public void destroyApp(boolean unconditional) {
		
	}

	public void listRec() {
        list.deleteAll(); //ɾ���б�Ԫ��
        try {
			int numRec = recordStore.getNumRecords(); //��ȡ��¼��
			if(numRec > 0) {
				list.addCommand(VIEW);
				list.addCommand(DEL);
				list.addCommand(DEL_ALL);
				recID = new int [numRec];
				RecordEnumeration re = recordStore.enumerateRecords(null,null,true); //��¼ö�ٶ���
				int i = 0;
				while(re.hasNextElement()) { //������¼
					recID[i] = re.nextRecordId();
					name = PersonalRecord.getName(recordStore.getRecord(recID[i]));  //��ȡ����
					list.append(name,null); //�����б�Ԫ��
					i++;
				}
			} else {
				list.removeCommand(VIEW);
				list.removeCommand(DEL);
				list.removeCommand(DEL_ALL);				
			}
        } catch (RecordStoreException rse) {
        }
		display.setCurrent(list);
	 }
	
	public void viewRecScreen() {  //��ʾ��¼��Ϣ
		Form view = new Form("������Ϣ"); //������ 
        nameField = new TextField("����:",name,20,TextField.ANY);  //�����ı���
        phoneField = new TextField("�绰:",phone,20,TextField.NUMERIC);
        emailField = new TextField("E-Mail:",email,20,TextField.EMAILADDR);
        view.append(nameField);  //�����ı��򵽱�
        view.append(phoneField); 
        view.append(emailField);
        view.addCommand(BACK);
        view.addCommand(SAVE);
        view.setCommandListener(this);
        display.setCurrent(view); //���õ�ǰ��ʾ��Ļ
	}
	
	public void viewRec() {
    	isAdd = false;
    	try {
    		byte[] b = recordStore.getRecord(recID[list.getSelectedIndex()]);
	    	name = PersonalRecord.getName(b); //��ȡ����
	        phone = PersonalRecord.getPhone(b); //��ȡ�绰
	        email = PersonalRecord.getEmail(b); //��ȡ�����ʼ�
	    } catch(Exception e) {
	    } 
	    viewRecScreen(); //��ʾ��Ϣ
	}
	
	public void addRec() {  //���Ӽ�¼
    	isAdd = true;
    	name = null;
        phone = null;
        email = null;
	    viewRecScreen();
	}
	
	public void saveRec() { //�洢��¼
		name = nameField.getString();  //��ȡ�û����� 
		phone = phoneField.getString();
		email = emailField.getString();
       	if (!(name.trim().equals(""))) {       		
	       	byte[] b = PersonalRecord.createRecord(name,phone,email); //����һ����¼
	    	try {
	    		if(isAdd) {
	    			recordStore.addRecord(b,0,b.length); //���Ӽ�¼
		    	} else {
	        		recordStore.setRecord(recID[list.getSelectedIndex()],b,0,b.length); //�޸ļ�¼
	        	}
		    } catch(Exception e) {
		    }
		 }
	}
	
	public void delRec(int i) { 
    	try{
	        recordStore.deleteRecord(i); //ɾ����¼
    	} catch(Exception e) {
    	}		
	}
	
	public void delAllRec() {  //ɾ�����м�¼
        try {
            String dbName = recordStore.getName();
            recordStore.closeRecordStore();  //�رռ�¼�洢��
            RecordStore.deleteRecordStore(dbName); //ɾ����¼�洢��
        } catch (RecordStoreException rsnoe) {
        }
	}
	
	// ʵ�ֽӿ�CommandListener�ķ���
	public void commandAction(Command c, Displayable d) {
        if (c == ADD) {
        	addRec();
        } else if (c == List.SELECT_COMMAND || c == VIEW) {
			viewRec();
        } else if(c == DEL) {
        	delRec(recID[list.getSelectedIndex()]);
        	listRec();
        } else if (c == DEL_ALL) {
	        delAllRec();
        	startApp();
		} else if (c == EXIT) {
        	destroyApp(false);
        	notifyDestroyed();
        } else if (c == BACK) {
            display.setCurrent(list);
        } else if (c == SAVE) { 
        	saveRec();
        	listRec();
        }  
	}	
}