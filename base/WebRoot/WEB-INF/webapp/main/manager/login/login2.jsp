<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
html, body { height: 100%; overflow: hidden; }
body {
    font-family: "Verdana", "Tahoma", "Lucida Grande", "Microsoft YaHei", "Hiragino Sans GB", sans-serif;
    background: url(images/loginbg_01.jpg) no-repeat center center fixed;
    background-size: cover;
}
.form-control{height:37px;}
.main_box{position:absolute; top:45%; left:50%; margin:-200px 0 0 -180px; padding:15px 20px; width:360px; height:400px; min-width:320px; background:#FAFAFA; background:rgba(255,255,255,0.5); box-shadow: 1px 5px 8px #888888; border-radius:6px;}
.login_msg{height:30px;}
.input-group >.input-group-addon.code{padding:0;}
#captcha_img{cursor:pointer;}
.main_box .logo img{height:35px;}
@media (min-width: 768px) {
    .main_box {margin-left:-240px; padding:15px 55px; width:480px;}
    .main_box .logo img{height:40px;}
}
.logo_title{font-size: 24px;}
</style>
</head>
<body>
<div class="container">
    <div class="main_box">
        <form action="${ROOT_PATH}/ManagerLoginController/login" id="gform" method="post">
            <input type="hidden" id="url" name="url" value="<c:out value="${url}" />" />
            <!-- 使用图片 -->
            <%-- <p class="text-center logo"><img src="${IMG_PATH}/logo.png" height="45"></p> --%>
            <!-- 使用文字 -->
            <p class="text-center logo_title">管理系统</p>
            <div class="login_msg text-center"><font color="red"></font></div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon-user"><span class="glyphicon glyphicon-user"></span></span>
                    <input type="text" class="form-control" id="logid" name="userid" value="" placeholder="登录账号" aria-describedby="sizing-addon-user">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon-password"><span class="glyphicon glyphicon-lock"></span></span>
                    <input type="password" class="form-control" id="logpassword" name="password" placeholder="登录密码" aria-describedby="sizing-addon-password">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon-password"><span class="glyphicon glyphicon-exclamation-sign"></span></span>
                    <input type="text" class="form-control" id="captcha" name="captcha" placeholder="验证码" aria-describedby="sizing-addon-password">
                    <span class="input-group-addon code" id="basic-addon-code"><img id="captcha_img" src="<c:url value='/authimg.img'/>" onclick="javascript:onclickimg();" alt="点击更换" title="点击更换" class="m"></span>
                </div>
            </div>
            <div class="form-group">
                <div class="checkbox">
                    <label for="j_remember" class="m"></label>
                </div>
            </div>
            <div class="text-center">
                <button type="button" id="login_ok" class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp;</button>&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="reset" class="btn btn-default btn-lg">&nbsp;重&nbsp;置&nbsp;</button>
            </div>
            <div class="text-center">
                <hr>
                <a href="javascript:void(0)" onclick="reg();">立即注册</a>
            </div>
        </form>
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
	$("#login_ok").click(function(){
		var logid = $("#logid").val();
		if(logid == ""){
			gAlert("请输入账号");
			return;
		}
		var logpassword = $("#logpassword").val();
		if(logpassword == ""){
			gAlert("请输入密码");
			return;
		}
		var captcha = $("#captcha").val();
		if(captcha == ""){
			gAlert("请输入验证码");
			return;
		}
		var url = $("#url").val();
		url = encodeURI(url);
		$("#url").val(url);
		$("#password").val(gScript.getPwdMd5(password, "${ROOT_PATH}"));
		$("#login_ok").attr("disabled", true);
		$("#gform").submit();
	});
});

function reg(){
	showGialog("注册", "${ROOT_PATH}/ManagerRegController/index", 400, 550);
}
</script>
</html>
<%@ include file="/WEB-INF/webapp/main/commons/messages.jsp"%>