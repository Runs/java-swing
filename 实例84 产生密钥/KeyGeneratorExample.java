/*
 * Copyright (c) 2004 ???. All Rights Reserved.
 *
 * Version 1.0 , Created on 2004-3-10
 *
 */

/**
 * @author Devon
 */

 /**
  *  ��β����ͱ�����Կ
  */

import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import java.io.*;

public class KeyGeneratorExample {
	public static void main(String[] args)	{
		try{
		   //������Կ���ܵ���Կ(myKey)
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede"); //����DESede�㷨
			keyGenerator.init(168); //ѡ��DESede�㷨,��Կ����Ϊ112λ��168λ
			Key myKey = keyGenerator.generateKey(); //������Կ
			System.out.println("�õ���Կ������Կ");

			//����˫Կ����Կ��(keyPair)
			KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA"); //����RSA�㷨
			keyPairGenerator.initialize(1024); //ָ����Կ����Ϊ1024λ
			KeyPair keyPair = keyPairGenerator.generateKeyPair();  //������Կ��
			System.out.println("���������Ĺ�Կ��");

			//���湫Կ���ֽ�����
			File f = new File("publicKey.dat");  //���湫Կ���ļ�publicKey.dat
			FileOutputStream fout = new FileOutputStream(f);
			fout.write(keyPair.getPublic().getEncoded()); //�õ���Կ���ֽ�����
			fout.close();  //�ر��ļ������
			System.out.println("���湫Կ���ļ�: "+f.getAbsolutePath());

			//��Java�������л�����˽Կ,ͨ��Ӧ��˽Կ���ܺ��ٱ���
			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("privateKey.dat")); //����˽Կ���ļ�privateKey.dat
			oout.writeObject(keyPair.getPrivate()); //���л�˽Կ
			oout.close();  //�ر������
			System.err.println("����˽Կ��: privateKey.dat");

			//���ļ��еõ���Կ������ֽ�����
			FileInputStream fin = new FileInputStream("publicKey.dat");  //����publicKey.dat
			ByteArrayOutputStream baout = new ByteArrayOutputStream();  //����д���ļ����ֽ���
			int aByte = 0;
			while ((aByte = fin.read())!= -1)  //���ļ���ȡһ���ֽ�
			{
			   baout.write(aByte);   //д��һ���ֽ�
			}
			fin.close();  //�ر��ļ�������
			byte[] keyBytes = baout.toByteArray();  //�õ���Կ���ֽ�����
			baout.close();  //�ر��ֽ����������

			//���ֽ�������빫Կ
			X509EncodedKeySpec x509KeySpec =new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");  //ָ���㷨RSA,�õ�һ��KeyFactory��ʵ��
			PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);  //���빫Կ
            System.out.println("���ļ��гɹ��õ���Կ");
		}catch (Exception ex){ex.printStackTrace();
		}
	}
}