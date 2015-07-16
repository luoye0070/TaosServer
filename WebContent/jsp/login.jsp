<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<%
	String webContext = request.getContextPath();
%>
<link rel="stylesheet" type="text/css"
	href="<%=webContext%>/js/easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=webContext%>/js/easyui-1.4.2/themes/icon.css">
<script type="text/javascript"
	src="<%=webContext%>/js/easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=webContext%>/js/easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=webContext%>/js/easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

	<div style="margin: 0px auto; width: 400px;">
		<div class="easyui-panel" title="用户登录" style="width: 400px">
			<div style="padding: 10px 60px 20px 60px">
				<c:url var="loginUrl" value="/login"/>
				<form:form action="${loginUrl}" method="post">
					<table cellpadding="5">
						<c:if test="${error}">
						<tr>
							<td colspan="2" style="color:red;text-align:center">用户名或密码错误，请重新登录！</td>
						</tr>
						</c:if>
						<tr>
							<td>用户名:</td>
							<td><input class="easyui-textbox" type="text"
								name="username" data-options="required:true"></input></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input class="easyui-textbox" type="password"
								name="password" data-options="required:true"></input></td>
						</tr>
					</table>
					<div style="text-align: center; padding: 5px">
						<input class="easyui-linkbutton" style="padding:5px;" type="submit" value="登录" />
					</div>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>