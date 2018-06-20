<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>注册</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="${DOC_PATH }/layui-ex/css/admin.css" type="text/css" charset="utf-8"/>
<link rel="stylesheet" href="${DOC_PATH }/layui-ex/css/login.css" type="text/css" charset="utf-8"/>
<style type="text/css">
.login_body{overflow: hidden;}
.login_main{background: url("${IMG_PATH}/login.jpg") no-repeat;background-size: cover;width: 100%;height:100vh;overflow: auto;}
.login_main_bg{background-color: #fff;}
</style>
</head>
<body class="login_body layui-layout-body login_body">
	<div class="login_main">
		<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-reg" >
			<div class="layadmin-user-login-main login_main_bg">
				<form id="gform" action="${ROOT_PATH}/manager/reg/doReg">
				<div class="layadmin-user-login-box layadmin-user-login-header">
					<h2>注册</h2>
					<p>layui 官方出品的单页面后台管理模板系统</p>
				</div>
				<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
						<input type="text" name="userid" id="userid" placeholder="用户名" class="layui-input" maxlength="30">
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-user" for="LAY-user-login-user"></label>
						<input type="text" name="username" id="username" placeholder="昵称" class="layui-input" maxlength="30">
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
						<input type="password" name="password" id="password1" placeholder="密码" class="layui-input" maxlength="30">
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password1"></label>
						<input type="password" name="password2" id="password2" placeholder="确认密码" class="layui-input" maxlength="30">
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid" id="reg_sub" >注册</button>
					</div>
					<div class="layui-trans layui-form-item layadmin-user-login-other">
						<a href="${ROOT_PATH}/manager/login/index" class="layadmin-user-jump-change layadmin-link">用已有账号登录</a>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script>
$(document).ready(function(){
	$("#reg_sub").click(function(){
		var userid = $("#userid").val();
		if(!gScript.checkIsInput(userid, "用户名")){
			return false;
		}
		if(!gScript.checkStr(userid, "用户名")){
			return false;
		}
		var username = $("#username").val();
		if(!gScript.checkIsInput(username, "昵称")){
			return false;
		}
		var password1 = $("#password1").val();
		if(!gScript.checkIsInput(password1, "密码")){
			return false;
		}
		if(password1.length < 6){
			gAlertErr("密码长度不能小于6位");
			return false;
		}
		if(!gScript.checkStr(password1, "密码")){
			return false;
		}
		var password2 = $("#password2").val();
		if(password1 != password2){
			gAlertErr("两次密码不一致");
			return false;
		}
		
		$("#reg_sub").attr("disabled", true);
		$("#gform").submit();
	});
});
</script>
</html>
<%@ include file="/WEB-INF/webapp/main/commons/messages.jsp"%>