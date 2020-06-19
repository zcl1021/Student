package com.hbsi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbsi.bean.Admin;
import com.hbsi.dao.AdminDao;
import com.hbsi.impl.AdminDaoImpl;



public class AdminManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取请求参数action的值
		String action =request.getParameter("action");
		AdminDao ad = new AdminDaoImpl();
		//判断action值来自哪里。
		if(action.equals("adminregister")) {
			String id =request.getParameter("aid");
			int aid=0;
			aid=Integer.parseInt(id);
			String aname = request.getParameter("aname");
			String sex = request.getParameter("sex");
			String phone = request.getParameter("phone");
			//定义一个对象
			Admin admin = new Admin();
			admin.setAid(aid);
			admin.setAname(aname);
			admin.setSex(sex);
			admin.setPhone(phone);
			boolean flag =  ad.addAdmin(admin);
			if(flag) {
				request.setAttribute("regError", "管理员注册成功，请返回登陆！");
				this.gotoPage("public/register.jsp", request, response);
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
