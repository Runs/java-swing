import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.*;

public class TimerDemo extends MIDlet {

    Display display; //�豸����ʾ��
    NumberCanvas numCanvas = new NumberCanvas(); //�������ֵ�Canvas����
    NumberMovie mov = new NumberMovie(); 
    Timer  timer = new Timer();  //��ʱ��

    public TimerDemo() {}   

    protected void startApp() {
	    display = Display.getDisplay( this );  //��ȡ�豸����ʾ��
        display.setCurrent( numCanvas ); //���õ�ǰ�Ļ�ͼ����
        timer.schedule( mov, 2, 2 ); //�豸��ʱ��������ʱ��
    }

    protected void pauseApp() {}

    protected void destroyApp( boolean u) {}

    public void exit(){
        timer.cancel();  //�˳���ʱ��
        destroyApp( true );
        notifyDestroyed();
    }

    class NumberMovie extends TimerTask {
        public void run(){
            numCanvas.scroll();  //������Ļ
        }
    }

    class NumberCanvas extends Canvas {
        int        height;
        int        width;
        int[]      stars;  //�������ֵ������
        Random     random = new Random();
	    int ran; //��������0��1�������

        public NumberCanvas(){
            height      = getHeight(); 
            width       = getWidth();
            stars       = new int[ height ];
            for( int i = 0; i < height; ++i ){
                stars[i] = -1;
            }
        }

        public void scroll() {
            for( int i = height-1; i > 0; --i ){
                stars[i] = stars[i-1];
            }
            stars[0] =  random.nextInt() %  width ; //���ɻ������ֵ������ 
            if( stars[0] >= width ){
                stars[0] = -1;
            }
            repaint(); //�ػ���Ļ
        }

        protected void paint( Graphics g ){

            g.setColor( 0, 0, 0 ); //���õ�ǰ��ͼ��ɫΪ��ɫ
            g.fillRect( 0, 0, width, height ); //��䱳��

            g.setColor( 255, 255, 255 ); //���õ�ǰ��ͼ��ɫΪ��ɫ

            for( int y = 0; y < height; y++ ){
                int x = stars[y];
                if( x == -1 ) continue;
		        ran=random.nextInt()%2; //����-1��1�������
		        if (ran<0)
		           ran=1;
                g.drawString(Integer.toString(ran),x,y,Graphics.BOTTOM|Graphics.LEFT); //��������
		    }
        }

        protected void keypressed( int keycode ){
            exit(); //�˳�����
        }
    }
}