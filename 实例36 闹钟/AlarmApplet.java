import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;

//����

public class AlarmApplet extends Applet implements Runnable{
	
	TextField tfHour,tfMinute,tfSecond,tfNowHour,tfNowMinute,tfNowSecond; //��ʾ��������Ϣ���ı���
	Button btStart,btStop; //�򿪺͹ر����Ӱ�ť
	Thread alarm; //�����߳�
	boolean turnOn; //�Ƿ��
	
	public void init(){
		turnOn=true; //��ʼ������
		
		Panel panel2=new Panel(); //ʵ�������
		Panel panel3=new Panel();
		Panel panel4=new Panel();
		tfHour=new TextField(1); //��������������
		tfMinute=new TextField(1);
		tfSecond=new TextField(1);
		tfNowHour=new TextField(1);
		tfNowMinute=new TextField(1);
		tfNowSecond=new TextField(1);
		btStart=new Button("��");
		btStop=new Button("��");	
		
		panel2.add(new Label("��ǰʱ�䣺"));
		panel2.add(tfNowHour);
		panel2.add(new Label("ʱ"));
		panel2.add(tfNowMinute);
		panel2.add(new Label("��"));
		panel2.add(tfNowSecond);
		panel2.add(new Label("��"));
		panel3.add(new Label("����ʱ�䣺"));				
		panel3.add(tfHour);
		panel3.add(new Label("ʱ"));
		panel3.add(tfMinute);
		panel3.add(new Label("��"));
		panel3.add(tfSecond);
		panel3.add(new Label("��"));

		panel4.add(new Label("��������"));
		panel4.add(btStart);
		panel4.add(btStop);
		
		add(panel2); //���������Applet��
		add(panel3);
		add(panel4);
		 
		btStart.addActionListener(new ActionListener(){  //�����Ӱ�ť�¼�����
			public void actionPerformed(ActionEvent event){
				turnOn=true; //���ô򿪱�־ΪTrue
			}
		});
		
		btStop.addActionListener(new ActionListener(){  //�ر����Ӱ�ť�¼�����
			public void actionPerformed(ActionEvent event){
				turnOn=false; //���ô򿪱�־Ϊfalse
			}
		});	
	}	
	
	public void start(){
		if (alarm==null){
			alarm=new Thread(this);  //ʵ�����߳�
			alarm.start(); //�����߳�
		}
	
	}

	public void run(){
		while (alarm!=null){
			try{
				alarm.sleep(1000); //�߳�����һ��
			}
			catch (InterruptedException ex){
			}
			runAlarm(); //��������
		}
		
	}
	
	private void runAlarm(){
		Calendar now=new GregorianCalendar();	//�õ���������
		int hour=now.get(Calendar.HOUR_OF_DAY); //�õ�Сʱ��
		int minute=now.get(Calendar.MINUTE);   //�õ�����
		int second=now.get(Calendar.SECOND);   //�õ�����
		
		tfNowHour.setText(Integer.toString(hour)); //����ʱ����ʾ�ı���
		tfNowMinute.setText(Integer.toString(minute));
		tfNowSecond.setText(Integer.toString(second));
		if (turnOn){ //��������Ǵ򿪵�
			int alarmHour=-1,alarmMinute=-1; 
			String time=tfHour.getText(); //�õ����õ�����Сʱ��
			if (time!=null && !time.equals(""))
				alarmHour=Integer.parseInt(time);
			time=tfMinute.getText();
			if (time!=null && !time.equals(""))  //�õ����õ����ӷ�����
				alarmMinute=Integer.parseInt(time);		
			
			if (alarmHour!=-1 && alarmMinute!=-1){			
				if (hour==alarmHour){  //�Ƚ�ʱ��
					if (minute==alarmMinute){
        				AudioClip sound=getAudioClip(getDocumentBase(),"alarm.wav");  //ȡ�������ļ�
        				sound.play(); //��������
        				turnOn=false; 
					}
				}
			}
		}
	}
}