<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>exit.jsp</title>
</head>
<body>
  <%
   //设置会话对象释放
   session.invalidate();
  %>
  <jsp:forward page="login.jsp"></jsp:forward>
</body>
</html>