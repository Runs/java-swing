import java.awt.*;
import java.applet.*;
import java.util.Random;

//��������

public class JumpTextApplet extends Applet implements Runnable{	
   String message;  //����ʾ���ı���Ϣ
   Thread jumpThread;  //ʵ���������ֵ��߳�
   int fontHeight,speed,baseline; //����߶�,�����ٶȺͻ���
   Color textColor,bgColor; //������ɫ�뱳����ɫ
   Image jumpImage;  //ʵ��������Image����
   Graphics jumpGraphics;  //ʵ��������Graphics����
   boolean normal; //�����Ƿ������ı�־
   Font font; //��ʾ����
   FontMetrics fontMetric; //��ʾ�����FontMetrics����
   Color randomColors[]; //���������ɫ
   boolean randomColor;  //�Ƿ��������ɫ

   public void init(){ //��ʼ��
		Graphics graphics = getGraphics(); //�õ�graphics����
	   Dimension dim=getSize(); //�õ��ߴ�
	   fontHeight=dim.height-10; //����Applet�ߴ��������ָ߶�
	   jumpImage=createImage(dim.width,dim.height); //����Image����
	   jumpGraphics = jumpImage.getGraphics(); //�õ�Graphics����
	   message=getParameter("text"); //�õ���ʾ����
	   if (message==null){        
	     	message="����������";	//����Ĭ������
	   }
	   
	   int textWidth=dim.width-(message.length() + 1)*5-10; //�������ֿ��
	   do{
	   	graphics.setFont(new Font("TimesRoman", 1, fontHeight)); //������ʾ����
	      fontMetric = graphics.getFontMetrics(); //�õ�FontMetric����
	      if(fontMetric.stringWidth(message)>textWidth) //�������ֿ�ȵ�����߶�
	         fontHeight--;
	   }
	   while(fontMetric.stringWidth(message) > textWidth);{
	   	baseline = getSize().height - fontMetric.getMaxDescent(); //������ʾ����λ��
	   }
	   font = new Font("TimesRoman", 1, fontHeight); //�õ�����ʵ��
	   
	   String param; //�����ַ���
	   if((param = getParameter("TEXTCOLOR")) == null) //�õ��ı���ɫ
	   	textColor = Color.black; //����Ĭ���ı���ɫ
	   else
	      textColor = new Color(Integer.parseInt(param));  //�����ı���ɫ
	   if((param = getParameter("BGCOLOR")) == null)  //�õ�������ɫ
	       bgColor = Color.lightGray;  //����Ĭ�ϱ�����ɫ
	   else
	       bgColor = new Color(Integer.parseInt(param)); 
	   setBackground(bgColor); //���ñ�����ɫ
	   if((param = getParameter("SPEED")) != null) //�õ������ٶ�
	       speed = Integer.valueOf(param).intValue();
	   if(speed == 0)
	       speed = 200;  //����Ĭ�������ٶ�
	   if((param = getParameter("RANDOMCOLOR")) != null) //�õ��Ƿ��������ɫ����
	       randomColor = (Integer.valueOf(param).intValue()!=0); //����ֵ��Ϊ��,��Ϊ�����ɫ
	   if((param = getParameter("NORMAL")) != null) //�õ��Ƿ��������ɫ����
	       normal = (Integer.valueOf(param).intValue()!=0); //����ֵ��Ϊ��,��Ϊ�����ɫ
		if (randomColor){ //��ʼ�������ɫ���� 
		   Random random=new Random(); //ʵ����Random����
		   int r,g,b; //��ɫ��RGBֵ
		   for (int i=0;i<10;i++){
			  	r=random.nextInt(255);  //�õ�0��255֮������ֵ
			  	g=random.nextInt(255);
			  	b=random.nextInt(255);
		   	Color randomc=new Color(r,g,b);	//������ɫʵ��
		   	randomColors[i]=randomc; //��������ֵ
		   }	 
	   } 
     	jumpThread = new Thread(this); //ʵ�������������߳�
    }

    public void start(){ //��ʼ�����߳�
        if(jumpThread == null) {
        		jumpThread = new Thread(this);
        }
        jumpThread.start();
    }

    public void run(){  //�߳���������
        while(jumpThread!=null) { 
            try{
                Thread.sleep(speed); //�߳�����,���������ʱ��
            }
            catch(InterruptedException ex) {}
            repaint();  //�ػ���Ļ
        }
        System.exit(0);  //�˳�����
    }


    public void paint(Graphics g) {  //����Applet
        if(normal) {  //����Ǿ�̬�ı�
            g.setColor(bgColor);  //���õ�ǰ��ɫ
            g.fillRect(0, 0, getSize().width, getSize().height);  //����������
            g.setColor(textColor); //���õ�ǰ��ɫ
            g.setFont(font); //���õ�ǰ����
            g.drawString(message, (getSize().width - fontMetric.stringWidth(message)) / 2, baseline); //����ַ���
        }
    }

    public void update(Graphics g){  //����Applet
        jumpGraphics.setColor(bgColor); //���õ�ǰ��ɫ
        jumpGraphics.fillRect(0, 0, getSize().width, getSize().height); //����������
        jumpGraphics.setColor(textColor); //���õ�ǰ��ɫ
        jumpGraphics.setFont(font); //��������
        if(!normal){ //�������������
            int xpoint = 0;
            for(int j = 0; j < message.length(); j++){
                if(randomColor){
                    Color color;
                    while(bgColor == (color = randomColors[Math.min(9, (int)(Math.random()*10))])); //�õ���ɫ�������뱳��ɫ��ͬ��һ�������ɫ                    
                    jumpGraphics.setColor(color);  //���õ�ǰ��ɫ
                }
                xpoint += (int)(Math.random() * 10); //�����ַ���X����
                int ypoint = baseline - (int)(Math.random() * 10); //�����ַ���Y����
                String s1 = message.substring(j, j + 1);
                jumpGraphics.drawString(s1, xpoint, ypoint);
                xpoint += fontMetric.stringWidth(s1);
            }
        } 
        else {  //����Ǿ�̬�ı�
            jumpGraphics.drawString(message, (getSize().width - fontMetric.stringWidth(message)) / 2, baseline); //�����ַ���
        }
        g.drawImage(jumpImage, 0, 0, this); //����Image
    }

    public JumpTextApplet() {  //���캯��
        speed = 100;  //��ʼ�ٶ�
        normal = false; //��ʼʱΪ��̬�ı�
        randomColors = new Color[10]; //��ʼ�������ɫ����
        randomColor = false; 
    }
}
