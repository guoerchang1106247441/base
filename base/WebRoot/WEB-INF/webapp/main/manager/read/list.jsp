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
			<c:if test="${USER_SESSION.type eq 3}">
				<a class="btn_add"  target="rightFrame" href="${ROOT_PATH}/ManagerTaskController/toAdd">新增</a>
			</c:if>
		</div>
		<div class="list_con">
			<table class="tab_list">
				<tr>
					<th width="100">ID</th>
					<th width="200">标题</th>
					<th width="400">内容</th>
					<th width="100">时间</th>
					<th width="100">状态</th>
					<th width="100">发布人</th>
					<th width="100">操作</th>
				</tr>
				<c:forEach items="${list}" var="task">
					<tr>
						<td align="center">${task.id}</td>
						<td align="left">${fn:substring(task.title,0,12)}</td>
						<td align="left">${fn:substring(task.content,0,24)}</td>
						<td align="center"><fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${task.ctime}"/></td>
						<td align="center">
							<c:if test="${task.status eq 0}">未审核</c:if>
							<c:if test="${task.status eq 1}">审核通过</c:if>
						</td>
						<td align="center">${task.username}</td>
						<td align="center">
							<c:if test="${USER_SESSION.type eq 1}">
								<c:if test="${task.status eq 0}">
									<a target="rightFrame" href="javascript:void(0)" onclick="sh('${task.id}')">审核</a><br/>
								</c:if>
							</c:if>
							<a target="rightFrame" href="javascript:void(0)" onclick="view('${task.id}')">查看</a><br/>
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
		if(confirm("确定要审核通过这条信息吗？")){
			$("#gform").attr("action","${ROOT_PATH}/ManagerTaskController/update?id=" + id);
			$("#gform").submit();
		}
	}
	//查看留言
	function view(id){
		$("#gform").attr("action","${ROOT_PATH}/ManagerTaskController/info?id=" + id);
		$("#gform").submit();
	}
	</script>
  </body>
</html>