<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<form action="${pageContext.request.contextPath }/userIconUploadServlet.do" method = "post" enctype="multipart/form-data">
		选择头像: <input type = "file" name = "file" /> <br />
		<input type = "submit" value = "upload">
	</form>
</center>
</body>
</html>