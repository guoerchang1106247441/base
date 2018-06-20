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
	function save(){
		$("#gform").submit();
	}
	</script>
  </head>
  
  <body>
  	<div class="info_m">
  	<form id="gform" action="${ROOT_PATH}/ManagerMessageController/hf" method="post">
  		<input type="hidden" value="${USER_SESSION.username}" name="username"/>
		<input type="hidden" value="${USER_SESSION.id}" name="uid"/>
		<input type="hidden" value="${message.id}" name="messageid"/>
		<div class="info_item info_item_x">
			<label>标题：${message.name}</label>
		</div>
		<div class="info_item info_item_x">
			<label>内容：${message.content}</label>
		</div>
		<c:forEach items="${list}" var="reply">
			<div class="info_item info_item_x">
				<label>[<fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${reply.ctime}"/>]&nbsp;${reply.username}：${reply.test}</label>
			</div>
		</c:forEach>
		<c:if test="${USER_SESSION.type eq 3}">
			<div class="info_item info_item_x">
				<label>回复内容：<textarea name="test" style="width:400px;height:120px;"></textarea></label>
			</div>
		</c:if>
		<div class="info_item">
			<c:if test="${USER_SESSION.type eq 3}">
				<a class="info_btn info_btn_save" onclick="save();">保存</a>
			</c:if>
			<a class="info_btn info_btn_back" onclick="history.go(-1);">返回</a>
		</div>
	</form>
    </div>
  </body>
</html>