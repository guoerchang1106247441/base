<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<!DOCTYPE html>
<html>
<head>
	<b:block name="prevhead" />
	<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
	<b:block name="head" />
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<%-- 头部 --%>
		<div class="layui-header">
			<%@ include file="/WEB-INF/webapp/main/manager/index/navagation.jsp"%>
		</div>
		<!-- 菜单 -->
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<%@ include file="/WEB-INF/webapp/main/manager/index/menu.jsp"%>
			</div>
		</div>
		<!-- 内容 -->
		<div class="layui-body" style="background-color: #f2f2f2;bottom: 0px;">
			<div style="padding: 15px 0;">
				<b:block name="content" />
			</div>
		</div>
		<!-- 提示信息 -->
		<%@ include file="/WEB-INF/webapp/main/commons/messages.jsp"%>
	</div>
	<script>
	layui.use('element', function(){
		var element = layui.element;
	});
	</script>
</body>
</html>
