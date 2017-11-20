<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	/**
	 *  如果用户禁用了cookie，那么通过cookie获得sessionId的方法则将行不通，只能通过Url重写，使每个访问
	 *  服务端应用的超链接都带上sessionId这个参数，而默认的在页面第一次刷新的时候都会带上sessionId。如果
	 *  客户端没有禁用cookie。那么第二次访问应用，则不会在url后带sessionId，如果用户禁用的话，那么sessionId，
	 *  则会一直保留在超连接后面
	 *  如果在本机测试，请使用127.0.0.1去访问应用，localhost浏览器即使设置阻止cookie，也没用。
	 */
	String buyUrl = request.getContextPath() + "/sessionCookieBuy.do";
	// url重写带上sessionId
	buyUrl = response.encodeURL(buyUrl);
	String payUrl = request.getContextPath() + "/sessionCookiePay.do";
	// url重写带上sessionId
	payUrl = response.encodeURL(payUrl);
%>

<body>
	<a href = "<%=buyUrl%>" target="_blank">购买</a> <br />
	<a href = "<%=payUrl%>" target="_blank">结账</a> <br />
</body>
</html>