<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.lj.taosserver.constant.AuthorityConstant"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <%  String webContext = request.getContextPath();  %>  
 <link rel="stylesheet" type="text/css"  href="<%=webContext %>/js/easyui-1.4.2/themes/default/easyui.css"> 
 <link rel="stylesheet" type="text/css"  href="<%=webContext %>/js/easyui-1.4.2/themes/icon.css"> 
 <script type="text/javascript"  src="<%=webContext %>/js/easyui-1.4.2/jquery.min.js"></script> 
 <script type="text/javascript"  src="<%=webContext %>/js/easyui-1.4.2/jquery.easyui.min.js"></script>  
 <script type="text/javascript"  src="<%=webContext %>/js/easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
 
 <script type="text/javascript">
 	
 </script>
 <style>
 </style>
<title>店铺编辑</title>
</head>

<body>  

<script type="text/javascript">
 	$(function(){
 		$('#ff').form({
 		    url:'<%=webContext %>/auth/usersave',
 		    onSubmit: function(){
 		        // do some check
 		        // return false to prevent submit;
 		    },
 		    success:function(data){
 		        //alert(data)
 		        if(data=="ok"){
 		        	//alert("保存成功");
 		        	$('#dd').dialog('close');
 		        	$('#dg').datagrid("reload");
 		        }
 		        else{
 		        	alert("保存失败："+data);
 		        }
 		    }
 		});
 		// submit the form
 		//$('#ff').submit();
 	});
 	/* function submitForm(){
 		//alert("submit");
 		$('#ff').submit();
 	}
 	function cancelForm(){
 		$('#dd').dialog('close');
 	} */
 </script>
<style type="text/css">
	td{
		text-align: right;
	}
	label{
	 	width:100px;
	 	margin-right: 10px;
	 }
	 input,select{
	 	width:200px;
	 	float:left;
	 }
</style> 
<form:form id="ff" method="post" commandName="userModel">
	<form:input type="hidden" path="id" />
	<table>
    <tr>
        <td><label for="userName">用户名:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="userName" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="passWord">密码:</label></td>
        <td><form:input class="easyui-validatebox" type="password" path="passWord"/></td>
    </tr>
    <tr>
        <td><label for="auths">权限:</label></td>
        <td>
	        <form:select path="auths">
	        	<c:forEach items="${auths}" var="auth">
	        		<form:option value="${auth.code}">${auth.name }</form:option>
	        	</c:forEach>
	        	<%-- <form:option value="false">关闭</form:option> --%>
	        </form:select>
	    </td>
    </tr>
    <tr>
    	<!-- <td><input type="button" value="取消" onclick="cancelForm()"/></td>
    	<td><input type="button" value="保存" onclick="submitForm()"/></td> -->
    </tr>
    </table>
</form:form>


<form:form action="" method="post">


</form:form>

</body>

</html>