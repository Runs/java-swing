import java.awt.*;
import java.applet.*;
import java.util.Random;

//��������

public class ShadowTextApplet extends Applet implements Runnable{	
   String message;  //����ʾ���ı���Ϣ
   Thread thread;  //ʵ�������˶����߳�
   int fontHeight,speed,baseline; //����߶�,�˶��ٶȺͻ���
   Color textColor,bgColor,shadomColor; //������ɫ��������ɫ����Ӱ��ɫ
   Image newImage;  //ʵ��������Image����
   Graphics newGraphics;  //ʵ��������Graphics����
   boolean normal; //�����Ƿ������ı�־
   Font font; //��ʾ����
   FontMetrics fontMetric; //��ʾ�����FontMetrics����

   public void init(){ //��ʼ��
		Graphics graphics = getGraphics(); //�õ�graphics����
	   Dimension dim=getSize(); //�õ��ߴ�
	   fontHeight=dim.height-10; //����Applet�ߴ��������ָ߶�
	   newImage=createImage(dim.width,dim.height); //����newImage����
	   newGraphics = newImage.getGraphics(); //�õ�Graphics����
	   message=getParameter("text"); //�õ���ʾ����
	   if (message==null){        
	     	message="��Ӱ����";	//����Ĭ������
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
	       bgColor = Color.white;  //����Ĭ�ϱ�����ɫ
	   else
	       bgColor = new Color(Integer.parseInt(param)); 
	   if((param = getParameter("SHADOMCOLOR")) == null)  //�õ���Ӱ��ɫ
	       shadomColor = Color.lightGray;  //����Ĭ����Ӱ��ɫ
	   else
	       shadomColor = new Color(Integer.parseInt(param)); 
	   if((param = getParameter("NORMAL")) != null) //�Ƿ��Ǿ�̬�ı�
	       normal = (Integer.valueOf(param).intValue()!=0); //����ֵ��Ϊ��,��Ϊ��̬�ı�
	   setBackground(bgColor); //���ñ�����ɫ
	   if((param = getParameter("SPEED")) != null) //�õ��˶��ٶ�
	       speed = Integer.valueOf(param).intValue();
	   if(speed == 0)
	       speed = 200;  //����Ĭ���˶��ٶ�	  
     	thread = new Thread(this); //ʵ�����˶������߳�
    }

    public void start(){ //��ʼ�����߳�
        if(thread == null) {
        		thread = new Thread(this); //ʵ�����߳�
        }
        thread.start(); //�߳�����
    }

    public void run(){  //�߳���������
        while(thread!=null) { 
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
        newGraphics.setColor(bgColor); //���õ�ǰ��ɫ
        newGraphics.fillRect(0, 0, getSize().width, getSize().height); //����������
        newGraphics.setColor(textColor); //���õ�ǰ��ɫ
        newGraphics.setFont(font); //��������
        if(!normal){ //�������������
        		java.util.Random r=new java.util.Random();	
            int xpoint = r.nextInt(fontMetric.stringWidth(message));  //�������X����
            
            font = new Font("TimesRoman",Font.BOLD,30); //��������
			newGraphics.setFont(font);  //���õ�ǰ����
			    
		    newGraphics.setColor(shadomColor); //���õ�ǰ��ɫ
		    newGraphics.drawString(message,xpoint+3,baseline +3); //������Ӱ
		    
		    newGraphics.setColor(textColor); //�����ı���ɫ
		    newGraphics.drawString(message,xpoint,baseline); //���ַ���
			    
        } 
        else {  //����Ǿ�̬�ı�
            font = new Font("TimesRoman",Font.BOLD,30); //��������
			newGraphics.setFont(font);  //���õ�ǰ����
			    
		    newGraphics.setColor(shadomColor); //���õ�ǰ��ɫ
		    newGraphics.drawString(message,xpoint+3,baseline +3); //������Ӱ
		    
		    newGraphics.setColor(textColor); //�����ı���ɫ
		    newGraphics.drawString(message,xpoint,baseline); //���ַ���
     	  }
        g.drawImage(newImage, 0, 0, this); //����Image
    }
}
