package exp4;

import javax.swing.*;

public class ChatView {
	static String userName;  //�ɿͻ��˵�¼ʱ����
	JTextField text;
	JTextArea textArea;
	ClientReadAndPrint.ChatViewListen listener;
	
	// ���캯��
	public ChatView() {
		JFrame jf = new JFrame("�ͻ���");
		jf.setBounds(500,200,400,330);  //��������ʹ�С
		jf.setResizable(false);  // ����Ϊ��������
		
		JPanel jp = new JPanel();
		JLabel lable = new JLabel("�û���" + userName);
		textArea = new JTextArea("***************��¼�ɹ�����ӭ�������������ң�****************\n",12, 35);
		textArea.setEditable(false);  // ����Ϊ�����޸�
		JScrollPane scroll = new JScrollPane(textArea);  // ���ù�����壨װ��textArea��
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // ��ʾ��ֱ��
		jp.add(lable);
		jp.add(scroll);
		
		text = new JTextField(20);
		JButton button = new JButton("����");
		jp.add(text);
		jp.add(button);
		
		// ���ü���
		listener = new ClientReadAndPrint().new ChatViewListen();
		listener.setJTextField(text);  // ����PoliceListen��ķ���
		listener.setJTextArea(textArea);
		listener.setChatViewJf(jf);
		text.addActionListener(listener);  // �ı�����Ӽ���
		button.addActionListener(listener);  // ��ť��Ӽ���
		
		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // �������Ͻǹر�ͼ�������
		jf.setVisible(true);  // ���ÿɼ�
	}
}

