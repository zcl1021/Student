package com.hbsi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.AdminDao;
import com.hbsi.dao.StuDao;
import com.hbsi.dao.UserDao;
import com.hbsi.impl.AdminDaoImpl;
import com.hbsi.impl.StudentDaoImpl;
import com.hbsi.impl.UserDaoImpl;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username  = request.getParameter("username");
		String password = request.getParameter("password");
		String usertypes = request.getParameter("usertypes");
		//获取用户的会话对象
		HttpSession session = request.getSession();
		//把从请求中获取的用户名、密码、用户身份信息封装为User对象属性值
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setUsertypes(usertypes);
		//用UserDao实现类创建UserDao对象
		UserDao ud = new UserDaoImpl();
		//调用接口对象的lookUser方法验证用户输入值是否在user表中
		User u = ud.lookUser(user);
		//如果返回的对象u的usertypes属性值是error，说明表中没有用户输入的信息
		if(u.getUsertypes().equals("error")) {
			request.setAttribute("errorMsg","用户名或密码错误，请重新输入");
			//然后请求转发会登陆页面
			this.gotoPage("public/login.jsp",request,response);
			
		}else {
			if(u.getDel_status().equals("1")) {
				request.setAttribute("errorMsg","该用户已删除，如需找回请联系管理员");
				//然后请求转发会登陆页面
				this.gotoPage("public/login.jsp",request,response);
			}
			if(u.getDel_status().equals("2")) {
				//把用户对象设置为会话属性值
				session.setAttribute("user", u);
				if(u.getUsertypes().equals("admin")) {
					//如果登陆用户是管理员，进入管理员主页面
					this.gotoPage("admin/aIndex.jsp", request, response);
				
				}
				if(u.getUsertypes().equals("student")) {
					//如果登陆用户是学生，进入管理员主页面
					 int id = u.getId();
		        	 Student student = new Student();
		        	 student.setId(id);
		        	 StuDao sd = new StudentDaoImpl();
		        	 Student s = sd.lookStudentName(student);
		        	 if(s.getSname() == null ) {
		        		request.setAttribute("id", u.getId());
		     			this.gotoPage("student/studentInfo.jsp", request, response);
		        	 }else {
		        		 this.gotoPage("student/sIndex.jsp", request, response);
		        	 }
				}
			}
		}
	}
	 //定义私有方法，实现请求转发
		private void gotoPage(String url,HttpServletRequest request,HttpServletResponse response)
		  throws ServletException,IOException{
			RequestDispatcher dispatcher=request.getRequestDispatcher(url);
			dispatcher.forward(request,response);
		}

}
