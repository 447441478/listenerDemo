<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>在线用户管理</title>
		<style type="text/css">
			table {
				border: 1px solid;
				border-collapse: collapse;	
				width: 80%;
				margin: 0px auto;	
			}
			tr:first-child{
				font-weight: bold;
			}
			td {
				border: 1px solid;
				padding: 5px;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<c:if test="${!empty onlineInfos}">
			<table>
				<tr>
					<td>用户名</td>
					<td>第一次访问时间</td>
					<td>上一次访问时间</td>
					<td>Ip</td>
					<td>操作</td>
				</tr>	
				<c:forEach items="${onlineInfos}" var="onlineInfo">	
					<tr>
						<td>
							<c:if test="${empty onlineInfo.user}">游客</c:if>
							<c:if test="${!empty onlineInfo.user}">${onlineInfo.user.name}</c:if>
						</td>
						<td><fmt:formatDate value="${onlineInfo.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${onlineInfo.AccessedDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${onlineInfo.IP}</td>
						<td>
							<c:if test="${!empty onlineInfo.user && sessionScope.user.admin}">
								<a href="<c:url value='/servlet/KickOutServlet?sessionId=${onlineInfo.sessionId}'/>">踢出</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>		
			</table>
		</c:if>
		
	</body>
</html>