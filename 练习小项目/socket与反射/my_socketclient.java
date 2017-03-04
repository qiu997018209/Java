package cn.qiujiahao.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.AbstractDocument.BranchElement;

public class my_socketclient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Socket my_socket = new Socket("localhost",8888);
		OutputStream out = my_socket.getOutputStream();
		InputStream in = my_socket.getInputStream();
		
		//BufferedOutputStream为输出流提高缓冲功能。PrintWriter，处理流，也就是能对字节流和字符流进行处理
		PrintWriter pWriter = new PrintWriter(new BufferedOutputStream(out));
		//结尾加换行符，否则客户端readline会一直读下去
		pWriter.println("cn.qiujiahao.test.my_business:getprice:衣服");
		pWriter.flush();
		
		//InputStreamReader从字符输入流中读取文本，缓冲各个字符,BufferedReader提供通用的缓冲方式文本读取
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));
		String Result = rd.readLine();
		System.out.println("result is:"+Result);
		
		rd.close();
		pWriter.close();
		my_socket.close();
	}

}
