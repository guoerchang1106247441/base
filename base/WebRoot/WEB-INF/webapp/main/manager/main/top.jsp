<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
  

  </head>
  <body> 
  	<center>
  	<h1>欢迎使用在线互动交流平台</h1>
  	<div style="float: right;margin-right: 30px;">
  		<a>欢迎你,${USER_SESSION.username}[
  			<c:if test="${USER_SESSION.type eq 1}">管理员</c:if>
  			<c:if test="${USER_SESSION.type eq 2}">家长</c:if>
  			<c:if test="${USER_SESSION.type eq 3}">教师</c:if>
  		]</a>&nbsp;&nbsp;&nbsp;&nbsp;
  		<a href="${ROOT_PATH}/ManagerLoginController/extLogin" target="mainFrame">退出登录</a>
  	</div>
  	</center>
  </body>
</html>
