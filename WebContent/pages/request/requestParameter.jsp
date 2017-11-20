<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/requestCharset.do" method="post">
		用户名: <input type = "text" name = "userName" /> <br />
		密&nbsp;码 <input type = "password" name = "password" /> <br/>
		确认密码: <input type = "password" name = "password1" /> <br />
		性别: <input type = "radio" name = "sex" value = "男" />男
		 	 <input type = "radio" name= "sex" value = "女" /> 女 <br />	
		爱好: <input type = "checkbox" name = "hobby" value = "看书"> 看书<br />	
		<input type = "checkbox" name = "hobby" value = "看电影"> 看电影<br />	
		<input type = "checkbox" name = "hobby" value = "旅游"> 旅游<br />	
		<input type = "checkbox" name = "hobby" value = "运动"> 运动<br />	
		<input type = "submit"> 
	</form>
</body>
</html>