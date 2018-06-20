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
	</script>
  </head>
  
  <body>
  	<div class="info_m">
  	<form id="gform" action="" method="post">
		<div class="info_item info_item_x">
			<label>标题：${task.title}</label>
		</div>
		<div class="info_item info_item_x">
			<label>内容：${task.content}</label>
		</div>
		<div class="info_item info_item_x">
			<label>附件：${task.file}&nbsp;&nbsp;
			<c:if test="${not empty task.file }">
				<a href="${ROOT_PATH}/ManagerTaskController/download?img=${task.file}&uid=${task.uid}">下载附件</a>
			</c:if>
			</label>
		</div>
		<div class="info_item">
			<a class="info_btn info_btn_back" onclick="history.go(-1);">返回</a>
		</div>
	</form>
    </div>
  </body>
</html>