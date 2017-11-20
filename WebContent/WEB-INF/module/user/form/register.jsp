<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script type="text/javascript">
	function changeCode(pic) {
		pic.src = pic.src + '?' + new Date().getTime();
	}
</script>

<style type="text/css">
	.input {
		line-height: 30px;
		margin-bottom: 10px;
	}
</style>

</head>
<body>
<!-- 包含主页 -->
<%@include file="/WEB-INF/module/user/public/header.jsp" %>


<div style="text-align:left;padding:100px 0 0 200px;">
	<!-- POST数据如何加密？ -->
	<form action="${pageContext.request.contextPath }/userRegisterServlet.do" method="post">
		<!-- 服务器传送给客户端表单的令牌 -->
		<input type="hidden" name="token" value=${sessionScope.token }>
		用户名: <input type="text" name="username" value=${userForm.username } >
			<span style="color:red">${userForm.errors.username }</span> <br/><br/>
		密码:<input type="password" name="password" value=${userForm.password }>
			<span style="color:red">${userForm.errors.password}</span><br/><br/>
		确认密码: <input type="password" name="password2" value=${userForm.password2 } >
			<span style="color:red">${userForm.errors.password2}</span> <br/><br/>
		昵称: <input type="text" name="nickname" value=${userForm.nickname }>
			<span style="color:red">${userForm.errors.nickname}</span><br/><br/>
		校验码: <input type="text" name="code" /> <img alt="验证码" 
			src="${pageContext.request.contextPath}/codeGenerateServlet.do" id="codePic" onclick="changeCode(this)">
			<span style="color:red">${userForm.errors.code}</span><br/><br/>
		<input type="submit" value="注册"><br/>
	</form>
</div>
</body>
</html>