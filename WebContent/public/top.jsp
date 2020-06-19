<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hbsi.bean.User"%>
<%@ page import="com.hbsi.dao.AdminDao"%>
<%@ page import="com.hbsi.impl.AdminDaoImpl" %>
<%@ page import="com.hbsi.bean.Admin" %>
<%@ page import="com.hbsi.bean.Student" %>
<%@ page import="com.hbsi.dao.StuDao" %>
<%@ page import="com.hbsi.impl.StudentDaoImpl" %>
<html>
<head>
<meta charset="UTF-8">
<title>top页面</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
<style type="text/css">
   .col-sm-3{
     line-height:40px;
       }
   body{
    background-color:#73A1BF;
   }
</style>
<script type="text/javascript">
   function logout(){
	   var flag=window.confirm("确定退出当前账号吗");
	   if(flag){//说明点击的是按钮
		   top.location="exit.jsp";
	   }
	   return false;
   }
</script>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-9" >
    <h3>学生信息管理系统 <span class="badge badge-info">V1.0.1</span></h3>
    </div>
    <div class="col-sm-3" >
       <%
         //获取会话中封装的的登陆用户信息
         User u=(User)session.getAttribute("user");
         //获取用户的id
         int id = u.getId();
         if(u.getUsertypes().equals("student")){
        	 Student student = new Student();
        	 student.setId(id);
        	 StuDao sd = new StudentDaoImpl();
        	 Student s = sd.lookStudentName(student);%>
        	 <span class="glyphicon glyphicon-user">学生</span> <%=s.getSname() %>
         <%
         }else if(u.getUsertypes().equals("admin")){
        	 Admin admin = new Admin(); 
        	 admin.setAid(id);
        	 AdminDao ad = new AdminDaoImpl();
        	 Admin a = ad.lookAdminName(admin);
        %>
        <span class="glyphicon glyphicon-user">管理员</span> <%=a.getAname() %>
        <% 
        }
        %>
       <button type="button" class="btn btn-outline-dark btn-sm" onclick="logout()">退出</button>
    </div>
  </div>
</div>
</body>
</html>