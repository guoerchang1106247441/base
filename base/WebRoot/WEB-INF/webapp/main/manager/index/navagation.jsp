<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<div class="layui-logo">后台管理</div>
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
			<dd><a href="${ROOT_PATH}/manager/menber/info">基本资料</a></dd>
			<dd><a href="">修改密码</a></dd>
		</dl>
	</li>
	<li class="layui-nav-item"><a href="${ROOT_PATH }/manager/login/extLogin">退出</a></li>
</ul>
