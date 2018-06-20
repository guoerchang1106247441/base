<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/webapp/main/inc/taglibs.inc"%>
<%@ include file="/WEB-INF/webapp/main/commons/meta-model.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script>
	$(document).ready(function(){
		var sex = "${user.sex}";
		$("#sex_" + sex).attr("checked",true);
	});
	
	//保存
	function save(){
		$("#gform").submit();
	}
	</script>
  </head>
  
  <body>
  	<div class="info_m">
	<form id="gform" action="${ROOT_PATH}/ManagerParentController/edit" method="post">
		<input type="hidden" name="czType" value="${type}"/>
		<input type="hidden" name="cz" value="${cz}"/>
		<input type="hidden" name="id" value="${user.id}"/>
		<div class="info_item info_item_x">
			<label>用户名：<input type="text" value="${user.username}" name="username"/></label>
		</div>
		<div class="info_item info_item_x">
			<label>密码：<input type="text" value="${user.password}" name="password"/></label>
		</div>
		<div class="info_item info_item_x">
			<label>
				角色类型：
				<select name="type">
					<option value="1" <c:if test="${user.type eq 1}">selected='selected'</c:if>>管理员</option>
					<option value="2" <c:if test="${user.type eq 2}">selected='selected'</c:if>>家长</option>
					<option value="3" <c:if test="${user.type eq 3}">selected='selected'</c:if>>教师</option>
				</select>
			</label>
		</div>
		<div class="info_item info_item_x">
			<label>性别：
				<input type="radio" name="sex" id="sex_1" value="1"/>男&nbsp;&nbsp;
				<input type="radio" name="sex" id="sex_0" value="0"/>女
			</label>
		</div>
		<div class="info_item info_item_x">
			<label>年龄：<input type="text" value="${user.age}" name="age"/></label>
		</div>
		<div class="info_item">
			<a class="info_btn info_btn_save" onclick="save();">保存</a>
			<a class="info_btn info_btn_back" onclick="history.go(-1);">返回</a>
		</div>
	</form>
    </div>
  </body>
</html>