<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 用户登录 -->
	<form action="${pageContext.request.contextPath}/sessionUserLogin.do" method="post">
		用户名:<input type="text" name="username" style="margin-bottom:3px;"/> <br />
		密&nbsp;码:<input type="password" name="password"> <br />
		校验码: <input type="text" name="token" /> <img alt="验证码" 
			src="${pageContext.request.contextPath}/responseRandomPic.do"><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>