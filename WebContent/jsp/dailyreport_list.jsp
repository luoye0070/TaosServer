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
 <script type="text/javascript"  src="<%=webContext %>/js/serializeJson.js"></script>
 
 <script type="text/javascript">
 	$(function(){
 		/* $('#dd').dialog({
 			buttons:[{
				text:'保存',
				handler:function(){
					$('#ff').submit();
				}
			},{
				text:'取消',
				handler:function(){
					$('#dd').dialog('close');
				}
			}]
 		}); */
 	});
 	
 	$(function(){
        var pager = $('#dg').datagrid().datagrid('getPager');    // get the pager of datagrid
        pager.pagination({
        	
        });            
    });
 </script>
 
 	<script type="text/javascript">
 		var toolbar = [];
        <%-- var toolbar = [{
            text:'编辑',
            iconCls:'icon-edit',
            handler:function(){
            	var selectedRow=$('#dg').datagrid("getSelected");
            	if(selectedRow==null){
            		alert("请选择要编辑的行");
            		return;
            	}
            	
            	$('#dd').dialog('open');
            	$('#dd').dialog('refresh', '<%=webContext%>/restaurant-manage/edit?rId='+selectedRow.id);
            	
            	//alert('edit->'+selectedRow.id)
            	
            }
        }]; --%>
    </script>
    <script type="text/javascript">
    	function searchReport(){
    		//alert("searcj");
    		/* $('#dg').datagrid('load',{
    			beginDate: $("#beginDate").val(),
    			endDate: $("#endDate").val(),
    			restaurant:$("#restaurant").val()
    		}); */
    		$("#dg").datagrid('load',$("#searchForm").serializeJson());
    	}
    </script>
 <style>
 </style>
<title>测试</title>
</head>

<body>  
	<table id="dg" class="easyui-datagrid" data-options="rownumbers:true,pagination:true,singleSelect:true,toolbar:'#tb',url:'<%=webContext+"/dailyreport-manage/list"%>'">
	    <thead>
	        <tr>
	        	<th data-options="field:'id'">编号</th>
	            <th data-options="field:'restaurantName'">店铺名称</th>
	            <th data-options="field:'date'">日期</th>
	            <th data-options="field:'totalCount'">总订单数</th>
	            <th data-options="field:'availableCount'">有效订单数</th>
	            <th data-options="field:'completeCount'">最终完成订单数</th>
	        </tr>
	    </thead>
	    <tbody>
	        <!-- <tr>
	            <td>001</td><td>name1</td><td>2323</td>
	        </tr>
	        <tr>
	            <td>002</td><td>name2</td><td>4612</td>
	        </tr> -->
	    </tbody>
	</table>
	<div id="tb" style="padding:2px 5px;">
		<form id="searchForm">
        	日期起: <input class="easyui-datebox" style="width:110px" name="beginDate"/>&nbsp;&nbsp; 
    		日期止: <input class="easyui-datebox" style="width:110px" name="endDate"/>&nbsp;&nbsp;   
        	饭店: 
	        <select class="easyui-combobox" panelHeight="auto" style="width:100px" name="restaurant">
	            <option value="1">Java</option>
	            <option value="2">C</option>
	            <option value="3">Basic</option>
	            <option value="4">Perl</option>
	            <option value="5">Python</option>
	        </select>&nbsp;&nbsp;  
	        <a href="#" class="easyui-linkbutton" onclick="searchReport()" iconCls="icon-search">查找</a>
        </form>
    </div>
    
<!-- <div id="dd" class="easyui-dialog" title="编辑饭店信息" style="width:455px;height:405px;"
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    Dialog Content.
</div> -->
	<!-- <div id="pp" class="easyui-pagination" style="background:#efefef;border:1px solid #ccc;"
	        data-options="total:2000,pageSize:10">
	</div> -->
</body>

</html>