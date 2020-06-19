<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hbsi.bean.User"%>
<html>
<head>
<meta charset="UTF-8">
<title>学生导航</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
<style type="text/css">
  body{
    padding-top:20px;
  } 
</style>
</head>
<body style="text-align: center;">
<div class="container">
  <div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">学生导航栏</li>
    </ul>
    <a href="/sx/public/right.jsp" class="list-group-item list-group-item-action" target="main">首页</a>
    <a href="/sx/stuManage?action=show" class="list-group-item list-group-item-action" target="main">查看/修改信息</a>
        <a href="/sx/public/updatePassword.jsp" class="list-group-item list-group-item-action" target="main">修改密码</a>
  </div>

</div>
</body>
</html>