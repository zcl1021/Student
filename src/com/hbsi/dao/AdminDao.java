package com.hbsi.dao;

import com.hbsi.bean.Admin;
import com.hbsi.bean.User;

public interface AdminDao {
	  //定义添加用户的方法
		boolean addAdmin(Admin admin);
	  //定义方法查询表中用户信息
		Admin lookAdminName(Admin admin);
}
