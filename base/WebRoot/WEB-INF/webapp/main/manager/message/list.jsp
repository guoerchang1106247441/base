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
	
	
  </head>
  
  <body>
	<div class="list_m">
	<form id="gform" action="" method="post">
		<div calss="list_btn">
			<a class="btn_add"  target="rightFrame" href="${ROOT_PATH}/ManagerMessageController/toAdd">新增</a>
		</div>
		<div class="list_con">
			<table class="tab_list">
				<tr>
					<th width="100">ID</th>
					<th width="200">标题</th>
					<th width="400">留言内容</th>
					<th width="100">留言时间</th>
					<th width="100">状态</th>
					<th width="100">留言者</th>
					<th width="100">操作</th>
				</tr>
				<c:forEach items="${list}" var="message">
					<tr>
						<td align="center">${message.id}</td>
						<td align="left">${fn:substring(message.name,0,12)}</td>
						<td align="left">${fn:substring(message.content,0,24)}</td>
						<td align="center"><fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${message.ctime}"/></td>
						<td align="center">
							<c:if test="${message.status eq 0}">未审核</c:if>
							<c:if test="${message.status eq 1}">审核通过</c:if>
							<c:if test="${message.status eq 2}">审核不通过</c:if>
						</td>
						<td align="center">${message.username}</td>
						<td align="center">
							<c:if test="${USER_SESSION.type eq 1}">
								<c:if test="${message.status eq 0}">
									<a target="rightFrame" href="javascript:void(0)" onclick="sh('${message.id}')">审核</a><br/>
								</c:if>
							</c:if>
							<a target="rightFrame" href="javascript:void(0)" onclick="view('${message.id}')">查看</a><br/>
							<c:if test="${USER_SESSION.type eq 3}">
								<a target="rightFrame" href="javascript:void(0)" onclick="hf('${message.id}')">回复</a><br/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		</form>
	</div>
    <script>
	//审核
	function sh(id){
		if(confirm("确定要审核通过这条留言吗？")){
			$("#gform").attr("action","${ROOT_PATH}/ManagerMessageController/update?id=" + id);
			$("#gform").submit();
		}
	}
	//查看留言
	function view(id){
		$("#gform").attr("action","${ROOT_PATH}/ManagerMessageController/info?id=" + id);
		$("#gform").submit();
	}
	
	//回复留言
	function hf(id){
		$("#gform").attr("action","${ROOT_PATH}/ManagerMessageController/toHf?id=" + id);
		$("#gform").submit();
	}
	</script>
  </body>
</html>