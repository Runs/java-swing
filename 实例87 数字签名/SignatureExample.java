import java.security.*;

public class SignatureExample {
   public static void main(String[] args){
    try{
	    byte[] info ="��ǩ����Ϣ".getBytes();

	    //����RSA��Կ��(myKeyPair)
	    KeyPairGenerator myKeyGen= KeyPairGenerator.getInstance("RSA");
	    myKeyGen.initialize(1024);
	    KeyPair myKeyPair = myKeyGen.generateKeyPair();
	    System.out.println( "�õ�RSA��Կ��" );

		//����Signature����,����˽Կ����Ϣ(info)ǩ��.
	    Signature mySig = Signature.getInstance("SHA1WithRSA");  //��ָ���㷨����ǩ������
	    mySig.initSign(myKeyPair.getPrivate());  //��˽Կ��ʼ��ǩ������
	    mySig.update(info);  //����ǩ�������ݴ��͸�ǩ������(���ڳ�ʼ��֮��)
	    byte[] sigResult = mySig.sign();  //����ǩ������ֽ�����
	    System.out.println("ǩ������Ϣ: "+ new String(sigResult) );

		//�ù�Կ��֤ǩ�����
	    mySig.initVerify(myKeyPair.getPublic());  //ʹ�ù�Կ��ʼ��ǩ������,������֤ǩ��
	    mySig.update(info); //����ǩ������
	    boolean verify= mySig.verify(sigResult); //�õ���֤���
	    System.out.println( "ǩ����֤���: " +verify);
    }catch (Exception ex){ex.printStackTrace();}
    }
}
