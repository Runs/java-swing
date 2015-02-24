import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

// �ֻ�����

public class MobileAlbum extends MIDlet implements CommandListener {
    // �����˳�����ͷ�������
    private final static Command EXIT = new Command("�˳�", Command.EXIT, 1);
    private final static Command BACK = new Command("����", Command.BACK, 1);
    private Display display;  // �豸����ʾ��
    private List mainList;  // ����ͼƬ�������б�
    private Form form;  // ������ʾͼ��ı�
    String[] pictureName = {
        "Title", 
        "Girl", 
        "Love",
        "Cow"
    };    
    String[] fileName = {
        "/title.jpg", 
        "/girl.jpg", 
        "/love.jpg",
        "/cow.jpg" 
    };
    
    public MobileAlbum() {
        display = Display.getDisplay(this); //ȡ���豸����ʾ��
    }

	// ���س�����MIDlet�ĳ��󷽷�startApp()
    protected void startApp() {
        int num = pictureName.length;  // ͼƬ����
        Image[] imageArray = new Image[num]; // �б��ͼ������
        try {
            Image icon = Image.createImage("/icon.png"); // ����ͼ��
	        for(int i=0;i<num;i++) {
	        imageArray[i] =icon;
	        }            
        } catch (java.io.IOException err) {
       	}

        mainList = new List("�ֻ�����",Choice.IMPLICIT,pictureName,imageArray);   // ʵ�����б�
        mainList.addCommand(EXIT); // Ϊ�б�����˳�����
        mainList.setCommandListener(this); // �������������
        display.setCurrent(mainList); // ��ʾ�б�
    }

	// ���س�����MIDlet�ĳ��󷽷�destroyApp()
    protected void destroyApp(boolean unconditional) {
    }

	// ���س�����MIDlet�ĳ��󷽷�pauseApp()
    protected void pauseApp() {
    }

	// ʵ�ֽӿ�CommandListener�ķ���
    public void commandAction(Command c, Displayable d) {
        if (d.equals(mainList)) {
            if (c == List.SELECT_COMMAND) {
            	getImage(((List)d).getSelectedIndex());  // ȡ��ͼƬ���ڱ���
                display.setCurrent(form); // ��ʾ��
            }
        } else {
            if (c == BACK) {
                display.setCurrent(mainList); // ��ʾ���б�
            }
        }

        if (c == EXIT) {
            destroyApp(false);  // ���ٳ���
            notifyDestroyed();
        }
    }
    
	// װ�ز���ʾͼ��
    protected void getImage(int index) {
        Image image =null;
        try {
            image = Image.createImage(fileName[index]);  // ��ָ���ļ�����һ���̶�ͼ��
        } catch (java.io.IOException err) {}        
        ImageItem imageItem = new ImageItem(null,image,ImageItem.LAYOUT_CENTER,"img"); // Ϊͼ��image����һ��ImageItem����imageItem
        form = new Form(pictureName[index]);  // ������ʾͼ��ı�
    	form.append(imageItem);  // ��imageItem�����
        form.addCommand(BACK); // Ϊ�����Ϸ�������
        form.addCommand(EXIT); // Ϊ�������˳�����
        form.setCommandListener(this); // Ϊ���������������
    }
}

