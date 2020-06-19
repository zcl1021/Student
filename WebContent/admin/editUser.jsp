<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>用户修改密码</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
<script type="text/javascript">
    function check(){
    	//获取密码输入框的值
    	var password=document.getElementById("pwd").value;
    	if(password == ""||password.length==0){
    		alert("密码不能为空")
    		return false;
    	}else{
    		return true;
    	}
    }
</script>
</head>
<body>
<div class="container" style=" text-align: center; margin-top:20px;">
    <div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">
       <span style="font-size:20px;">修改密码</span> 
    </li>
    </ul>
  </div>
   <form action="/sx/userManage" method="post" onsubmit="check();">
   <input type="hidden" name="action" value="updatePassword"/>
   <input type="hidden" name="id" value="${param.id }">
   
   	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">新密码</span>
	   </div>
	   <input type="password" name="password" id="pwd" class="form-control" >
							
	</div>
    <div class="container" style="margin-top:20px;">
        <input type="submit" class="btn btn-success" value="修改"/>
        &nbsp;&nbsp;&nbsp;
    </div>
   </form>
   </div>
</body>
</html>