package com.hbsi.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbsi.bean.Admin;
import com.hbsi.dao.AdminDao;
import com.hbsi.db.ConnectionFactory;
import com.hbsi.db.DBClose;

public class AdminDaoImpl implements AdminDao {
	Connection conn =null;
	PreparedStatement pstat=null;
	ResultSet rs = null;
	//添加管理员信息
	public boolean addAdmin(Admin admin) {
		boolean flag =false;
		conn=ConnectionFactory.getConnection();
		String sql="insert into admin(aid,aname,sex,phone) values(?,?,?,?)";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, admin.getAid());
			pstat.setString(2, admin.getAname());
			pstat.setString(3, admin.getSex());
			pstat.setString(4, admin.getPhone());
			int i=pstat.executeUpdate();
			if(i>0) {//说明有用户添加成功
				flag=true;//如果添加数据成功，把布尔值设为true
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(pstat, conn);
		}
		return flag;
	}
	//根据id查询用户的姓名
	public Admin lookAdminName(Admin admin) {
		conn=ConnectionFactory.getConnection();
		String sql = "select aname from admin where aid=?";
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, admin.getAid());
			rs=pstat.executeQuery();
			if(rs.next()) {
				//把结果集中字段名为id的字段值取出，作为参数设置为user对象的属性id的值
				admin.setAname(rs.getString("aname"));
					}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(rs,pstat,conn);
		}
		return admin;
	}

}
