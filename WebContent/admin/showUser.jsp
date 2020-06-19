<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>查看用户信息</title>
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
       <span style="font-size:20px;">用户信息</span> 
    </li>
    </ul>
  </div>      
  <table class="table  table-striped">
  <div style="width:50%; margin-bottom:10px">
   <form action="/sx/userManage?action=UserList" method="post">
   
    	<div class="input-group md-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">用户ID</span>
			  </div>
			  <input  type="text" class="form-control" placeholder="请输入用户ID" id="userId" name="userId"/>
			  &nbsp;&nbsp;
			  <button type="submit" class="btn btn-info btn-sm">查&nbsp;询</button>			
	    </div>
    
  </form>
  </div>
    <thead style=" text-align: center;">
      <tr>
        <th>账号id</th>
        <th>账号</th>
        <th>密码</th>
        <th>用户类型</th>
        <th>注册时间</th>
        <th>账号状态</th>
        <th>操作</th>
        
      </tr>
    <c:set var="userlist" value="${doPage.list}"></c:set>
	<c:forEach items="${userlist}" var="user"> 
    </thead>
    
    <tbody style=" text-align: center;">

      <tr>
	   <td height="26px"><c:out value="${user.id }"></c:out></td>
	   <td height="26px"><c:out value="${user.username }"></c:out></td>
	   <td height="26px"><c:out value="${user.password }"></c:out></td>
       <td height="26px">
          <c:if test="${user.usertypes == 'admin'}">
              <span>管理员</span>
          </c:if>
           <c:if test="${user.usertypes == 'student'}">
              <span>学生</span>
          </c:if>
       </td>
	   <td height="26px"><c:out value="${user.ctime}"></c:out></td>
       <td height="26px">
	     <c:if test="${user.del_status == '1' }">
	         <p class="bg-danger text-white">已注销</p>
	     </c:if>
	     <c:if test="${user.del_status == '2' }">
	         <p class="bg-success text-white">正常</p>
	     </c:if>
	   </td>
	   <td height="26px">
	      <c:if test="${user.del_status == '1'}">
	         <a href="/sx/userManage?action=retrieve&id=${user.id}" class="btn btn-info btn-sm" role="button">找&nbsp;回</a>
          </c:if>
          <c:if test="${user.del_status == '2'}">
              <a href="/sx/userManage?action=delete&id=${user.id}" class="btn btn-secondary btn-sm" role="button">注&nbsp;销</a>
              <a href="/sx/admin/editUser.jsp?id=${user.id}" class="btn btn-primary btn-sm" role="button">修改密码</a>                  
          </c:if>
	     

	   </td>
      </tr>
     </c:forEach>
    </tbody>
   
  </table>
</div>
 <div class="container" style=" text-align: center; margin:0px auto;">
       <ul class="pagination float-md-right">
          <li class="page-item"> 
             <a class="page-link" href="/sx/userManage?action=UserList&page=1&sql=${doPage.sql }">首页</a>
          </li>
          <li class="page-item">
             <%--如果当前页面不是第一页，显示上一页选项 --%>
             <c:if test="${doPage.nowPage>1 }">
               <a class="page-link" href="/sx/userManage?action=UserList&page=${doPage.nowPage-1 }&sql=${doPage.sql}">上一页</a>
             </c:if>
          </li>
          <li class="page-item"> 
             <%--如果当前页面不是最后一页，显示下一页选项 --%>
             <c:if test="${doPage.nowPage<doPage.totalPage}">
                <a class="page-link" href="/sx/userManage?action=UserList&page=${doPage.nowPage+1 }&sql=${doPage.sql}">下一页</a>
             </c:if>
          </li>
          <li class="page-item">
            <%--显示末页 --%>
            <a class="page-link" href="/sx/userManage?action=UserList&page=${doPage.totalPage }&sql=${doPage.sql}">末页</a>
          </li>
          <li class="page-item">
            <span class="page-link">共有${doPage.totalPage}页</span>   
          </li>
     </ul>

  </div>
  

</body>
</html>