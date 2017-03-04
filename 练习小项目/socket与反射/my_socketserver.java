package cn.qiujiahao.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.stream.events.StartDocument;

public class my_socketserver {

	public static void main(String[] args) throws Exception {
		// TkODO Auto-generated method stub
		ServerSocket serverSocket = new ServerSocket();
		serverSocket.bind(new InetSocketAddress("localhost",8888));
		while(true) {
			//没有连接则阻塞
			Socket socket = serverSocket.accept();
			new Thread(new my_server_task(socket)).start();
		}
		
		
	}

}
