<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
   <style type="text/css">

    body {
        background-image:url(/sx/image/thumb1.jpg);
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
    .card{
      margin-top:100px;
      background-color:rgba(0,0,0,0.3);
    }

    </style>
</head>
<body> 
	 <div class="container" align="center">
		<div class="card col-6 p-5 ">
		   <div class="bg-info text-white p-2 mt-2" align="center">
			   <h4>用户注册</h4>
		   </div>
		   <form class="form1" action="/sx/register" method="post">
		   <input type="hidden" name="action" value="register"/>
			 <div class="input-group mt-3">
			   <div class="input-group-prepend ">
				  <span class="input-group-text">用户名</span>
			   </div>
			      <input  type="text" class="form-control" placeholder="请输入用户名" name="username"/>
							
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">密码</span>
			  </div>
				 <input  type="password" class="form-control" placeholder="请输入密码" name="password"/>
							
			</div>
			<div class="input-group mt-3">
				<div class="input-group-prepend ">
					<span class="input-group-text">请选择身份</span>
				</div>
		        <select class="form-control" name="usertypes">
					<option value="admin" >管理员</option>
				    <option value="student">学生</option>
				</select>
			</div>
			 <span style="font-size:12px;color:red;" class="errorcode">
               ${regError}
             </span>
				<button type="submit" class="btn btn-success w-100 mt-3">注册</button>
				<a href="/sx/public/login.jsp" class="btn btn-default btn-sm btn-block" role="button">返回登陆</a>
						
					</form>
				</div>
				
	</div>

		<script src="js/jquery-3.4.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>