import java.applet.AudioClip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

// �������ų���

public class AudioPlayDemo	extends JFrame	implements ActionListener, ItemListener {
	boolean looping = false; //�Ƿ�ѭ������
	String[] choics = { "chimes.wav", "start.wav" }; //�����ļ�������
	URL file1 = getClass().getResource(choics[0]); //�����ļ�1
	URL file2 = getClass().getResource(choics[1]); //�����ļ�2
	AudioClip sound1 = java.applet.Applet.newAudioClip(file1); //������������1
	AudioClip sound2 = java.applet.Applet.newAudioClip(file2); //������������2
	AudioClip chosenClip = sound1; //ѡ���������������

	JComboBox jcbFiles = new JComboBox(choics); //�ļ�ѡ����Ͽ�
	JButton playButton = new JButton("����"); //���Ű�ť
	JButton loopButton = new JButton("ѭ������"); //ѭ�����Ű�ť
	JButton stopButton = new JButton("ֹͣ"); //ֹͣ���Ű�ť
	JLabel status = new JLabel("ѡ�񲥷��ļ�"); //״̬����ǩ
	JPanel controlPanel = new JPanel(); //����������ڰ��ݰ�ť
	Container container = getContentPane(); //��ô������ݴ���

	public AudioPlayDemo() { //������
		super("�������ų���"); //���ø��๹�������ô��ڱ�����

		jcbFiles.setSelectedIndex(0); //������Ͽ�ѡ����
		jcbFiles.addItemListener(this); //Ϊ���Ű�ť�����Ŀ������
		//Ϊ���Ű�ť��ѭ�����Ű�ť��ֹͣ���Ű�ť��Ӷ���������
		playButton.addActionListener(this);
		loopButton.addActionListener(this);
		stopButton.addActionListener(this);
		stopButton.setEnabled(false); //����ֹͣ���Ű�ť������
		//�Ѳ��Ű�ť��ѭ�����Ű�ť��ֹͣ���Ű�ť����������
		controlPanel.add(playButton);
		controlPanel.add(loopButton);
		controlPanel.add(stopButton);
		//���ļ�ѡ����Ͽ򡢿�����塢״̬����ǩ���뵽�������ݴ���
		container.add(jcbFiles, BorderLayout.NORTH);
		container.add(controlPanel, BorderLayout.CENTER);
		container.add(status, BorderLayout.SOUTH);

		setSize(300, 130); //���ô��ڴ�С
		setVisible(true); //���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رմ���ʱ�˳�����
	}
	//�ļ�ѡ����Ͽ��¼�����
	public void itemStateChanged(ItemEvent e) {
		if (jcbFiles.getSelectedIndex() == 0) {
			chosenClip = sound1;
		} else {
			chosenClip = sound2;
		}
	}
	//��ť�¼�����
	public void actionPerformed(ActionEvent event) {
		if (chosenClip == null) {
			status.setText("����δ����");
			return; //���AudioClip����Ϊ�գ���ֱ�ӷ���
		}
		Object source = event.getSource(); //��ȡ�û�ϴ�Ӽ���İ�ť
		//���Ű�ť�¼�����
		if (source == playButton) {
			stopButton.setEnabled(true); //����ֹͣ���Ű�ť����
			loopButton.setEnabled(true); //����ѭ�����Ű�ť����
			chosenClip.play(); //����ѡ���������������һ��
			status.setText("���ڲ���"); //����״̬����Ϣ
		}

		//ѭ�����Ű�ť�¼�����
		if (source == loopButton) {
			looping = true;
			chosenClip.loop(); //ѭ������ѡ���������������
			loopButton.setEnabled(false); //����ѭ�����Ű�ť������
			stopButton.setEnabled(true); //����ֹͣ���Ű�ť����
			status.setText("����ѭ������"); //����״̬����Ϣ
		}
		//ֹͣ���Ű�ť�¼�����
		if (source == stopButton) {
			if (looping) {
				looping = false;
				chosenClip.stop(); //ֹͣѭ������ѡ���������������
				loopButton.setEnabled(true); //����ѭ�����Ű�ť����
			} else {
				chosenClip.stop(); //ֹͣ����ѡ���������������
			}
			stopButton.setEnabled(false); //����ѭ�����Ű�ť����
			status.setText("ֹͣ����"); //����״̬����Ϣ
		}
	}

	public static void main(String s[]) {
		new AudioPlayDemo(); //����AudioPlayDemo����
	}
}
