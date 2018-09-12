<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />

<script>
$(function(){
	
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_user').datagrid('reload');
		}
	}, {
		text:'禁用',
		iconCls:'icon-remove',
		handler:function(){
			var row = $('#grid_user').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if (row.status == 3) {
				$.messager.alert('温馨提示', '该用户已是禁用状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认禁用当前用户？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/appuser/forbidden.do?userId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_user').datagrid('reload');
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
		text:'解禁',
		iconCls:'icon-release',
		handler:function(){
			var row = $('#grid_user').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if (row.status != 3) {
				$.messager.alert('温馨提示', '该用户已是启用状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认解禁当前用户？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/appuser/release.do?userId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_user').datagrid('reload');
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
	}];
	
	//用户查询
	$('#btn_query').click(function(){
		var nick = $('#nick').val();
		var mobile = $('#mobile').combotree('getValue');
		$('#grid_user').datagrid('load', {nick: nick, mobile: mobile});
		$('#win_query').window('close');
	});
		
		
	
	$('#grid_user').datagrid({
		toolbar: toolbar,
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

<table id="grid_user" url="${WEB_PATH }/app/appuser/getPage.do"
	pagination="true" fit="true" border="false" fitColumns="true"
	rownumbers="true" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }">
	<thead>
		<tr>
			<th field="id" width="120" hidden="true">id</th>
			<th field="nick" width="120">昵称</th>
			<th field="mobile" width="120">手机</th>
			<th field="openid" width="120">第三方平台</th>
			<th field="email" width="120">Email</th>
			<th field="created_at" width="120" formatter="formatTime">创建日期</th>
			<th field="status" width="120" formatter="formatStatus">状态</th>
		</tr>
	</thead>
</table>
<div id="win_add" style="width: 450px; height: 340px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 450px; height: 340px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_acc" style="width: 280px; height: 340px; padding: 5px;"
	data-options="cache:false,modal:true"></div>

