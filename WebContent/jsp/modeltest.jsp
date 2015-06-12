<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>

Hello,I am a Model test.这是一个模型测试。

<form:form action="" method="post"
				commandName="restaurantModel">
	<form:input type="text" path="name"/>
	<form:input type="text" path="updateTime"/>
	<form:errors path="updateTime"/>
	<input type="submit" value="提交"/>
</form:form>

</body>
</html>