<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function reload() {
		window.location.reload();
	}
</script>
</head>
<body onload="reload">
<!-- 购买商品回退后需要刷新界面才能展示浏览的最后一条记录 -->
以下是所有的商品列表 <br />
	<c:forEach items="${goodsList}" var="goods" >
		<a href = "${pageContext.request.contextPath}/goodsDetail.do?id=${goods.id}" 
			 />${goods.name} <br />
	</c:forEach>
<br>
以下是已浏览的商品列表	<br />
	<c:forEach items="${goodsHistoryList}" var="goodsHistory">
		${goodsHistory.name} <br />
	</c:forEach>
</body>
</html>