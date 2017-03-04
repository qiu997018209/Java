package cn.qiujiahao.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

import javax.swing.text.AbstractDocument.BranchElement;

public class my_server_task implements Runnable{

	private Socket socket;
	public my_server_task(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("start");
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			//�����������������Ȼ���ٶ�����ȡ
			BufferedReader rd = new BufferedReader(new InputStreamReader(in));
			//���ͻ��˵����ݻ�ȡ����
			String[] split = rd.readLine().split(":");
			//����
			String classname = split[0];
			//����
			String methname  = split[1];
			String methParam = split[2];
			
			//�������÷���ʵ�ֶ�̬���ú���
			//��ȡ��
			Class<?> forName = Class.forName(classname);
			System.out.println("call class name:"+forName);
			//��ȡ����
			Object newinstance = forName.newInstance();

			//��ȡ����
			Method method = forName.getMethod(methname,String.class);
			System.out.println("call method:"+method);
			
			//ִ�к���
			Object result = method.invoke(newinstance, methParam);			
			System.out.println("results: " + (int)result);
			
			PrintWriter pWriter = new PrintWriter(new BufferedOutputStream(out));
			//��β�ӻ��з�������ͻ���readline��һֱ����ȥ
			pWriter.println((int)result);
			pWriter.flush();
			
			rd.close();
			pWriter.close();
			socket.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
