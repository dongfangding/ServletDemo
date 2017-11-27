<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="module.user.entity.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<!-- 包含主页 -->
<%@include file="/WEB-INF/module/user/public/header.jsp" %>

<div style="padding-left: 200px;">
<c:if test="${user != null }">
	<%
		User user = (User) session.getAttribute("user");
		String userLastLoginDate = user.getUsername() + "LastLoginDate";
		String dateStr = (String) session.getAttribute(userLastLoginDate);
		if (dateStr == null) {
			dateStr = "";
		}
		System.out.println(request.getServletContext().getAttribute("LAST_PATH"));
	%>
	欢迎回来！${user.username }
	<%=dateStr%>
	<!-- 实际上是username+LastLoginDate，在这里不知道怎么拼接 -->
	<%-- <c:if test="${adminLastLoginDate != null }">
		您上次登录的时间是${adminLastLoginDate } 
	</c:if> --%>
	
	
	<c:if test="${LAST_PATH != null }">
		<a href = "${pageContext.request.contextPath }/${LAST_PATH}" > 返回登陸前界面</a>
	</c:if>
</c:if>
</div>

<c:if test="${loginRepet != null }">
	${loginRepet }
</c:if>

</body>
</html>