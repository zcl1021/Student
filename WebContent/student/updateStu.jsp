<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>修改学生信息</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
</head>
<body style="text-align: center; margin:10px auto;">
<div class="container" style=" text-align: center; margin-top:20px;">
  <div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">
       <span style="font-size:20px;">修改学生信息</span> 
    </li>
    </ul>
  </div> 
<form action="/sx/stuManage" method="post">
   <input type="hidden" name="id" value="${student.id }">
   <input type="hidden" name="action" value="update">
	  <div class="input-group mt-3">
		<div class="input-group-prepend ">
		  <span class="input-group-text"> 学号 ：</span>
		</div>
		<input  type="text" class="form-control" name="sid" value="${student.sid }" readonly/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="input-group-prepend ">
		  <span class="input-group-text"> 姓名 ： </span>
		</div>
		<input  type="text" class="form-control" name="sname" value="${student.sname }"/>
							
	</div>
	<div class="input-group mt-3">
		<div class="input-group-prepend ">
			<span class="input-group-text"> 性别 ：</span>
		</div>
		<input  type="text" class="form-control" name="sex" value="${student.sex }"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="input-group-prepend ">
			<span class="input-group-text"> 年龄 ：</span>
		</div>
		<input  type="text" class="form-control" name="age" value="${student.age }"/>
		
	</div>
	<div class="input-group mt-3">
		<div class="input-group-prepend ">
			<span class="input-group-text"> 地址 ：</span>
		</div>
		<input  type="text" class="form-control" name="address" value="${student.address }"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<div class="input-group-prepend ">
			<span class="input-group-text"> 电话 ：</span>
		</div>
		<input  type="text" class="form-control" name="phone" value="${student.phone }"/>
							
	</div>

<div class="container" style="margin-top:20px;">
 <input type="submit" class="btn btn-success" value="修改"/>
  &nbsp;&nbsp;&nbsp;
<a href="/sx/stuManage?action=StuList" class="btn btn-info" role="button">返回</a>
</div>
 </form>
 </div>
</body>
</html>