package Netty.BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	private static int port = 8899;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		while (true) {
			Socket socket = serverSocket.accept();// server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
			new Thread(new Task(socket)).start(); //// 每接收到一个Socket就建立一个新的线程来处理它
		}
	}

	// 线程任务实现
	static class Task implements Runnable {
		private Socket socket;

		public Task(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				handleSocket();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void handleSocket() throws IOException {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String temp = null;
			int index;
			while ((temp=bufferedReader.readLine()) != null) {
				if ((index = temp.indexOf("eof")) != -1) {// 遇到eof时就结束接收
					sb.append(temp.substring(0, index));
					break;
				}
				sb.append(temp);
			}
			System.out.println("from client: " + sb); 
			Writer writer = new OutputStreamWriter(socket.getOutputStream());  
	        writer.write("Hello Client.");  
	        writer.write("eof\n");  
	        writer.flush();  
	        writer.close();  
	        bufferedReader.close();  
	        socket.close();  
		}
		
		 
	}

}
