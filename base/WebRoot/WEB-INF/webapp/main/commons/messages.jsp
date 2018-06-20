<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- Error Messages --%>
<c:if test="${FLASH_SUCCESS_ERROR != null}">
	<script type="text/JavaScript"> 
		$(document).ready(function(){
			layer.alert('${FLASH_SUCCESS_ERROR}', {icon: 2, skin: 'layer-ext-moon'})
			$.post("${ROOT_PATH}/PubSelect/removeFlashError");
		});
	</script>
</c:if>
<%-- Info Messages --%>
<c:if test="${FLASH_SUCCESS_SUCCESS != null}">
	<script type="text/JavaScript"> 
		$(document).ready(function(){
			layer.alert('${FLASH_SUCCESS_SUCCESS}', {icon: 1, skin: 'layer-ext-moon'})
			$.post("${ROOT_PATH}/PubSelect/removeFlashSuccess");
		});
	</script>
</c:if>