import java.io.File;
import java.io.FileOutputStream;

//����Ȩ�޵Ŀ���
public class PolicyExample {
	public static void main(String[] args){
  	try{
    	//дһ���ļ���c:/hello.txt
	    byte[] info = "Hello,���ǲ�����Ϣ".getBytes(); //����д���ļ�����Ϣ
	    File testFile = new File("c:/hello.txt"); //�����ļ�,�����c:/hello.txt
	    FileOutputStream fout = new FileOutputStream(testFile);
	    fout.write(info); //д��Ϣ���ļ�
	    fout.close(); //�ر������
	    System.out.println(testFile.getAbsolutePath() + " д��ɹ�");
	}catch (Exception ex){
	    ex.printStackTrace();
	}
	}
}
