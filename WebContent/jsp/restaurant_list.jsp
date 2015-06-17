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
 	$(function(){
 		
 	});
 	
 	$(function(){
        var pager = $('#dg').datagrid().datagrid('getPager');    // get the pager of datagrid
        pager.pagination({
        	
        });            
    });
 </script>
 
 	<script type="text/javascript">
        var toolbar = [{
            text:'Edit',
            iconCls:'icon-edit',
            handler:function(){
            	var selectedRow=$('#dg').datagrid("getSelected");
            	if(selectedRow==null){
            		alert("请选择要编辑的行");
            	}
            	
            	$('#dd').dialog('open');
            	$('#dd').dialog('refresh', '<%=webContext%>/restaurant-manage/edit?rId='+selectedRow.id);
            	
            	alert('edit->'+selectedRow.id)
            	
            }
        }];
    </script>
 <style>
 </style>
<title>测试</title>
</head>

<body>  
	<table id="dg" class="easyui-datagrid" data-options="rownumbers:true,pagination:true,singleSelect:true,toolbar:toolbar,url:'<%=webContext+"/restaurant-manage/list"%>'">
	    <thead>
	        <tr>
	        	<th data-options="field:'id'">店铺编号</th>
	            <th data-options="field:'name'">店铺名称</th>
	            <th data-options="field:'address'">店铺地址</th>
	            <th data-options="field:'phone'">联系电话</th>
	            <th data-options="field:'enabled'">店铺是否有效</th>
	            <th data-options="field:'cuisineName'">所属菜系</th>
	            <th data-options="field:'licenseLastTime'">许可证最后获取时间</th>
	            <th data-options="field:'licenseEnable'">许可证是否有效</th>
	        </tr>
	    </thead>
	    <tbody>
	        <tr>
	            <td>001</td><td>name1</td><td>2323</td>
	        </tr>
	        <tr>
	            <td>002</td><td>name2</td><td>4612</td>
	        </tr>
	    </tbody>
	</table>

<div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    Dialog Content.
</div>
	<!-- <div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
	        data-options="total:2000,pageSize:10">
	</div> -->
</body>

</html>