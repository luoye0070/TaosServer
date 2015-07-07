<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form action="../j_spring_security_check" method="post">
		<div> 
			用户名：
			<input type="text" name="j_username" value=""/>
		</div>
		<div>
			密码：
			<input type="password" name="j_password" value=""/>
		</div>
		<div>
			<input type="submit" value="登录"/>
		</div>
	</form>
</body>
</html>