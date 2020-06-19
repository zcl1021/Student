package com.hbsi.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hbsi.bean.DoPage;
import com.hbsi.bean.Student;
import com.hbsi.bean.User;
import com.hbsi.dao.StuDao;
import com.hbsi.dao.UserDao;
import com.hbsi.impl.StudentDaoImpl;
import com.hbsi.impl.UserDaoImpl;


public class StudentManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//获取请求参数action的值
	    String action =request.getParameter("action");
	    StuDao sd = new StudentDaoImpl();
	    if(action.equals("sturegister")) {//判断action是否来自注册页面
	    	//获取用户输入的信息
	    	String userid = request.getParameter("id");
	    	int uid = Integer.parseInt(userid);
	    	String id = request.getParameter("sid");
	    	int sid = Integer.parseInt(id);
	    	String sname = request.getParameter("sname");
	    	String age = request.getParameter("age");
	    	String sex = request.getParameter("sex");
	    	String address = request.getParameter("address");
	    	String phone = request.getParameter("phone");
	    	//定义一个对象
	    	Student student = new Student();
	    	student.setId(uid);
	    	student.setSid(sid);
	    	student.setSname(sname);
	    	student.setAge(age);
	    	student.setSex(sex);
	    	student.setAddress(address);
	    	student.setPhone(phone);
	    	boolean flag = sd.addStu(student);
	    	if(flag) {
	    		request.setAttribute("regError", "学生注册成功，请返回登陆！");
				this.gotoPage("public/register.jsp", request, response);
	    	}
	    	
	    }
	    if(action.equals("addStuInfo")) {
	    	//获取用户输入的信息
	    	String userid = request.getParameter("id");
	    	int uid = Integer.parseInt(userid);
	    	String id = request.getParameter("sid");
	    	int sid = Integer.parseInt(id);
	    	String sname = request.getParameter("sname");
	    	String age = request.getParameter("age");
	    	String sex = request.getParameter("sex");
	    	String address = request.getParameter("address");
	    	String phone = request.getParameter("phone");
	    	//定义一个对象
	    	Student student = new Student();
	    	student.setId(uid);
	    	student.setSid(sid);
	    	student.setSname(sname);
	    	student.setAge(age);
	    	student.setSex(sex);
	    	student.setAddress(address);
	    	student.setPhone(phone);
	    	boolean flag = sd.addStu(student);
	    	if(flag) {
	     		this.gotoPage("/stuManage?action=StuList", request, response);
	    	}
	    }
	    if(action.equals("StuList")) {
	    	//创建Dopage对象，初始化对象的属性为默认值
			DoPage dopage = new DoPage();
			//获取当前页面参数（获取请求中的当前页码）
			String pageNum=request.getParameter("page");
			int pageNo=0;//定义变量pageNo表示当前是第几页
			if(pageNum==null) {//如果没有从请求汇总获取到page参数的值
				pageNo=1;
			}else {//如果从请求中获取到了参数page的值
				pageNo=Integer.parseInt(pageNum);//把得到的参数值转化为整数复制给pageNum
				
			}
			//1.设置dopage对象的nowpage属性值为pageNo
			dopage.setNowPage(pageNo);
			//从请求中获取请求参数sql的值
			String sqlStr = "";
			int id=0;
			String stuid= request.getParameter("stuId");
			try {
				id=Integer.parseInt(stuid);
				System.out.print(id);
				if(id == 0) {//如果没有得到参数sql的值
					sqlStr="";
				}else {
                    sqlStr=" where sid ="+id;
				}
			
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//2.设置dopage对象的sqlStr的值
			dopage.setSql(sqlStr);
			//3.设置dopage对象的pageSize的属性值
			dopage.setPageSize(5);
			//获取总的记录数
			int totalCount = sd.doCount(dopage);
			if(totalCount == 0) {
				System.out.print("未找到，请输入正确的ID");
				request.setAttribute("errorMsg","未找到该用户，请输入正确的ID");
				System.out.print(request);
				this.gotoPage("admin/showStu.jsp", request, response);
			}else {
				//4.设置dopage对象的count属性值
				dopage.setCount(totalCount);
				//获取总页数
				int totalPage=sd.doTotalPage(dopage);
				//5.设置dopage的totalPage属性值
				dopage.setTotalPage(totalPage);
				//6.根据前面设置，dopage对象有5个属性值，作为参数
				dopage=sd.doFindAll(dopage);
				//把6个属性封装好的dopage对象设置为请求属性
				request.setAttribute("doPage", dopage);
				this.gotoPage("admin/showStu.jsp", request, response);
			}

	    }
	    if(action.equals("show")) {
			//获取会话对象
			HttpSession session = request.getSession();
			//从会话对象中取出登陆用户对象
			User user=(User)session.getAttribute("user");
			//如果登陆用户是学生，获取用户id即为学生的sid
			int sid=0;
	    	Student student = new Student();

			if(user.getUsertypes().equals("student")) {
				sid=user.getId();
		    	student.setId(sid);
		    	sd.lookStu(student);
                request.setAttribute("student", student);
		    	this.gotoPage("student/stuShow.jsp", request, response);
			}else {//如果登陆用户不是学生
				//获取学生id的值
				sid=Integer.parseInt(request.getParameter("id"));
		    	student.setId(sid);
		    	sd.lookStu(student);
                request.setAttribute("student", student);
		    	this.gotoPage("student/updateStu.jsp", request, response);
			}

	    }
        if(action.equals("update")) {
        	//获取用户输入的信息
	    	String userid = request.getParameter("id");
	    	int uid = Integer.parseInt(userid);
	    	String sname = request.getParameter("sname");
	    	String age = request.getParameter("age");
	    	String sex = request.getParameter("sex");
	    	String address = request.getParameter("address");
	    	String phone = request.getParameter("phone");
	    	//定义一个对象
	    	Student student = new Student();
	    	student.setId(uid);
	    	student.setSname(sname);
	    	student.setAge(age);
	    	student.setSex(sex);
	    	student.setAddress(address);
	    	student.setPhone(phone);
	    	boolean flag = sd.updateStu(student);
	    	//获取会话对象
			HttpSession session = request.getSession();
			//从会话对象中取出登陆用户对象
			User user=(User)session.getAttribute("user");
	    	if(flag) {
	    		if(user.getUsertypes().equals("student")) {
	    			 this.gotoPage("/stuManage?action=show", request, response);
	    		}else if(user.getUsertypes().equals("admin")) {
	    			this.gotoPage("/stuManage?action=StuList", request, response);
	    		}
				
	    	}else {
	    		System.out.print("失败");
	    		System.out.print(uid);
	    	}
	    }
        if(action.equals("delete")) {
        	int id=0;//定义变量id初始值为0
			//获取请求参数中id的值
			String idStr= request.getParameter("id");
			try {
				id=Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean flag=sd.deleteStu(id);
			if(flag) {
				this.gotoPage("/stuManage?action=StuList", request, response);
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
