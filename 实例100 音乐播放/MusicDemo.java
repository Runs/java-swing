import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import java.io.*; 

public class MusicDemo extends MIDlet {
	private Display display; // �豸����ʾ��
    private Player player = null; //������
    private TextBox textbox; //��ʾ��ʾ��Ϣ

    public MusicDemo() {
        textbox=new TextBox("title","playing...",1024, 0);  //ʵ�����ı���
        try{
            InputStream is = this.getClass().getResourceAsStream("/music.wav");  //�����ļ�������
            player = Manager.createPlayer(is,"audio/x-wav");  //ʵ����������
        }catch(Exception e){}
    }

    public void startApp() {
  	    display = Display.getDisplay(this); //ȡ���豸����ʾ��
  	  
        if(player != null){
            try{
                player.start();  //��ʼ��������
                display.setCurrent(textbox);  //��ʾ���ڲ��ŵ���Ϣ
            }catch(MediaException e){
                textbox.setString("Errors");  //���ô�����Ϣ
                display.setCurrent(textbox);  //��ʾ������Ϣ
            }      
        }
        else{
    	    textbox.setString("Errors");
            display.setCurrent(textbox); 
    	}
    }

    public void pauseApp() {}

    public void destroyApp(boolean unconditional) {}
}

