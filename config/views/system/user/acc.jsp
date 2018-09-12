<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	
	var row = $('#grid_user').datagrid('getSelected');
	
	$('#tree_role').tree({
		checkbox: true,
		cascadeCheck: false,
		url: '${WEB_PATH }/system/user/getUserRoleCheckList.do?userId=' + row.id
	});
	
	$('#btn_acc').click(function(){
		$.messager.progress();
		
		var nodes = $('#tree_role').tree('getChecked');
		var length = nodes.length;
		var roleIds = '';
		var roleNames = '';
		for(var i=0; i < length; i++){
			if (roleIds != '') {
				roleIds += ',';
				roleNames += ',';
			}
			roleIds += nodes[i].id;
			roleNames += nodes[i].text;
		}
		
		$.ajax({
			url : '${WEB_PATH }/system/user/acc.do',
			cache : false,
			type : 'POST',
			data: {userId: row.id, roleIds: roleIds, roleNames: roleNames},
			success : function(result) {
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#grid_user').datagrid('reload');
					$('#win_acc').dialog('close');
					showMsg(data.msg, 'success');
				}
			},
			error: function(result) {
				showErrorMsg(result);
			}
		});
	});
	
});
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="centerdiv">
		<table class="centertable">
			<tr>
				<td width="10%" align="right"></td>
				<td>
					<ul id="tree_role"></ul>
				</td>
			</tr>
		</table>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_acc" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">授权</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_acc').window('close');">关闭</a>
	</div>
</div>