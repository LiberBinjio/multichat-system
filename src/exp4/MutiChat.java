package exp4;

import javax.swing.*;

public class MutiChat {
	JTextArea textArea;
	
	// �������ı����������Ϣ
	void setTextArea(String str) {
		textArea.append(str+'\n');
	}
	
	// ���캯��
	public MutiChat() {
		JFrame jf = new JFrame("��������");
		jf.setBounds(500,100,450,500);  // ���ô�������ʹ�С
		jf.setResizable(false);  // ����Ϊ��������
		
		JPanel jp = new JPanel();  // �½�����
		JLabel lable = new JLabel("==��ӭ������������ϵͳ���������ˣ�==");
		textArea = new JTextArea(23, 38);  // �½��ı��������ó���
		textArea.setEditable(false);  // ����Ϊ�����޸�
		JScrollPane scroll = new JScrollPane(textArea);  // ���ù�����壨װ��textArea��
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);  // ��ʾ��ֱ��
		jp.add(lable);
		jp.add(scroll);
		
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // ���ùر�ͼ������
		jf.setVisible(true);  // ���ÿɼ�
	}
}
