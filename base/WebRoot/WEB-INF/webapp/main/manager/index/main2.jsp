<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>后台管理</title>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		
		<!-- 头部导航 -->
		<div class="layui-header">
			<div class="layui-logo">后台布局</div>
			<ul class="layui-nav layui-layout-left">
				
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="javascript:;">
						<c:choose>
							<c:when test="${not empty USER_SESSION.img}">
								<img src="${DOC_PATH}/${USER_SESSION.img}" class="layui-nav-img">
							</c:when>
							<c:otherwise>
								<img src="${DOC_PATH}/img/user.png" class="layui-nav-img">
							</c:otherwise>
						</c:choose>
						${USER_SESSION.username}
					</a>
					<dl class="layui-nav layui-nav-child" lay-filter="child">
						<dd><a href="" lay-id="user-info">基本资料</a></dd>
						<dd><a href="" lay-id="user-pwd">修改密码</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item"><a href="">退了</a></li>
			</ul>
		</div>
  
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree"  lay-filter="test">
					<c:forEach items="${MENU_SESSION}" var="menuDir">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="" href="javascript:;">${menuDir.name}</a>
							<dl class="layui-nav-child">
								<c:forEach items="${menuDir.menuLs}" var="menu">
									<dd class="layui-nav" lay-filter="tree"><a lay-href="${ROOT_PATH}/${menu.url}">${menu.name}</a></dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<!-- 内容主体区域 -->
		<div class="layui-body">
			<div style="padding: 15px;">内容主体区域</div>
		</div>
	</div>
<script>
layui.use('element', function(){
	var element = layui.element;
});
</script>
</body>
</html>