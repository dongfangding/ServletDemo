<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 style="text-align:center;">
	Servlet Demo 演示
</h2>

<div style="text-align:right;padding-right:50px;">
	<c:if test="${user == null }">
		<a href="${pageContext.request.contextPath }/userLoginJspServlet.do" >登录</a> &nbsp;&nbsp;
	</c:if>
	<c:if test="${user != null }">
		<b>${user.username }</b>&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/userLoginJspServlet.do" >切换帐号</a> &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/userLoginOutServlet.do" >注销</a> &nbsp;&nbsp;
	</c:if>
	<a href="${pageContext.request.contextPath }/userRegisterJspServlet.do">注册</a> <br />
</div>

<div style="padding-left: 200px;">
	<a href = "${pageContext.request.contextPath }/userIndexJspServlet.do">首页</a>&nbsp;&nbsp;
	<a href = "${pageContext.request.contextPath }/bookListServlet.do">商城</a>
</div>
<br/>