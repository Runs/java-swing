import java.applet.Applet; 
import java.awt.*; 
import java.util.*; 

public class CalendarApplet extends Applet{ 

	static final int TOP = 70;  //���˾���
	static final int CELLWIDTH=50,CELLHEIGHT = 30;  //��Ԫ��ߴ�
	static final int MARGIN = 3;  //�߽����
	static final int FEBRUARY = 1; 
		
	TextField tfYear = new TextField("2004", 5); //��ʾ��ݵ��ı���
	Choice monthChoice = new Choice();  //�·�ѡ��������
	Button btUpdate = new Button("����");  //���°�ť
	GregorianCalendar calendar=new GregorianCalendar(); //��������
	Font smallFont = new Font("TimesRoman", Font.PLAIN, 15);  //��ʾС����
	Font bigFont = new Font("TimesRoman", Font.BOLD, 50);  //��ʾ������
	String days[] = {"������", "����һ", "���ڶ�", "������","������", "������", "������"};  
	String months[] = {"һ��", "����", "����", "����","����", "����", "����", "����", "����","ʮ��", "ʮһ��", "ʮ����"}; 
	int daysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //ÿ���µ�����
	int searchMonth,searchYear; //��ѯ����ݼ��·�

	public void init(){ 
	    setBackground(Color.white);  //���ñ�����ɫ	    
	    searchMonth = calendar.get(Calendar.MONTH); //�õ�ϵͳ���
	    searchYear = calendar.get(Calendar.YEAR);	//�õ�ϵͳ�·�
	    add(new Label(" ��:")); //���������Applet	
	    tfYear.setText(String.valueOf(searchYear)); //�����ı�������	
	    add(tfYear);  
	    add(new Label(" ��:")); 	
	    monthChoice.setFont(smallFont);  //�����·�ѡ�����������ʾ����
	    for (int i = 0; i < 12; i++) {	
	    	monthChoice.addItem(months[i]); //����������ѡ��
	    }
	    monthChoice.select(searchMonth); //����������ǰѡ����
	    add(monthChoice); 	    
	    add(btUpdate); 
	    int componentCount=this.getComponentCount(); //�õ�Applet�е��������
	    for (int i=0;i<componentCount;i++){
	    	getComponent(i).setFont(smallFont); //���������������ʾ����
	    }	    
	}	
	
	public void paint(Graphics g){ 	
	    FontMetrics fontMetric;   //��ʾ�����FontMetrics����
	    int fontAscent; 	
	    int dayPos; 	
	    int totalWidth, totalHeight; //�ܵĿ��,�߶�
	    int numRows;  //����
	    int xNum, yNum;   //ˮƽ�ʹ�ֱ����Ԫ������ 
	    int numDays;  	
	    String dayStr;	 //��ʾ�����ַ���
	    int margin;        
	    
	    g.setColor(Color.lightGray); //���õ�ǰ��ɫ
	    g.setFont(bigFont); //���õ�ǰʹ������
		g.drawString(searchYear+"��",60,TOP+70); //�����ַ���
		g.drawString((searchMonth+1)+"��",200,TOP+130);	  
	
		g.setColor(Color.black);
		g.setFont(smallFont);
	    fontMetric = g.getFontMetrics(); 	//��ȡ������ֵ
	    fontAscent = fontMetric.getAscent(); 	
	    dayPos = TOP + fontAscent / 2; 	   
	    totalWidth = 7 * CELLWIDTH; 	//�õ��ܵı����
	    for (int i = 0; i < 7; i++) {	
	    	g.drawString(days[i], (CELLWIDTH-fontMetric.stringWidth(days[i]))/2 + i*CELLWIDTH,dayPos-20);  //���Ʊ�������	
		}	
	    numRows = getNumberRows(searchYear, searchMonth); //������Ҫ���е�����
	    totalHeight = numRows * CELLHEIGHT; //�õ��ܵı��߶�
	    for (int i = 0; i <= totalWidth; i += CELLWIDTH) {
	    	g.drawLine(i, TOP , i, TOP+ totalHeight); //���Ʊ����
	    }	
	    for (int i = 0, j = TOP ; i <= numRows; i++, j += CELLHEIGHT) {
		    g.drawLine(0, j, totalWidth, j); //���Ʊ����
	    }	
	    xNum = (getFirstDayOfMonth(searchYear, searchMonth) + 1) * CELLWIDTH - MARGIN; 
	    yNum = TOP +  MARGIN + fontAscent; 	    
	    numDays = daysInMonth[searchMonth] + ((calendar.isLeapYear(searchYear) && (searchMonth == FEBRUARY)) ? 1 : 0); 
	    for (int day = 1; day <= numDays; day++) { 	
	    	dayStr = Integer.toString(day); 
	     	g.drawString(dayStr, xNum - fontMetric.stringWidth(dayStr), yNum); 	//�����ַ���
	     	xNum += CELLWIDTH; 	
	     	if (xNum > totalWidth) { 	
	         	xNum = CELLWIDTH - MARGIN; 	
	         	yNum += CELLHEIGHT; 	
	     	} 
     	} 
	 }
	
	
	public boolean action(Event e, Object o){ 	
		int searchYearInt; 	
		if (e.target==btUpdate){ 	
	 		searchMonth = monthChoice.getSelectedIndex();  //�õ���ѯ�·�
	 		searchYearInt = Integer.parseInt(tfYear.getText(), 10);  //�õ���ѯ���	 
	 		if (searchYearInt > 1581) {	
	 			searchYear = searchYearInt; 
	 		} 	
			repaint();  //�ػ���Ļ
			return true; 
		 } 	
		 return false; 	
	 } 	
	
	private int getNumberRows(int year, int month) { //�õ�������
		int firstDay; 	
		int numCells;	
		if (year < 1582) { //���С��1582�꣬�򷵻�-1
			return (-1); 
		}	
		if ((month < 0) || (month > 11)) {
			return (-1); 	
		}
		firstDay = getFirstDayOfMonth(year, month); //�����·ݵĵ�һ��
		 	
		if ((month == FEBRUARY) && (firstDay == 0) && !calendar.isLeapYear(year)) {
			return 4;
		}
		numCells = firstDay + daysInMonth[month]; 
		if ((month == FEBRUARY) && (calendar.isLeapYear(year))) {
			numCells++; 
		}
	 	return ((numCells <= 35) ? 5 : 6); 	//��������
	 } 	
	
	private int  getFirstDayOfMonth(int year, int month) {  //�õ�ÿ�µĵ�һ��
		int firstDay; 
		int i;	
		if (year < 1582) { //���С��1582��,����-1
			return (-1); 
		}
		if ((month < 0) || (month > 11)) { //�·�������,����-1
			return (-1);	 
		}
	 	firstDay = getFirstDayOfYear(year);	//�õ�ÿ��ĵ�һ��
	 	for (i = 0; i < month; i++) {
	 		firstDay += daysInMonth[i]; //����ÿ�µĵ�һ��
	 	}	
	 	if ((month > FEBRUARY) && calendar.isLeapYear(year)) {
	 		firstDay++; 
	 	}
		return (firstDay % 7); 			
	 } 		

	private int getFirstDayOfYear(int year){ //����ÿ��ĵ�һ��
		int leapYears; 
		int hundreds;
		int fourHundreds; 	
		int first;
	 	if (year < 1582) { //������С��1582��
	 		return (-1); //����-1
	 	} 
	 	leapYears = (year - 1581) / 4;
	 	hundreds = (year - 1501) / 100;
	 	leapYears -= hundreds;
	 	fourHundreds = (year - 1201) / 400; 
	 	leapYears += fourHundreds;
	 	first=5 + (year - 1582) + leapYears % 7; //�õ�ÿ���һ��
	 	return first; 	
	 } 
} 