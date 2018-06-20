<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<style>
.bjui-row .input_l{height:30px;line-height: 30px;}
.bjui-row .input_w{width:220px;}
.bjui-row .lab_l{height:30px;line-height: 30px;}
.bjui-row .lab_w{width:80px;}
.btn-group .btn{height:30px;}
.btn-group .text_l{height:60px;}
.btn_item{width:100%;height:40px;line-height: 40px;text-align: center;}
.btn_i{width:30px;height:30px;}
</style>
<form action="${ROOT_PATH}/ManagerRegController/reg" method="post" id="regForm">
<div class="bjui-row">
	<label class="row-label lab_l lab_w">账号</label>
	<div class="row-input required">
		<input type="text" class="input_l input_w" id="userid" name="userid" maxlength="30" data-rule="required">
	</div>
	<label class="row-label lab_l lab_w">名称</label>
	<div class="row-input">
		<input type="text" class="input_l input_w" id="username" name="username" maxlength="30">
	</div>
	<label class="row-label lab_l lab_w">密码</label>
	<div class="row-input required">
		<input type="text" class="input_l input_w" id="password1" name="password" maxlength="30" data-rule="required">
	</div>
	<label class="row-label lab_l lab_w">确认密码</label>
	<div class="row-input required">
		<input type="text" class="input_l input_w" id="password2" maxlength="30" data-rule="required">
	</div>
	<label class="row-label lab_l lab_w">角色类型</label>
	<div class="row-input">
		<select data-toggle="selectpicker" name="type" id="type">
			<option value="">请选择</option>
			<c:forEach items="${roleLs}" var="role">
				<option value="${role.rolecode}">${role.rolename}</option>
			</c:forEach>
		</select>
	</div>
	<label class="row-label lab_l lab_w">性别</label>
	<div class="row-input input_l">
		<input type="radio" name="sex" id="sex1" value="1" data-toggle="icheck" data-label="男" checked="">&nbsp;&nbsp;
		<input type="radio" name="sex" id="sex0" value="0" data-toggle="icheck" data-label="女">
	</div>
	<label class="row-label lab_l lab_w">年龄</label>
	<div class="row-input">
		<input type="text" class="input_l" id="age" name="age" value="1" data-toggle="spinner" data-min="0" data-max="120" size="10">
	</div>
	<label class="row-label lab_l lab_w">手机号</label>
	<div class="row-input">
		<input type="text" class="input_l input_w" maxlength="11" id="phone" name="phone">
	</div>
	<label class="row-label lab_l lab_w">邮箱</label>
	<div class="row-input">
		<input type="text" class="input_l input_w" maxlength="50" id="email" name="email">
	</div>
	<label class="row-label lab_l lab_w">地址</label>
	<div class="row-input">
		<textarea class="text_l input_w" id="addr" maxlength="50" name="addr"></textarea>
	</div>
	<div class="btn_item">
		<button type="button" id="btn_sub" class="btn-blue btn-nm" data-icon="save">注册</button>
		<button type="button" id="btn_close" class="btn-default btn-nm" data-icon="close" style="margin-left: 30px;">关闭</buutton>
	</div>
</div>
</form>
<script>
$(document).ready(function(){
	//注册
	$("#btn_sub").click(function(){
		var userid = $("#userid").val();
		if(!gScript.checkIsInput(userid, "账号")){
			return false;
		}
		if(!gScript.checkStr(userid, "账号")){
			return false;
		}
		var password1 = $("#password1").val();
		if(!gScript.checkIsInput(password1, "密码")){
			return false;
		}
		if(password1.length < 6){
			gAlert("密码长度不能小于6位");
			return false;
		}
		if(!gScript.checkStr(password1, "密码")){
			return false;
		}
		var password2 = $("#password2").val();
		if(password1 != password2){
			gAlert("两次密码不一致");
			return false;
		}
		var type = $("#type").val();
		if(type.length == 0){
			gAlert("请选择角色类型");
			return false;
		}
		var age = $("#age").val();
		if(age.length > 0){
			if(!gScript.checkNumber(age, 0, "年龄")){
				return false;
			}
		}
		var phone = $("#phone").val();
		if(phone.length > 0){
			if(!gScript.checkMobile(phone, "手机号")){
				return false;
			}
		}
		var email = $("#email").val();
		if(email.length > 0){
			if(!gScript.checkEmail(email, "邮箱")){
				return false;
			}
		}
		$("#btn_sub").attr("disabled", true);
		$("#regForm").submit();
	});
	//关闭
	$("#btn_close").click(function(){
		colseGialog();
	});
});
</script>