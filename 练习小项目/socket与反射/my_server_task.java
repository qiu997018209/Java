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
			
			//将输入读到缓冲区，然后再读缓冲取
			BufferedReader rd = new BufferedReader(new InputStreamReader(in));
			//将客户端的内容获取过来
			String[] split = rd.readLine().split(":");
			//类名
			String classname = split[0];
			//方法
			String methname  = split[1];
			String methParam = split[2];
			
			//以下利用反射实现动态调用函数
			//获取类
			Class<?> forName = Class.forName(classname);
			System.out.println("call class name:"+forName);
			//获取对象
			Object newinstance = forName.newInstance();

			//获取方法
			Method method = forName.getMethod(methname,String.class);
			System.out.println("call method:"+method);
			
			//执行函数
			Object result = method.invoke(newinstance, methParam);			
			System.out.println("results: " + (int)result);
			
			PrintWriter pWriter = new PrintWriter(new BufferedOutputStream(out));
			//结尾加换行符，否则客户端readline会一直读下去
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
