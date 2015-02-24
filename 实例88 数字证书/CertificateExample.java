import java.io.FileInputStream;
import java.security.cert.Certificate;
import java.security.cert.*;
//���ļ��ж�ȡ����֤��

public class CertificateExample
{
    public static void main(String[] args)
	{
		try{

			//�����ļ�������,�����ļ�Ϊc:/mycert.cer
      		FileInputStream fin = new FileInputStream("mycert.cer");

      		//��ȡһ������X.509֤���֤�鹤��
      		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

      		//��ȡ֤��
      		Certificate mycert = certFactory.generateCertificate(fin);

      		fin.close();

      		System.out.println(mycert);
      }catch (Exception ex){ex.printStackTrace();
      }

	}
}