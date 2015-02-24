import java.awt.*;
import java.applet.*;
import java.io.*;

public class AlbumApplet extends Applet{
	Choice choicePhoto; //ͼƬѡ��������
	int totalImages; //ͼ��������
	Image images[],showImage; //ͼ�����鼰��ǰ��ʾͼ��
	Graphics graphics; //����ͼ���Graphics����
	MediaTracker imagetracker; //ý�������
  
	public void init(){
		setBackground(Color.black); //����Applet�ı�����ɫ
		setLayout(null); //���ò��ֹ�����
		choicePhoto = new Choice(); //ʵ����������
		choicePhoto.setBounds(5,10,200,20); //����������߽���λ��
		String param;
		param = getParameter("Amount"); //��ȡͼ����������
		totalImages=Integer.parseInt(param); //�õ�ͼ������
		images = new Image[totalImages]; 

		imagetracker = new MediaTracker(this); //ʵ����ý�������
		for(int i=0; i<totalImages; i++){
			param = getParameter("Name"+i);  //��ȡ����
			choicePhoto.addItem(param); //����������ѡ��
			param = getParameter("Picture"+i);  //��ȡ����
			images[i] = getImage(getDocumentBase(),param); //�õ�ͼ��
			imagetracker.addImage(images[i],i); //���Ӵ����ص�ͼ��
		}
		try{ 
			imagetracker.waitForID(0); //�ȴ���һ��ͼƬ�ļ������
		}
		catch(InterruptedException e){}
		
		add(choicePhoto); //���������Applet
		Dimension dim=getSize(); //�õ�Applet�ߴ�
		showImage = createImage(dim.width,dim.height-40); //����Imageʵ��
		graphics = showImage.getGraphics(); //�õ�Graphicsʵ��
	}
  
	public void paint(Graphics g)	{
		g.drawImage(showImage,5,40,this); //����ͼ��
	}

	public boolean action(Event e , Object o){
		if(e.target == choicePhoto)	{ //�ж��¼�Դ
			int selected=choicePhoto.getSelectedIndex(); //�õ�ѡ��ͼ����
			graphics.drawImage(images[selected],0,0,this); //����ͼ��
			repaint(); //�ػ���Ļ
		}
		return true;
	}
}