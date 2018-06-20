<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  <body>
  	<center>
  	<br><h2>导航栏</h2>
  	<c:forEach items="${MENU_SESSION}" var="menu">
  		<br/><a href="${ROOT_PATH}/${menu.url}" target="rightFrame"><h3>${menu.name}</h3></a><br/>
  	</c:forEach>
  	</center>
  </body>
</html>
