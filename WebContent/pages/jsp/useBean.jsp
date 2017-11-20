<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="training.jsp.Person" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 创建实例化Person对象 -->
	<jsp:useBean id="person" class="training.jsp.Person" scope="session">这里只会第一次会出现</jsp:useBean>
	<br/>
	<!-- 使用java代码给person赋值 -->
	<%
		person.setUsername("ddf");
		person.setAge(11);
		person.setPassword("123456");
	%>
	username:<%=person.getUsername() %>
	<br/>
	<!-- 使用标签给person对象赋值 -->
	<jsp:setProperty property="username" name="person" value="ddf1"/>
	<!-- 使用标签给person的username取值 -->
	username:<jsp:getProperty property="username" name="person" /><br/>
	
	<!-- 使用标签通过访问的url给person对象的username赋值 -->
	<!-- /jsp/useBean.jsp?username=ddf2&password=234567 -->
	<jsp:setProperty property="username" name="person" param="username"/>
	<jsp:setProperty property="password" name="person" param="password"/>
	<!-- 使用标签给person的username取值 -->
	username:<jsp:getProperty property="username" name="person" /><br/>
	password:<jsp:getProperty property="password" name="person" /><br/>
	
	<!-- 上面的如果确定可以避免某些特殊类型不会存在类型转换错误，可以用直接使用*来匹配所有的来赋值 -->
	<jsp:setProperty property="*" name="person"/>
	username:<jsp:getProperty property="username" name="person" /><br/>
	password:<jsp:getProperty property="password" name="person" /><br/>
	
</body>
</html>