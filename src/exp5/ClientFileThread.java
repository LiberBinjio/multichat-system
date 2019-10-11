package exp5;

import java.io.*;
import java.net.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientFileThread extends Thread{
	private Socket socket = null;
	private JFrame chatViewJFrame = null;
	static String userName = null;
	static PrintWriter out = null;  // ��ͨ��Ϣ�ķ��ͣ�������ˣ�
	static DataInputStream fileIn = null;
	static DataOutputStream fileOut = null;
//	static DataInputStream fileReader = null;
	static DataOutputStream fileWriter = null;
	
	public ClientFileThread(String userName, JFrame chatViewJFrame, PrintWriter out) {
		ClientFileThread.userName = userName;
		this.chatViewJFrame = chatViewJFrame;
		ClientFileThread.out = out;
	}
	
	public void run() {
		try {
			InetAddress addr = InetAddress.getByName(null);  // ��ȡ������ַ
			socket = new Socket(addr, 8090);  // �ͻ����׽���
			fileIn = new DataInputStream(socket.getInputStream());  // ����������������
			fileOut = new DataOutputStream(socket.getOutputStream());  // ���������������
			// �����ļ�
			while(true) {
				String textName = fileIn.readUTF();
				double totleLength = fileIn.readLong();
				int result = JOptionPane.showConfirmDialog(chatViewJFrame, "�Ƿ���ܣ�", "��ʾ",
														   JOptionPane.YES_NO_OPTION);
				int length = -1;
				byte[] buff = new byte[1024];
				double curLength = 0;
				// ��ʾ��ѡ������0Ϊȷ����1λȡ��
				if(result == 0){
					out.println("��" + userName + "ѡ���˽����ļ�����");
					out.flush();
					File file = new File("C:\\Users\\Samven\\Desktop\\�����ļ�\\(" +
							 userName + ")" + textName);
					fileWriter = new DataOutputStream(new FileOutputStream(file));
					while((length = fileIn.read(buff)) > 0) {  // ���ļ�д������
						fileWriter.write(buff, 0, length);
						fileWriter.flush();
						curLength += length;
						out.println("�����ս���:" + curLength/totleLength*100 + "%��");
						out.flush();
						if(curLength == totleLength) {  // ǿ�ƽ���
							break;
						}
					}
					out.println("��" + userName + "�ɹ������ļ�����");
					out.flush();
					// ��ʾ�ļ���ŵ�ַ
					JOptionPane.showMessageDialog(chatViewJFrame, "�ļ���ŵ�ַ��\n" +
							"C:\\Users\\Samven\\Desktop\\�����ļ�\\(" +
							userName + ")" + textName, "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
				else {  // �������ļ�
					while((length = fileIn.read(buff)) > 0) {
						curLength += length;
						if(curLength == totleLength) {  // ǿ�ƽ���
							break;
						}
					}
				}
				fileWriter.close();
			}
		} catch (Exception e) {}
	}
	
	static void outFileToServer(String path) {
		try {
			fileOut.writeUTF(path);  // �����ļ�·��
			out.println("��" + userName + "�ѳɹ������ļ�����");
			out.flush();
		} catch (Exception e) {}
	}
}
