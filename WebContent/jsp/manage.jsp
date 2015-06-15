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
 	/**
	 * 创建新选项卡
	 * @param tabId    选项卡id
	 * @param title    选项卡标题
	 * @param url      选项卡远程调用路径
	 */
	function addTab(tabId,title,url){
 		if($('#centerTab').tabs('exists',title)){
 			$('#centerTab').tabs('select',title);
 		}else{//如果当前id的tab不存在则创建一个tab
			var name = 'iframe_'+tabId;
			$('#centerTab').tabs('add',{
				title: title,         
				closable:true,
				cache : false,
				selected:true,
			    tools:[{
			        iconCls:'icon-mini-refresh',
			        handler:function(){
			            //alert('refresh');
			            $("#"+tabId)[0].contentWindow.location.reload(true);
			        }
			    }],
				//注：使用iframe即可防止同一个页面出现js和css冲突的问题
				content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>'
			});
		}
	}
 
 	$(function(){
 		$(".easyui-tree").tree({
 			onSelect:function(node){
 				//alert(node.text);
 				addTab($(node.text).attr("tabId"),$(node.text).attr("title"),$(node.text).attr("url"));
 			}
 		});
 	});
 	
 </script>
 
 <style>
 	.tree-node{
 		padding:5px 0px;
 	}
 </style>
<title>测试</title>
</head>
<%-- <body class="easyui-layout">

Hello,I am a Model test.这是一个模型测试。

<form:form action="" method="post"
				commandName="restaurantModel">
	<form:input type="text" path="name"/>
	<form:input type="text" path="updateTime"/>
	<form:errors path="updateTime"/>
	<input type="submit" value="提交"/>
</form:form>

</body> --%>

<body class="easyui-layout">  
    <!-- 正上方panel -->  
    <%-- <div region="north" style="height:100px;padding:10px;" href="<%=webContext%>/Web/common/page/top.html"></div>   --%>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 疯狂秀才 
        	<a href="#" id="editpass">修改密码</a> 
        	<a href="#" id="loginOut">安全退出</a>
        </span>
        <span style="padding-left:10px; font-size: 16px; ">
        <!-- <img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> --> 
        XX管理系统
		</span>
    </div>
    <!-- 正左边panel -->  
    <div region="west" title="菜单栏" split="true" style="width:280px;padding1:1px;overflow:hidden;">  
        <div class="easyui-accordion" fit="false" border="false">  
            <!-- selected -->  
            <div title="饭店管理" selected="true">  
            <ul class="easyui-tree" style="margin:10px 0px;">  
                <li><a href="#" tabId='tabId_shopInfo' title='饭店信息' url='<%=webContext%>/index.html'>饭店信息</a></li> 
            </ul>  
            </div>
            <div title="日报管理" selected="true">  
            <ul class="easyui-tree" style="margin:10px 0px;">  
                <li><a href="#" tabId='tabId_dailyReports' title='日报管理' url='<%=webContext%>/index.html'>日报管理</a></li> 
            </ul>  
            </div>   
        </div>  
    </div>  
    <!-- 正中间panel -->  
    <div region="center" title="功能区" >  
        <div class="easyui-tabs" id="centerTab" fit="true" border="false">  
            <div title="欢迎页" style="padding:20px;overflow:hidden;">   
                <div style="margin-top:20px;">  
                    <h3>你好，欢迎来到管理系统</h3>  
                </div>  
            </div>  
        </div>  
    </div>  
    <!-- 正下方panel -->  
    <div region="south" style="height:50px;" align="center">  
        <label>  
           <br/>  
        </label>  
    </div>  
</body>  

</html>