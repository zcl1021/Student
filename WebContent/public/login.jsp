<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>学生信息管理系统</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
<script type="text/javascript">
   function refresh(){
	   //点击是应该再次请求，重新画一张图片
	   var imgEle=document.getElementById("codeimage");//获取id获取图片标签对象
	   imgEle.src="/sx/createImage?"+new Date();//使用new Date（）实现路径的刷新，预防缓存中图片重新加载
   }
</script>
<style type="text/css">

    body {
        background-image:url(/sx/image/thumb1.jpg);
        background-repeat:no-repeat;
        background-size:100% 100%;
    }
    .card{
      margin-top:85px;
      background-color:rgba(0,0,0,0.3);
    }
</style>
</head>
<body>
 <div class="container" align="center">
       
		<div class="card col-6 p-5 ">
		 <h1>学生信息管理系统<sup>V1.0.1</sup></h1>
		   <div class="bg-info text-white p-2 mt-2" align="center">
		   
			   <h4>用户登陆</h4>
		   </div>
		   <form class="form1" action="/sx/checkCode" method="post" >
			 <div class="input-group mt-3">
			   <div class="input-group-prepend ">
				  <span class="input-group-text">用户名</span>
			   </div>
			      <input  type="text" class="form-control" placeholder="请输入用户名" name="username" id="username" />
					  <%
		                Object msgObj=request.getAttribute("usermessage"); 
		                String msg="";
		                if(msgObj!=null){
		                   msg=String.valueOf(msgObj);
		                } 
		           %>
		           <span id="usernamemessage"><%=msg%></span>			
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">密码</span>
			  </div>
				 <input  type="password" class="form-control" placeholder="请输入密码" id="password" name="password"/>
							
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
			   <%
                      //获取请求中封装的属性
                      Object obj=request.getAttribute("errorMsg");
                      if(obj !=null){//如果得到了属性值
                   %>
                   <font color="#feeeed" style="font-size:15px">
                     <%=String.valueOf(obj) %>
                   </font>
                   <%
                      }
                   %>
			<div class="input-group mt-3">
			    <div class="input-group-prepend ">
					<span class="input-group-text">验证码</span>
				</div>
			    <input  type="text" class="form-control" placeholder="请输入验证码" name="code"/>&nbsp;&nbsp;
			     <img alt="*" src="/sx/createImage" onclick="refresh()" id="codeimage" title="单击更换验证码">
			</div>
			 <span class="errorcode" style="font-size:15px; color:#feeeed;">${codeMsg}</span>
             <br>
				<button type="submit" class="btn btn-success w-100 mt-3">登陆</button>
				<br/><br/>
				<a href="/sx/public/register.jsp" class="btn btn-primary btn-sm" role="button">没有账号？点击注册</a>
						
					</form>
				</div>
				
	</div>

		<script src="js/jquery-3.4.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>