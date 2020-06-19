package com.hbsi.dao;

import com.hbsi.bean.Admin;
import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;

public interface StuDao {
	  //定义添加用户的方法
	  boolean addStu(Student student);
	  //定义方法查询表中用户信息
	  Student lookStudentName(Student student);
	  //定义方法查询表中用户信息
	  Student lookStu(Student student);
	  //获取总记录数
	  int doCount(DoPage dopage);
	  //获取总页数
	  int doTotalPage(DoPage dopage);
	  //查询当前页要显示的数据
	  DoPage doFindAll(DoPage dopage);
	  //修改用户信息
	  boolean updateStu(Student student);
	  //删除用户信息
	  boolean deleteStu(int id);
}
