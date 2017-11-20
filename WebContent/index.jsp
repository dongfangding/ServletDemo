<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Welcome! ${user.username}
	
	<table  align="center" border="1" bordercolor="red" cellspacing="1" cellpadding="1" width="700">
		<tr style="font-weight: bolder;line-height:40px;">
			<td>用户登陆、注册、商城演示</td>
			<td><a href = "${pageContext.request.contextPath}/userIndexJspServlet.do">用户</a></td>
		</tr>
	</table>
</body>
</html>