package com.hbsi.dao;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.User;

public interface UserDao {
     //定义添加用户的方法
	User addUser(User user);
	//定义方法查询表中用户信息
	User lookUser(User user);
	//获取总记录数
	int doCount(DoPage dopage);
    //获取总页数
	int doTotalPage(DoPage dopage);
	//查询当前页要显示的数据
	DoPage doFindAll(DoPage dopage);
	//定义软删除用户方法
	boolean deleteUser(int id);
	//定义找回软删除的用户方法
	boolean retrieveUser(int id);
	//修改密码
	boolean updatePwd(User user);
}
