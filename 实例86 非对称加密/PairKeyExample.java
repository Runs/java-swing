/*
 * Copyright (c) 2004 ???. All Rights Reserved.
 *
 * Version 1.0 , Created on 2004-3-10
 *
 */

/**
 * @author Devon
 */

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;

public class PairKeyExample {

	public static void main(String argv[]) {
		try {
			String algorithm = "RSA"; //��������㷨,���� DES,DESede,Blowfish
			String message = "��������ã���������";

			//������������Կ��(keyPairZhang)
			KeyPairGenerator keyGeneratorZhang =
				KeyPairGenerator.getInstance(algorithm);  //ָ�����õ��㷨
			keyGeneratorZhang.initialize(1024); //ָ����Կ����Ϊ1024λ
			KeyPair keyPairZhang = keyGeneratorZhang.generateKeyPair(); //������Կ��
			System.out.println("���������Ĺ�Կ��");

			// �������ɹ�Կ(publicKeyZhang)�����͸�����,���﷢�͵��ǹ�Կ�������ֽ�
			byte[] publicKeyZhangEncode = keyPairZhang.getPublic().getEncoded();

			//ͨ���������̵ȷ�ʽ,�ѹ�Կ���봫�͸�����
			//���Ľ��յ����������Ĺ�Կ,�������
			KeyFactory keyFacoryLi = KeyFactory.getInstance(algorithm);  //�õ�KeyFactory����
			X509EncodedKeySpec x509KeySpec =
				new X509EncodedKeySpec(publicKeyZhangEncode);  //��Կ����X.509����
			PublicKey publicKeyZhang = keyFacoryLi.generatePublic(x509KeySpec); //����Կ��KeySpec����ת��Ϊ��Կ
			System.out.println("���ĳɹ�����,�õ������Ĺ�Կ");

			//�����������Ĺ�Կ������Ϣ�������͸�����
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");  //�õ�Cipher����
			cipher.init(Cipher.ENCRYPT_MODE, publicKeyZhang);  //�������Ĺ�Կ��ʼ��Cipher����
			byte[] cipherMessage = cipher.doFinal(message.getBytes());  //�õ�������Ϣ
			System.out.println("���ܺ���Ϣ��" + new String(cipherMessage));
			System.out.println("������ɣ����͸�����...");

			//�������Լ���˽Կ���ܴ����Ĵ��յ�����Ϣ
			cipher.init(Cipher.DECRYPT_MODE, keyPairZhang.getPrivate()); //��������˽Կ��ʼ��Cipher����
			byte[] originalMessage = cipher.doFinal(cipherMessage);  //�õ����ܺ���Ϣ
			System.out.println("�����յ���Ϣ�����ܺ�Ϊ��" + new String(originalMessage));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
