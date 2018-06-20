<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
  <head>

  </head>
  	<frameset rows="12%,*" frameborder="yes" border="1" name="mainFrame">
  		<frame src="${ROOT_PATH}/ManagerIndexController/navagation" name="top" noresize="noresize" scrolling="no"></frame>
  		
  		<frameset cols="15%,*" frameborder="yes" border="1" >
		  	<frame src="${ROOT_PATH}/ManagerIndexController/menu" name="leftFrame"  noresize="noresize" scrolling="no"></frame>
		  	<frame src="" name="rightFrame" noresize="noresize" scrolling="no"></frame>
  		</frameset>
  		
  	</frameset>
  
</html>
