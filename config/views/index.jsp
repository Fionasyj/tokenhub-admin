<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>TokenHub后台管理系统</title>
<link rel="shortcut icon" href="${WEB_PATH }/favicon.ico">
<style type="text/css">
</style>
<c:import url="${WEB_PATH }/import.jsp" />
<script>
		$(function(){
			
		});
		
		function click_node(node){
			var APP_tabs = $('#APP_tabs');
			if(node.attributes.url == '#' || node.attributes.url == ''){
				return;
			}

			if(APP_tabs.tabs('exists', node.text)){
				APP_tabs.tabs('select', node.text);
				return;
			}
			
			var content = '<iframe scrolling="yes" frameborder="0"  border="0" src="${WEB_PATH }' + node.attributes.url + '"  height="100%" width="100%"></iframe>';
			APP_tabs.tabs("add", {title: node.text, content:content, closable:true, iconCls: node.iconCls});
		}
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" class="header" style="height:55px;">
		<!--  <span class='project_name' style="float: rigth;">TokenHub后台管理系统</span>-->
		<div class='links' style="padding: 10px;">
			<span class='login'> 欢迎您，${user.name}</span>&nbsp; 
			<a class='editpass' href='javascript:void(0)' onclick='pass()'>修改密码</a>|
			&nbsp; <a class='logout' href='javascript:void(0)' onclick='logout()'>退出</a>|
			&nbsp; <a class='help' href='javascript:void(0)' onclick='help()'>帮助</a>
		</div>
	</div>
	<div data-options="region:'east', split:true" style="width: 5px;">
	</div>

	<div data-options="region:'west',split:true" title="我的菜单"
		style="width: 180px; padding: 0px; overflow: hidden;">
		<div class="easyui-accordion" data-options="fit:true, border:false">
			<c:forEach var="module" items="${moduleList}">
				<div title="${module.text}" iconCls="${module.iconCls}">
					<ul class="easyui-tree"
						data-options="url:'${WEB_PATH }/getMenuList.do?pid=${module.id}',animate:true,
							onClick:function(node){click_node(node);}"></ul>
				</div>
			</c:forEach>
		</div>
	</div>

	<div data-options="region:'center'">
		<div id="APP_tabs" class="easyui-tabs"
			data-options="fit:true,border:false">
			<div title="首页" href="${WEB_PATH}/main.do"></div>
		</div>
	</div>

	<div id="win_pass" style="width: 320px; height: 240px; padding: 5px;"
		data-options="cache:false,modal:true"></div>

</body>
</html>

<script>
function logout(){
	$.messager.confirm('操作确认', '确定退出系统？', function(btn) {
		if (btn) {
			window.location.href = "${WEB_PATH }/system/user/logout.do";
		}
	});
}


function help(){
	var html = '';
	html += '技术支持： achuanok@gmail.com';
	$.messager.alert('帮助', html);
}

function pass(){
	var win = $('#win_pass').dialog({  
		title: '修改密码',  
		href: '${WEB_PATH }/system/user/view/pass.do'
	});
	win.dialog('open');
}
</script>

