<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息注册</title>
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
   <%
      Object obj=request.getAttribute("id");
      String stuid="";
      if(obj!=null){
        stuid=String.valueOf(obj);
       }
   %>

  <div class="container" align="center">
	<div class="card col-6 p-5 ">
	  <div class="bg-info text-white p-2 mt-2" align="center">
			   <h4>请填写基本信息</h4>
	  </div>
		   <form class="form1" action="/sx/stuManage" method="post">
		     <input type="hidden" value="<%=stuid%>" name="id">
             <input type="hidden" value="sturegister" name="action">
			<div class="input-group mt-3">
			   <div class="input-group-prepend ">
				  <span class="input-group-text">姓名</span>
			   </div>
			      <input  type="text" class="form-control" placeholder="请输入姓名" name="sname"/>
							
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">学号</span>
			  </div>
				 <input  type="text" class="form-control" placeholder="请输入学号"  name="sid"/>
							
			</div>

			<div class="input-group mt-3">
				<div class="input-group-prepend ">
					<span class="input-group-text">请选择性别</span>
				</div>
		        <select class="form-control" name="sex">
					<option value="男" >男</option>
				    <option value="女">女</option>
				</select>
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">年龄</span>
			  </div>
				 <input  type="text" class="form-control" placeholder="请输入年龄"  name="age"/>
							
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">住址</span>
			  </div>
				 <input  type="text" class="form-control" placeholder="请输入住址"  name="address"/>
							
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">手机号</span>
			  </div>
				 <input  type="text" class="form-control" placeholder="请输入手机号"   name="phone"/>
							
			</div>
				<button type="submit" class="btn btn-success w-100 mt-3">提交</button>
						
		</form>
	</div>
				
</div>

		<script src="js/jquery-3.4.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>