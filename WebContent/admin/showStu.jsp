<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息列表</title>
<link rel="stylesheet" href="/sx/css/bootstrap.min.css"/>
<style type="text/css">
.pagination{
   width:300px;
}
</style>
</head>
<body>
<div class="container" style=" text-align: center; margin-top:20px;">
  <div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">
       <span style="font-size:20px;">学生信息</span> 
    </li>
    </ul>
  </div>      
  <table class="table  table-striped">
  <div style="width:50%; margin-bottom:10px">
   <form action="/sx/stuManage?action=StuList" method="post">
   
    	<div class="input-group md-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">学号</span>
			  </div>
			  <input  type="text" class="form-control" placeholder="请输入学生学号" id="stuId" name="stuId"/>
			  &nbsp;&nbsp;
			  <button type="submit" class="btn btn-info btn-sm">查&nbsp;询</button>			
	    </div>
    
  </form>
  </div>
    <thead style=" text-align: center;">
      <tr>
        <th>账号ID</th>
        <th>学号</th>
        <th>姓名</th>
        <th>性别</th>
        <th>年龄</th>
        <th>地址</th>
        <th>手机号</th>
        <th>操作</th>
        
      </tr>
    <c:set var="stulist" value="${doPage.list}"></c:set>
	<c:forEach items="${stulist}" var="stu"> 
    </thead>
    
    <tbody style=" text-align: center;">

      <tr>
       <td height="26px"><c:out value="${stu.id }"></c:out></td>
	   <td height="26px"><c:out value="${stu.sid }"></c:out></td>
	   <td height="26px"><c:out value="${stu.sname }"></c:out></td>
	   <td height="26px"><c:out value="${stu.sex }"></c:out></td>
       <td height="26px"><c:out value="${stu.age }"></c:out> </td>
	   <td height="26px"><c:out value="${stu.address}"></c:out></td>
       <td height="26px"><c:out value="${stu.phone }"></c:out></td>
	   <td height="26px">
	       <a href="/sx/stuManage?action=show&id=${stu.id}" class="btn btn-info btn-sm" role="button">修&nbsp;改</a>
	       &nbsp;&nbsp;
	       <a href="/sx/stuManage?action=delete&id=${stu.id}" class="btn btn-danger btn-sm" role="button">删&nbsp;除</a>
	   </td>
      </tr>
     </c:forEach>
    </tbody>
   
  </table>
</div>
 <div class="container" style=" text-align: center; margin:0px auto;">
       <ul class="pagination float-md-right">
          <li class="page-item"> 
             <a class="page-link" href="/sx/stuManage?action=StuList&page=1&sql=${doPage.sql }">首页</a>
          </li>
          <li class="page-item">
             <%--如果当前页面不是第一页，显示上一页选项 --%>
             <c:if test="${doPage.nowPage>1 }">
               <a class="page-link" href="/sx/stuManage?action=StuList&page=${doPage.nowPage-1 }&sql=${doPage.sql}">上一页</a>
             </c:if>
          </li>
          <li class="page-item"> 
             <%--如果当前页面不是最后一页，显示下一页选项 --%>
             <c:if test="${doPage.nowPage<doPage.totalPage}">
                <a class="page-link" href="/sx/stuManage?action=StuList&page=${doPage.nowPage+1 }&sql=${doPage.sql}">下一页</a>
             </c:if>
          </li>
          <li class="page-item">
            <%--显示末页 --%>
            <a class="page-link" href="/sx/stuManage?action=StuList&page=${doPage.totalPage }&sql=${doPage.sql}">末页</a>
          </li>
          <li class="page-item">
            <span class="page-link">共有${doPage.totalPage}页</span>   
          </li>
     </ul>

  </div>
  
</body>
</html>