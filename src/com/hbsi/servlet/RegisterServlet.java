package com.hbsi.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbsi.bean.User;
import com.hbsi.dao.UserDao;
import com.hbsi.impl.UserDaoImpl;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action =request.getParameter("action");
		if(action.equals("register")) {
			//获取用户填写的信息
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String usertypes = request.getParameter("usertypes");
			Date d =new Date();//获取当前日期和时间
			DateFormat df = DateFormat.getDateTimeInstance();//获取日期和时间的格式化器对象
			String ctime =df.format(d);
			//定义User对象,属性初始化为默认值
			User user =new User();
			//用获取到的请求值设置为user对象的属性值
			user.setUsername(username);
			user.setPassword(password);
			user.setUsertypes(usertypes);
	        user.setCtime(ctime);
			//创建UserDao对象
			UserDao ud = new UserDaoImpl();
			//调用添加用户的方法
			User u = ud.addUser(user);
			if(u.getUsertypes().equals("error")) {//说明没有用户注册成功
				//设置一个请求属性，提示添加用户没有成功
	        	request.setAttribute("regError", "用户注册未成功，请重新注册");
	        	//返回注册页面
	        	this.gotoPage("public/register.jsp", request, response);
			}else if(u.getUsertypes().equals("admin")) {
//				request.setAttribute("regError", "管理员注册成功！请返回登陆");
				request.setAttribute("aid", u.getId());
				this.gotoPage("admin/adminInfo.jsp", request, response);
			}else if(u.getUsertypes().equals("student")) {
				request.setAttribute("id", u.getId());
				this.gotoPage("student/studentInfo.jsp", request, response);
			}
		}
		if(action.equals("addstu")) {
			//获取用户填写的信息
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String usertypes = request.getParameter("usertypes");
			Date d =new Date();//获取当前日期和时间
			DateFormat df = DateFormat.getDateTimeInstance();//获取日期和时间的格式化器对象
			String ctime =df.format(d);
			//定义User对象,属性初始化为默认值
			User user =new User();
			//用获取到的请求值设置为user对象的属性值
			user.setUsername(username);
			user.setPassword(password);
			user.setUsertypes(usertypes);
	        user.setCtime(ctime);
			//创建UserDao对象
			UserDao ud = new UserDaoImpl();
			//调用添加用户的方法
			User u = ud.addUser(user);
			if(u.getUsertypes().equals("error")) {//说明没有用户注册成功
				//设置一个请求属性，提示添加用户没有成功
	        	request.setAttribute("regError", "学生添加失败，请重新添加");
	        	//返回注册页面
	        	this.gotoPage("admin/addStu.jsp", request, response);
			}else if(u.getUsertypes().equals("student")) {
				request.setAttribute("id", u.getId());
				this.gotoPage("admin/addStuInfo.jsp", request, response);
			}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	//定义私有方法，实现请求转发
	private void gotoPage(String url,HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
	    RequestDispatcher dispatcher=request.getRequestDispatcher(url);
	    dispatcher.forward(request,response);
			}


}
