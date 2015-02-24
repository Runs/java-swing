import java.awt.*;
import java.applet.*;

public class Calculator extends Applet{
    TextField tfAnswer; //��ʾ����ͽ�����ı���
  	Button bPoint,bEqual,bPlus,bMinus,bClear,bMulti,bDivision; //�������ť
	Button[] b=new Button[10];  //���ְ�ť
  
  	String currentOp,preOp; //��ǰ��������һ������
	String foreText,backText; //��ǰ�������һ������
  	boolean isFloat = false; //�������ͱ�־
  
  	public void init() {
	  	Panel panel1=new Panel();  //ʵ�������
		Panel panel2=new Panel();
		Panel panel3=new Panel();
		
	    currentOp = new String(""); //ʵ���������
	    preOp = new String("");
	    foreText = new String("");
	    backText = new String("");
	    tfAnswer = new TextField(8);
	    setBackground(Color.lightGray); //����Applet����ɫ
	    setForeground(Color.blue);   //����Appletǰ��ɫ  	    
	    
		for(int i=9;i>=0;i--){ 
			b[i]=new Button(Integer.toString(i)); //ʵ�������ְ�ť
			panel2.add(b[i]); //���Ӱ�ť�����
		}        
	 
	    bPoint = new Button("."); //ʵ������ť
	    bEqual = new Button("=");
	    bEqual.setForeground(Color.red);   //���ð�ťǰ��ɫ
	    bClear = new Button("���");
	    bClear.setForeground(Color.red);
	    bDivision = new Button("/");
	    bDivision.setForeground(Color.red);
	    bMulti = new Button("*");
	    bMulti.setForeground(Color.red);
	    bMinus = new Button("-");
	    bMinus.setForeground(Color.red);    
	    bPlus = new Button("+");
	    bPlus.setForeground(Color.red);    
	
		setLayout(new FlowLayout()); //���ò��ֹ�����
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new GridLayout(4,3));
		panel3.setLayout(new GridLayout(4,1));
	    panel1.add(tfAnswer); //������������
		panel1.add(bClear);
		panel2.add(bPoint);
		panel2.add(bEqual);
		panel3.add(bPlus);
		panel3.add(bMinus);
		panel3.add(bMulti);
		panel3.add(bDivision);
		add(panel1); //���������Applet
		add(panel2);
		add(panel3);	
  	}
  
  	public boolean action(Event e, Object o) { //�¼�����
    	String s = new String("");
     	for(int i=0;i<10;i++){
			if(e.target==b[i]||e.target==bPoint){ //��ť�¼����������ְ�ť�͵㰴ť
        		if(e.target != bPoint) {
          			s = (String)o;
          			doForeText(s); //��������
        		}
        		if((e.target == bPoint)&&(!isFloat)){ //����������
          			isFloat = true; //���������־
         			s = (String)o;
          			if(foreText.equals("")){
           	 			foreText += "0.";  //����С����ǰ��0
          			}
          			else{
            			doForeText(s);
          			}
        		}
      		}
      	}      
      	if(e.target == bClear) {
        	doClear();  //�������
      	}      
      	if((e.target == bMulti)||(e.target == bDivision)|| (e.target == bPlus)||(e.target == bMinus)) { //��������
        	if(foreText != ""){
         		currentOp = ((String)o);
          		doOperator();  //��������
        	}
        	else {
          		preOp = ((String)o);
          	}
      	}      
      	if(e.target == bEqual) { //���ڰ�ť�¼�����
        	doOperator(); //��������
      	}  
		return true;  
	}
  
  	public void doOperator(){
    	double dFore,dBack;
    	Double d;
    
    	if(preOp.equals("")) {
      		backText = foreText;
      		foreText = "";
      		tfAnswer.setText(backText); //��ʾ�ı�
    	}
    	else {
      		dFore = (new Double(foreText)).doubleValue(); //�õ���һ����
      		dBack = (new Double(backText)).doubleValue(); //�õ��ڶ�����
      		foreText = "";
      		backText = tfAnswer.getText(); 
      
	      	if(preOp.equals("+")) { //�����㴦��
	        	d = new Double((dBack + dFore)); //�õ�������
	        	tfAnswer.setText(d.toString());  //��ʾ������
	        	backText = d.toString();
	        	Double.parseDouble(backText);
	      	}
	      	if(preOp.equals("-")) {
	        	d = new Double((dBack - dFore));//�õ�������
	        	tfAnswer.setText(d.toString()); //��ʾ������
	        	backText = d.toString();
	      	}
	      	if(preOp.equals("*")) {
	        	d = new Double((dBack * dFore));//�õ�������
	        	tfAnswer.setText(d.toString());//��ʾ������
	        	backText = d.toString();
	      	}
	      	if(preOp.equals("/")) {
	      		if (dFore==0){
	      			tfAnswer.setText("��������Ϊ0"); //��ʾ������Ϣ
	      			return;
	      		}
	       		d = new Double((dBack / dFore));//�õ�������
	        	tfAnswer.setText(d.toString());//��ʾ������
	        	backText = d.toString();
	      	}
    	}Math.sin();
    	preOp = currentOp;
  	}
  
  	public void doForeText(String s) {
    	foreText += s;
    	tfAnswer.setText(foreText); //��ʾ����
  	}
  
  	public void doBackText(String s){
    	backText = foreText;
    	foreText = "";
    	tfAnswer.setText(foreText); //��ʾ����
  	}
  
 	public void doClear() { //�������
    	currentOp = "";
    	preOp = "";
    	foreText = "";
    	backText = "";
    	isFloat = false;
    	tfAnswer.setText(""); 
  	}
}