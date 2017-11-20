<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>include一个界面</title>
</head>
<body>
<%@include file="/pages/public/header.jsp" %> <br />

网站正文部分： <br />
	1. 这是一个包含界面的例子 <br/><br/>
	
<%@include file="/pages/public/foot.jsp" %> <br />
</body>
</html>