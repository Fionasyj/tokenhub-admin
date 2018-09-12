<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	
	var row = $('#grid_role').datagrid('getSelected');
	
	$('#tree_menu').tree({
		checkbox: true,
		cascadeCheck: false,
		url: '${WEB_PATH }/system/role/getRoleMenuCheckList.do?roleId=' + row.id,
		onLoadSuccess:function(node, data){
			$(this).tree('expandAll');
		}
	});
	
	$('#btn_set').click(function(){
		$.messager.progress();
		
		var nodes = $('#tree_menu').tree('getChecked');
		var length = nodes.length;
		var menuIds = '';
		for(var i=0; i < length; i++){
			if (menuIds != '') {
				menuIds += ',';
			}
			menuIds += nodes[i].id;
		}
		
		$.ajax({
			url : '${WEB_PATH }/system/role/set.do',
			cache : false,
			type : 'POST',
			data: {roleId: row.id, menuIds: menuIds},
			success : function(result) {
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_set').dialog('close');
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
					<ul id="tree_menu"></ul>
				</td>
			</tr>
		</table>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_set" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">授权</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_set').window('close');">关闭</a>
	</div>
</div>