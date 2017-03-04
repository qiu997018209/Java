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
		
		//BufferedOutputStreamΪ�������߻��幦�ܡ�PrintWriter����������Ҳ�����ܶ��ֽ������ַ������д���
		PrintWriter pWriter = new PrintWriter(new BufferedOutputStream(out));
		//��β�ӻ��з�������ͻ���readline��һֱ����ȥ
		pWriter.println("cn.qiujiahao.test.my_business:getprice:�·�");
		pWriter.flush();
		
		//InputStreamReader���ַ��������ж�ȡ�ı�����������ַ�,BufferedReader�ṩͨ�õĻ��巽ʽ�ı���ȡ
		BufferedReader rd = new BufferedReader(new InputStreamReader(in));
		String Result = rd.readLine();
		System.out.println("result is:"+Result);
		
		rd.close();
		pWriter.close();
		my_socket.close();
	}

}
