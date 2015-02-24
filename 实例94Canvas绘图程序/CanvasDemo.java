import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.IOException;

// Canvas��ͼ����

public class CanvasDemo extends MIDlet implements CommandListener {

	private Command exitCommand = new Command("�˳�", Command.EXIT, 1);  //�˳�����
	private Command backCommand = new Command("����", Command.BACK, 3);  //��������
    private Display display;  // �豸����ʾ��
    private List menuList;  //ͼƬ�������˵��б�
	private DrawingCanvas canvas;  //��ʾ���Ƶ�ͼ��
	
	String[] itemNames = {  //��ʾ�б�����
        "ֱ��",
        "��",
        "����",
        "Բ�Ǿ���",
        "������",
        "����"
    };    

	public CanvasDemo() {
		display = Display.getDisplay(this); //ȡ���豸����ʾ��
	}
	
	// ���س�����MIDlet�ĳ��󷽷�startApp()
	protected void startApp() {
		canvas = new DrawingCanvas();  // ����DrawingCanvas����canvas
		canvas.addCommand(exitCommand); // Ϊcanvas�����˳�����
		canvas.addCommand(backCommand); // Ϊcanvas���Ϸ�������
		canvas.setCommandListener(this); // Ϊcanvas�������������
	
		
        int num = itemNames.length;  // �˵������
        Image[] imageArray = new Image[num]; // �б��ͼ������
        try {
            Image icon = Image.createImage("/Icon.png"); // �����б��ͼ��
	        for(int i=0;i<num;i++) {
	        imageArray[i] =icon;	
	        }            
        } catch (java.io.IOException err) {
       	}

        menuList = new List("Canvas��ͼ����", Choice.IMPLICIT, itemNames, imageArray);
        menuList.addCommand(exitCommand); // Ϊ���˵��б�����˳�����
        menuList.setCommandListener(this); // Ϊ���˵��б��������������
        display.setCurrent(menuList);  // ��ʾ���˵��б�		
	}
	
	// ���س�����MIDlet�ķ���pauseApp()
	protected void pauseApp() {
	}
	
	// ���س�����MIDlet�ķ���destroyApp()
	protected void destroyApp(boolean unconditional) {
	}
	
	// ʵ�ֽӿ�CommandListener�ķ���
    public void commandAction(Command c, Displayable d) {
        if (d.equals(menuList)) {
            if (c == List.SELECT_COMMAND) {
            	int select = ((List)d).getSelectedIndex();  //�õ�ѡ�еĲ˵���
            	switch(select) {
            		case 0:
            			canvas.drawLine();  // ����ֱ��
            			display.setCurrent(canvas);
            			break;
            		case 1:
            			canvas.drawArc();  // ���ƻ�
            			display.setCurrent(canvas);
            			break;
            		case 2:
            			canvas.drawRect();  // ���ƾ���
            			display.setCurrent(canvas);
            			break;
            		case 3:
            			canvas.drawRoundRect();  // ����Բ�Ǿ���
            			display.setCurrent(canvas);
            			break;
            		case 4:
            			canvas.drawTriangle();  // ����������
            			display.setCurrent(canvas);
            			break;
            		case 5:
            			canvas.drawText();  // ��������
            			display.setCurrent(canvas);
            	}
            }
        }
        
        if (c == backCommand) {
                display.setCurrent(menuList);
        } else if (c == exitCommand) {
            destroyApp(false);  // ���ٳ���
            notifyDestroyed();
        }
    }
	
	// ����ͼ�εĻ���
	public class DrawingCanvas extends Canvas {
		int w = getWidth();  // �����Ŀ��
		int h = getHeight();  // �����ĸ߶�
		Image buffer = Image.createImage(w, h);  // ���ڻ�ͼ�Ļ���ͼ��
		Graphics gc = buffer.getGraphics();  // ��ȡ����ͼ���ͼ�λ���
		
		// �������
		public void clearScreen() {
			gc.setColor(255,255,255);  // ���û�ͼ��ɫΪ��ɫ
			gc.fillRect(0,0,w,h);  // �ѻ���ͼ�����Ϊ��ɫ
			gc.setColor(255,0,0);  // ���û�ͼ��ɫΪ��ɫ
		}
		
		// ����ֱ��
		public void drawLine() {
			setTitle("ֱ��");  // ���û����ı���
			clearScreen();  // �������
			gc.drawLine(10,10,w-20,h-20);  // ���ƺ�ɫֱ��
			gc.setColor(0,0,255);  // ���û�ͼ��ɫΪ��ɫ
			gc.drawLine(10,h/2,w-10,h/2);  // ������ɫֱ��
		}
		
		// ���ƻ�
		public void drawArc() {
			setTitle("���ߺ���仡");
			clearScreen();
			gc.drawArc(5,5,w/2-20,h/2-20,60,216);  // ���ƻ���
			gc.drawArc(5,h/2-10,w/2-20,h/2-20,0,360);  // ����Բ
			gc.setColor(0,0,255);
			gc.fillArc(w/2,5,w/2-20,h/2-20,60,216);  // ������仡��
			gc.fillArc(w/2,h/2-10,w/2-20,h/2-20,0,360);  // �������Բ
		}
		
		// ���ƾ���
		public void drawRect() {
			setTitle("���κ�������");
			clearScreen();
			gc.drawRect(25,25,w/2-30,h/2-30);  // ���ƾ���
			gc.fillRect(w/2+25,25,w/2-30,h/2-30);  // ����������
		}
		
		// ����Բ�Ǿ���
		public void drawRoundRect() {
			setTitle("Բ�Ǿ��κ����Բ�Ǿ���");
			clearScreen();
			gc.drawRoundRect(5,5,w-5-30,h/2-30,20,20);  // ����Բ�Ǿ���
			gc.setColor(0,0,255);
			gc.fillRoundRect(5,h/2,w-30,h/2-30,20,20);  // �������Բ�Ǿ���
		}
		
		// ����������
		public void drawTriangle() {
			setTitle("���������");
			clearScreen();
			gc.fillTriangle(w/2, h/6, w/6, h/2, w/2, h/2);
		}
		
		// ��������
		public void drawText() {
			setTitle("����"); //���ñ���
			clearScreen();
			gc.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_BOLD,Font.SIZE_SMALL));  // ��������
			gc.drawString("Hello World!",0,0,gc.TOP|gc.LEFT);  // ʹ�õ�ǰ�����������
			gc.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_BOLD|Font.STYLE_UNDERLINED,Font.SIZE_LARGE));
			gc.drawString("Hello World!",0,h/3,gc.TOP|gc.LEFT);
		}

		public void paint(Graphics g) {
			g.drawImage(buffer, 0, 0, 0);  // �ѻ�����ͼ������ݻ��Ƶ�������
		}
	}
}
	