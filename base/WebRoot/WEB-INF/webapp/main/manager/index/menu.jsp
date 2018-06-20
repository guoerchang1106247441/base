<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<ul class="layui-nav layui-nav-tree">
	<c:forEach items="${MENU_SESSION}" var="menuDir">
		<li class="layui-nav-item layui-nav-itemed">
			<a class="" href="javascript:;">${menuDir.name}</a>
			<dl class="layui-nav-child">
				<c:forEach items="${menuDir.menuLs}" var="menu">
					<dd class="layui-nav"><a href="${ROOT_PATH}/${menu.url}">${menu.name}</a></dd>
				</c:forEach>
			</dl>
		</li>
	</c:forEach>
</ul>
