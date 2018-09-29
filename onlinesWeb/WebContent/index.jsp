<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>演示管理在线用户，管理员踢出登录用户，安全退出</title>
	</head>
	<body>
		
		<!-- 当用户没有登录的时候 显示登录表单 -->
		<c:if test="${empty sessionScope.user }" var="boo">
			<form action="<c:url value='/LoginServlet'/>" method="post">
				用户名：<input type="text" name="name" /><br/>
				密&emsp;码：<input type="password" name="pwd" /><br/>
				<input type="submit" value="用户名和密码一致即可登录"/>		           
			</form>
		</c:if>
		<!-- 当用户登录成功显示登录后的信息 -->
		<c:if test="${!boo}">
			欢迎您,&emsp;<c:if test="${sessionScope.user.admin}">管理员&emsp;</c:if>${sessionScope.user.name}
			<br/>
			<a href="<c:url value='/servlet/ShowOnlinesServlet'/>">查看在线用户</a> <br/>
			<a href="<c:url value='/servlet/LoginOutServlet'/>">安全退出</a>
		</c:if>
		<hr/>
		<a href="<c:url value='/news.jsp'/>">观看新闻</a>
	</body>
</html>