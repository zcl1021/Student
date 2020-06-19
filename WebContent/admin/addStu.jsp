<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>添加学生</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
<style type="text/css">
    .card{
      margin-top:100px;
    }

    </style>
</head>
<body>
	 <div class="container" align="center">
		<div class="card col-6 p-5 ">
		   <div class="bg-info text-white p-2 mt-2" align="center">
			   <h4>添加学生</h4>
		   </div>
		   <form class="form1" action="/sx/register" method="post">
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
			<input  type="hidden" class="form-control" name="usertypes" value="student"/>
			<input  type="hidden" name="action" value="addstu"/>
			 <span style="font-size:12px;color:red;" class="errorcode">
               ${regError}
             </span>
				<button type="submit" class="btn btn-success w-100 mt-3">添加</button>
						
		  </form>
	</div>
				
</div>
</body>
</html>