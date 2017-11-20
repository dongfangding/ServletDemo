<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
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
		<th style="text-align:center;line-height: 40px;" colspan="4">购物车列表</th>
	</tr>
	<tr class="titleTr">
		<td class="title"><b>书名</b></td>
		<td class="title"><b>数量</b></td>
		<td class="title" width="60" ><b>价格</b></td>
		<td class="title"width="100"><b>操作</b></td>
	</tr>
<c:forEach var="bookCart" items="${bookCartList }">
	<tr>
		<td>${bookCart.value.book.bookName }</td>
		<td>${bookCart.value.num }</td>
		<td>${bookCart.value.totalPrice }</td>
		<td></td>
	</tr>
</c:forEach>
</table>
</body>
</html>