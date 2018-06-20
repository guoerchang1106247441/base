<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<div class="list_m">
		<div calss="list_btn">
			<a class="btn_add"  target="rightFrame" href="${ROOT_PATH}/ManagerParentController/toEdit?id=-1&type=${type}&cz=1">新增</a>
		</div>
		<div class="list_con">
			<table class="tab_list">
				<tr>
					<th width="100">ID</th>
					<th width="100">用户名</th>
					<th width="100">角色类型</th>
					<th width="100">性别</th>
					<th width="100">年龄</th>
					<th width="100">操作</th>
				</tr>
				<c:forEach items="${list}" var="user">
					<tr>
						<td align="center">${user.id}</td>
						<td align="center">${user.username}</td>
						<td align="center">
							<c:if test="${user.type eq 1}">管理员</c:if>
							<c:if test="${user.type eq 2}">家长</c:if>
							<c:if test="${user.type eq 3}">教师</c:if>
						</td>
						<td align="center">
							<c:if test="${user.sex eq 0}">女</c:if>
							<c:if test="${user.sex eq 1}">男</c:if>
						</td>
						<td align="center">${user.age}</td>
						<td align="center">
							<a target="rightFrame" href="${ROOT_PATH}/ManagerParentController/toEdit?id=${user.id}&type=${type}">编辑</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
    
  </body>
</html>