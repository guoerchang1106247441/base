<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<b:override name="content">
<style>
.file_btn{position: absolute;right:0px;top:0px;opacity: 0;font-size: 28px;cursor: pointer;margin: 0;padding: 0;}
</style>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">设置我的资料</div>
				<div class="layui-card-body" pad15="">
					<form class="layui-form" action="${ROOT_PATH }/manager/menber/editUser" enctype="multipart/form-data" method="post">
						<div class="layui-form-item">
							<label class="layui-form-label">我的角色</label>
							<div class="layui-input-inline">
								<select name="role" lay-verify="">
									<option value="1" selected="">超级管理员</option>
									<option value="2" disabled="">普通管理员</option>
									<option value="3" disabled="">审核员</option>
									<option value="4" disabled="">编辑人员</option>
								</select>
							</div>
							<div class="layui-form-mid layui-word-aux">当前角色不可更改为其它角色</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">用户名</label>
							<div class="layui-input-inline">
								<input type="text" name="userid" value="${user.userid}" readonly="" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">昵称</label>
							<div class="layui-input-inline">
								<input type="text" name="username" value="${user.username}" autocomplete="off" placeholder="请输入昵称" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">性别</label>
							<div class="layui-input-block">
								<input type="radio" name="sex" id="sex1" value="1" title="男">
								<div class="layui-unselect layui-form-radio">
									<i class="layui-anim layui-icon"></i>
									<div>男</div>
								</div>
								<input type="radio" name="sex" id="sex0" value="0" title="女" >
								<div class="layui-unselect layui-form-radio layui-form-radioed">
									<i class="layui-anim layui-icon layui-anim-scaleSpring"></i>
									<div>女</div>
								</div>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">年龄</label>
							<div class="layui-input-inline">
								<input type="text" name="age" value="${user.age }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">头像</label>
							<div class="layui-input-inline">
								<input type="text" name="img" id="img" value="" readonly="" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux" style="padding: 0px !important;">
								<button type="button" class="layui-btn" style="position: relative;overflow: hidden;">上传图片
									<input type="file" class="file_btn" name="fileImg" id="fileImg" onchange="oncheckFile(this)"/>
								</button>
							</div> 
							
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">手机</label>
							<div class="layui-input-inline">
								<input type="text" name="phone" value="${user.phone }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">邮箱</label>
							<div class="layui-input-inline">
								<input type="text" name="email" value="${user.email }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">地址</label>
							<div class="layui-input-inline">
								<input type="text" name="addr" value="${user.addr}" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn">确认修改</button>
								<button type="reset" class="layui-btn layui-btn-primary">重新填写</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
layui.use('form', function(){
	var form = layui.form;
});
$(document).ready(function(){
	//加载性别
	sex();
});


//选中性别，如果没有性别，默认选中第一个
function sex(){
	var sex = "${user.sex}";
	if(sex != "" && sex == 0){
		$("#sex0").attr("checked",true);
	}else{
		$("#sex1").attr("checked",true);
	}
}

//上传图片
function oncheckFile(obj){
	var gs = ["png", "jpg","jpeg" ];
	var flag = false;
	var errMsg = "";
	var value = $(obj).val();
	if(value != ''){
		if(value.indexOf('.') != -1){
			for(var j = 0;j < gs.length; j++){
				var hz = value.split(".");
				if(hz[hz.length - 1].toLowerCase() == gs[j]){
					flag = true;
					break;
				}	
			}
		}
		if(!flag){
			errMsg = "文件格式不正确";
		}
		var size = $(obj)[0].files[0].size;
		if(flag && size > 1024*1024*2){
			flag = false;
			errMsg = "文件大小不能大于2M";
		}
		if(!flag){
			gAlert(errMsg);	
			$("#img").val("");
			var file = obj;
			file.value = "";  // firefox, chrome, safari 
    		if ( file.value ) { 
    			if ( document.all ) { // ie 
        			with( file.parentNode.insertBefore(document.createElement('form'), file)) { 
            			appendChild(file); reset(); removeNode(false); 
        			} 
    			} else { // opera 
        			file.type = "text"; file.type = "file"; 
    			} 
    		} 
			return false;
		}
	}
	$("#img").val(value);
}

</script>
</b:override>
<%@ include file="/WEB-INF/webapp/main/models/default-template.jsp"%>