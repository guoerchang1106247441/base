<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/WEB-INF/webapp/main/tags/block-tag.tld" prefix="b"%>
<c:set var="ROOT_PATH" value="${pageContext.request.contextPath eq '/'?'':pageContext.request.contextPath}" />
<c:set var="DOC_PATH" value="${ROOT_PATH}/webapp/resources/manager/static" />
<c:set var="IMG_PATH" value="${ROOT_PATH}/webapp/resources/manager/static/img" />
