<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
	<title>登录</title>
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
	<div id="" class=" login_main">
		<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" >
			<div class="layadmin-user-login-main login_main_bg">
				<form id="gform" action="${ROOT_PATH}/manager/login/doLogin">
				<div class="layadmin-user-login-box layadmin-user-login-header">
					<h2>layuiAdmin</h2>
					<p>layui 官方出品的单页面后台管理模板系统</p>
				</div>
				<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
						<input type="text" name="userid" id="userid" placeholder="用户名" class="layui-input">
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
						<input type="password" name="password" id="password" placeholder="密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<div class="layui-row">
							<div class="layui-col-xs7">
								<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
								<input type="text" name="captcha" id="captcha" placeholder="图形验证码" class="layui-input">
							</div>
							<div class="layui-col-xs5">
								<div style="margin-left: 10px;">
									<img src="<c:url value='/authimg.img'/>" class="layadmin-user-login-codeimg" id="captcha_img" onclick="javascript:onclickimg();">
								</div>
							</div>
						</div>
					</div>
					<div class="layui-form-item" style="margin-bottom: 20px;">
						<a class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid" id="login_sub">登录</button>
					</div>
					<div class="layui-trans layui-form-item layadmin-user-login-other">
						<a href="${ROOT_PATH}/manager/reg/index" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script>
//点击验证码，重新获取验证码
function onclickimg(){
	var d = new Date().getTime();
	document.getElementById("captcha_img").src = "${ROOT_PATH}/authimg.img?aa=" + d;
}

$(document).ready(function(){
	$("#login_sub").click(function(){
		var userid = $("#userid").val();
		if(userid == ""){
			gAlert("请输入用户名");
			return;
		}
		var password = $("#password").val();
		if(password == ""){
			gAlert("请输入密码");
			return;
		}
		var captcha = $("#captcha").val();
		if(captcha == ""){
			gAlert("请输入验证码");
			return;
		}
		$("#password").val(gScript.getPwdMd5(password, "${ROOT_PATH}"));
		$("#login_sub").attr("disabled", true);
		$("#gform").submit();
	});
});
</script>
</html>
<%@ include file="/WEB-INF/webapp/main/commons/messages.jsp"%>