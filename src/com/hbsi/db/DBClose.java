package com.hbsi.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//用来关闭连接释放资源
public class DBClose {
//关闭结果集对象
	private static void close(ResultSet rs) {
		if(rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private static void close(Statement stat) {
		if(stat !=null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private static void close (Connection conn) {
		if(conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	//定义公有方法，关闭执行添加、删除、修改操作用的资源
	public static void close(Statement stat,Connection conn) {
		close(stat);
		close(conn);
	}
	//定义公有方法，关闭执行查询用到的资源
	public static void close(ResultSet rs,Statement stat,Connection conn) {
		close(rs);
		close(stat);
		close(conn);
	}
}
