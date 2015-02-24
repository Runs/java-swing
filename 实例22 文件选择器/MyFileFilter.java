import java.io.File;
import javax.swing.filechooser.FileFilter;

//�ļ�������

public class MyFileFilter extends FileFilter {

	String ends;  //�ļ���׺
	String description;  //�ļ���������

	public MyFileFilter(String ends, String description) { //���캯��
		this.ends = ends;  //�����ļ���׺
		this.description=description;  //�����ļ���������
	}

	public boolean accept(File file) {  //����FileFilter�е�accept����
		if (file.isDirectory())  //�����Ŀ¼,�򷵻�true
			return true;
		String fileName = file.getName();  //�õ��ļ�����
	    if (fileName.toUpperCase().endsWith(ends.toUpperCase()))  //���ļ���׺��ɽ��ܺ�׺ת�ɴ�д��Ƚ�
	      return true;
        else
          return false;
   }

   public String getDescription() {  //�����ļ���������
      return description;
   }
}