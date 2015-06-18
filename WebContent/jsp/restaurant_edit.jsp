<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
 		    url:'<%=webContext %>/restaurant-manage/save',
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
<form:form id="ff" method="post" commandName="restaurantModel">
	<form:input type="hidden" path="id" />
	<table>
    <tr>
        <td><label for="name">名称:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="name" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">地址:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="address" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">联系电话:</label></td>
        <td><form:input class="easyui-numberbox" type="text" path="phone" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">经度:</label></td>
        <td><form:input class="easyui-numberbox" type="text" path="longitude" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">维度:</label></td>
        <td><form:input class="easyui-numberbox" type="text" path="latitude" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">营业时间起:</label></td>
        <td><form:input class="easyui-datetimebox" type="text" path="shopHoursBeginTime" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">营业时间止:</label></td>
        <td><form:input class="easyui-datetimebox" type="text" path="shopHoursEndTime" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">开启关闭状态:</label></td>
        <td>
	        <form:select path="enabled">
	        	<form:option value="true">开启</form:option>
	        	<form:option value="false">关闭</form:option>
	        </form:select>
	    </td>
    </tr>
    <tr>
        <td><label for="email">菜系:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="cuisineName" /></td>
    </tr>
    <tr>
        <td><label for="email">人均消费水平:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="averageConsume" /></td>
    </tr>
    <tr>
        <td><label for="email">简单描述:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="description" /></td>
    </tr>
    <tr>
        <td><label for="email">店铺基础url地址:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="baseUrl" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">订单间隔时间（分钟）:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="intervalTime" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">最后信息更新时间:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="updateTime" readonly="true" /></td>
    </tr>
    <tr>
        <td><label for="email">店铺IP:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="ip" data-options="required:true" /></td>
    </tr>
    <form:input type="hidden" path="licenseModel.id" />
    <tr>
        <td>许可证信息</td>
        <td></td>
    </tr>
    <tr>
        <td><label for="email">序列化字符串:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="licenseModel.licenseSerial" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">是否有效:</label></td>
        <td>
        <form:select path="licenseModel.enable">
        	<form:option value="true">有效</form:option>
        	<form:option value="false">无效</form:option>
        </form:select>
        </td>
    </tr>
    <tr>
        <td><label for="email">有效期时间(天):</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="licenseModel.expire" data-options="required:true" /></td>
    </tr>
    <tr>
        <td><label for="email">创建时间:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="licenseModel.createTime" readonly="true" /></td>
    </tr>
    <tr>
        <td><label for="email">最后访问时间:</label></td>
        <td><form:input class="easyui-validatebox" type="text" path="licenseModel.lastTime" readonly="true" /></td>
    </tr>
    <!-- private String name;
	private String address;
	private String phone;
	private double longitude=0.0;//经度
	private double latitude=0.0;//维度
    private Date shopHoursBeginTime;//营业时间起
    private Date shopHoursEndTime;//营业时间止
    private boolean enabled;//开启关闭状态true开启，false关闭
    private String cuisineName;//菜系 
    private double averageConsume;//人均消费水平，单位元
    private String description;//简单描述
    private String baseUrl;//店铺基础url地址
    private int intervalTime;// //订单间隔时间，单位分钟,默认为60分钟
    private Date updateTime;//最后信息更新时间
    private String ip;//店铺IP 
    
    
    protected String licenseSerial;//标示的序列化字符串
	protected boolean enable;//是否有效
	protected int expire;//有效期时间，单位天
	protected Date createTime;//创建时间
	protected Date lastTime;//最后访问时间
    -->
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