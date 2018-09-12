<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_chat_user').datagrid('reload');
		}
	}, {
		text:'筛选 :   <select id="filter" name="typeId" style="width:100px;" data-options="required:true"><option value="0">所有用户</option><option value="3">禁言用户</option></select>',
		iconCls:'icon-filter',
		handler:function(){
			$('#filter').on('change',function(){
				var filter = $('#filter').val();
					$('#grid_chat_user').datagrid('load', {status: filter});
			});
		}
	},{
		text:'禁言',
		iconCls:'icon-cancel',
		handler:function(){
			var row = $('#grid_chat_user').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if(row.status == 3){
				$.messager.alert('温馨提示', '该用户已是禁言状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认禁言当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/chat_user/forbid.do?userId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_chat_user').datagrid('reload');
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
	},{
		text:'解禁',
		iconCls:'icon-release',
		handler:function(){
			var row = $('#grid_chat_user').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if(row.status != 3){
				$.messager.alert('温馨提示', '该用户已是解禁状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认解禁当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/chat_user/release.do?userId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_chat_user').datagrid('reload');
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
		text:'查询',
		iconCls:'icon-query',
		handler:function(){
			$('#win_query').removeAttr('hidden');
			var win = $('#win_query').dialog({  
				title: '用户查询'
			});
			win.dialog('open');
		}
	}
	];
	
	$('#grid_chat_user').datagrid({
		toolbar: toolbar,
		onLoadError: function(data){
			showErrorMsg(data);
		}
	});
	//用户查询
	$('#btn_query').click(function(){
		var q_nick = $('#q_nick').val();
		$('#grid_chat_user').datagrid('load', {nick: q_nick});
		$('#win_query').window('close');
	});
});
</script>

<script>
function formatStatus(val,row){
	if(val == '3'){
		return '<span style="color:red">禁言</span>';
	}
	return '正常';
}
</script>
<table id="grid_chat_user" url="${WEB_PATH }/app/chat_user/getPage.do?chat_roomId=${chat_room}"
	pagination="true" fit="true" border="false" fitColumns="true"
	rownumbers="true" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }">
	<thead>
		<tr>
			<th field="id" width="120" hidden="true">id</th>
			<th field="user_id" width="120" hidden="true">id</th>
			<th field="room_name" width="120">所在房间</th>
			<th field="nick" width="120">用户昵称</th>
			<th field="created_at" width="120">创建时间</th>
			<th field="status" width="120" formatter="formatStatus">状态</th>
		</tr>
	</thead>
</table>
<div hidden="hidden" id="win_query" style="width: 340px; height: 200px; padding: 5px;"
	data-options="cache:false,modal:true">
	<div region="center" border="false" class="centerdiv"
		style="height: 90px">
		<table class="centertable">
			<tr>
				<td width="30%" align="right">昵称：</td>
				<td><input class="easyui-validatebox" type="text"
					id="q_nick" name="q_nick" /></td>
			</tr>
		</table>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_query" class="easyui-linkbutton l-btn" iconCls="icon-ok"
			href="javascript:void(0)">查询</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_query').window('close');">关闭</a>
	</div>
</div>
