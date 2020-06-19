package com.hbsi.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//1.取出请求中的参数code的值（及客户端输入的验证码）
		String checkcode = request.getParameter("code");
		//2.取出服务器画出验证码时保存在会话中的验证码
		HttpSession session = request.getSession();//获取会话对象
		String sessioncode = String.valueOf(session.getAttribute("code"));//获取会话对象中封装的code属性值
		//3.比较用户输入的验证码和会话中封装的验证码是否相同
		if(checkcode.equals(sessioncode)) {
			//如果相同，说明用户输入的验证码是正确的，请求转发。
			this.gotoPage("/login", request, response);
		}else {
			//如果用户输入的验证码不正确，在请求中设置属性codeMsg,值为“验证码错误”
			request.setAttribute("codeMsg", "验证码错误，请重新输入");
			//请求转发到login.jsp页面
			this.gotoPage("/public/login.jsp", request, response);
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
