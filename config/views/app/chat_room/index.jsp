<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />
<body id="chat_body">
<script>
$(function(){
	
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_chat_room').datagrid('reload');
		}
	}, {
		text:'新建',
		iconCls:'icon-add',
		handler:function(){
			var win = $('#win_add').dialog({  
				title: '新建资源',  
				href: '${WEB_PATH }/app/chat_room/view/add.do'
			});
			win.dialog('open');
		}
	}, {
		text:'添加公告',
		iconCls:'icon-add',
	handler:function(){
		var row = $('#grid_chat_room').datagrid('getSelected');
		if (row == null) {
			$.messager.alert('温馨提示', '请先选择一条记录！');
			return false;
		}
		var win = $('#win_addNotice').dialog({  
			title: '添加公告',  
			href: '${WEB_PATH }/app/chat_room/view/addNotice.do?chat_roomId=' + row.id
		});
		win.dialog('open');
	}
	}, {
		text:'禁用',
		iconCls:'icon-remove',
		handler:function(){
			var row = $('#grid_chat_room').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if (row.status == 3) {
				$.messager.alert('温馨提示', '该聊天室已是禁用状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认禁用当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/chat_room/forbidden.do?chat_roomId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_chat_room').datagrid('reload');
								showMsg(data.msg, 'success');
							}
						},
						error: function(result) {
							showErrorMsg(result);
						}
					});
				}
			});
		}
	}, {
		text:'启用',
		iconCls:'icon-start',
		handler:function(){
			var row = $('#grid_chat_room').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if (row.status != 3) {
				$.messager.alert('温馨提示', '该聊天室已是启用状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认启用当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/chat_room/release.do?chat_roomId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_chat_room').datagrid('reload');
								showMsg(data.msg, 'success');
							}
						},
						error: function(result) {
							showErrorMsg(result);
						}
					});
				}
			});
		}
	}, {
		text:'用户',
		iconCls:'icon-005',
		handler:function(){
			var row = $('#grid_chat_room').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			var win = $('#win_user').dialog({
				title: '用户管理',
				href: '${WEB_PATH }/app/chat_room/toChatUserIndex.do?room_id=' + row.room_id
			});
			win.dialog('open');
		}
	}];
	
	
	$('#grid_chat_room').datagrid({
		toolbar: toolbar,
		onDblClickRow: function(rowIndex, data){
			var win = $('#win_edit').dialog({  
				title: '修改资源',  
				cache: false,  
				href: '${WEB_PATH }/app/chat_room/view/edit.do?chat_roomId=' + data.id,  
				modal: true  
			});
			win.dialog('open');
		},
		onLoadError: function(data){
			showErrorMsg(data);
		}
	});
	
});
</script>
<script>
function formatStatus(val,row){
	if(val == '3'){
		return '<span style="color:red">禁用</span>';
	}
	return '正常';
}
</script>
	<table id="grid_chat_room" fit="true" border="false"
	 pagination="true" rownumbers="true"  fitColumns="true"
	collapsible="false" idField="id" treeField="text"
	url="${WEB_PATH }/app/chat_room/getPage.do" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }">
	<thead>
		<tr>
			<th field="id" width="120" hidden="true">id</th>
			<th field="room_id" width="120" hidden="true">room_id</th>
			<th field="room_name" width="120">聊天室名字</th>
			<th field="created_at" width="120" formatter="formatTime">创建时间</th>
			<th field="description" width="120">聊天室描述</th>
			<th field="notice" width="120">聊天室公告</th>
			<th field="status" width="120" formatter="formatStatus">状态</th>
		</tr>
	</thead>
</table>



<div id="win_add" style="width: 600px; height: 400px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_addNotice" style="width: 700px; height: 400px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_user" style="width: 1000px; height: 400px; padding: 5px;"
data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 600px; height: 400px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
</body>