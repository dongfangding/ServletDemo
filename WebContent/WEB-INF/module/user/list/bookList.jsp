<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品</title>
<style type="text/css">
	.title {
		text-align:center;
		line-height: 30px;
	}
	.operate {
		text-align: center;
		text-decoration: none;
	}
</style>
</head>
<body>
<!-- 包含主页 -->
<%@include file="/WEB-INF/module/user/public/header.jsp" %>

<table border="1" cellpadding="1" cellspacing="1" width="700" align="center" style="margin-top: 100px;">
	<tr>
		<th style="text-align:center;line-height: 40px;" colspan="4">商品列表</th>
	</tr>
	<tr class="titleTr">
		<td class="title"><b>书名</b></td>
		<td class="title" width="60" ><b>价格</b></td>
		<td class="title"><b>介绍</b></td>
		<td class="title"width="100"><b>操作</b></td>
	</tr>
<c:forEach var="book" items="${bookList }">
	<tr>
		<td>${book.bookName }</td>
		<td>${book.price }</td>
		<td>${book.description }</td>
		<td class="operate"><a href="${pageContext.request.contextPath }/bookBuyServlet.do?bookId=${book.id }">购买</a></td>
	</tr>
</c:forEach>
</table>
</body>
</html>