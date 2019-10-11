package exp5;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerFileThread extends Thread{
	ServerSocket server = null;
	Socket socket = null;
	static List<Socket> list = new ArrayList<Socket>();  // �洢�ͻ���
	
	public void run() {
		try {
			server = new ServerSocket(8090);
			while(true) {
				socket = server.accept();
				list.add(socket);
				// �����ļ������߳�
				FileReadAndWrite fileReadAndWrite = new FileReadAndWrite(socket);
				fileReadAndWrite.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class FileReadAndWrite extends Thread {
	private Socket nowSocket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	
	public FileReadAndWrite(Socket socket) {
		this.nowSocket = socket;
	}
	public void run() {
		try {
			input = new DataInputStream(nowSocket.getInputStream());  // ������
			while (true) {
				String path = input.readUTF();  // �����ļ�·��
				File file = new File(path);
				for(int i = 0; i < ServerFileThread.list.size(); i++) {
					Socket socket = ServerFileThread.list.get(i);
					output = new DataOutputStream(socket.getOutputStream());  // �����
					DataInputStream fileReader = new DataInputStream(new FileInputStream(file));
					if(socket != nowSocket) {  // ���͸������ͻ���
						output.writeUTF(file.getName());  // �����ļ�����
						output.flush();
						output.writeLong(file.length());  // �����ļ�����
						output.flush();
						int length = -1;
						byte[] buff = new byte[1024];
						while ((length = fileReader.read(buff)) > 0) {  // ��������
							output.write(buff, 0, length);
							output.flush();
						}
					}
					fileReader.close();
				}
			}
		} catch (Exception e) {
			ServerFileThread.list.remove(nowSocket);  // �̹߳رգ��Ƴ���Ӧ�׽���
		}
	}
}