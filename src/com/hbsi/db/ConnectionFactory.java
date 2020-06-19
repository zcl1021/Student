package com.hbsi.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
   //定义四个私有属性
	private static String DRIVER="";
	private static String URL="";
	private static String USERNAME ="";
	private static String PASSWORD="";
	
	//定义私有的构造方法，禁止从类体外调用构造方法创造对象
	private ConnectionFactory() {}
	//定义静态初始化块，当加载类时，调用静态初始化块
	static {
		getProperties();
	}
	private static void getProperties() {
		//获取当前正在运行的线程对象
		Thread curThread = Thread.currentThread();
		//获取当前正在运行的线程的类加载器
		ClassLoader loader = curThread.getContextClassLoader();
		//使用类加载器获取一个属性文件的输入流
		InputStream inStream = loader.getResourceAsStream("jdbc.properties");
		//创建一个属性对象，保存从jdbc.properties文件读入的属性和属性的值
		Properties prop = new Properties();
		try {
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		DRIVER=prop.getProperty("driver");
		URL=prop.getProperty("url");
		USERNAME=prop.getProperty("username");
		PASSWORD =prop.getProperty("password");
	}
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn =DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn; //返回连接对象
	}
}
