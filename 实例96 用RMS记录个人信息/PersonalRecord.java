
public class PersonalRecord {

    private final static int NAME_INDEX = 0;  //��������λ��
    private final static int PHONE_INDEX = 20; //��������λ��
    private final static int EMAIL_INDEX = 40;  //�����ʼ�����λ��
	private final static int FIELD_LEN = 20; //�ı�����
    private final static int MAX_REC_LEN = 60; //��¼��󳤶�

    private static StringBuffer recBuf = new StringBuffer(MAX_REC_LEN); //�ַ�����

    private PersonalRecord() {}

    private static void clearBuf() {
        for (int i = 0; i < MAX_REC_LEN; i++) {
            recBuf.insert(i, ' '); //���ַ������в����ַ�,��ʼ���ַ�������
        }
        recBuf.setLength(MAX_REC_LEN); //���û��峤��
    }

    public static byte[] createRecord(String name, String phone, String email) {
        clearBuf();
        recBuf.insert(NAME_INDEX, name); //��������
        recBuf.insert(PHONE_INDEX, phone); //����绰����
        recBuf.insert(EMAIL_INDEX, email);  //��������ʼ�
        recBuf.setLength(MAX_REC_LEN);
        return recBuf.toString().getBytes();
    }
    

    public static String getName(byte[] b) { //��������
        return new String(b, NAME_INDEX, FIELD_LEN).trim();
    }

    public static String getPhone(byte[] b) {  //���ص绰����
        return new String(b, PHONE_INDEX, FIELD_LEN).trim();
    }

    public static String getEmail(byte[] b) {  //���ص����ʼ�
        return new String(b, EMAIL_INDEX, FIELD_LEN).trim();
    }
    
    
}
