<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />

<script>
$(function(){
	
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_dept').treegrid('reload');
			window.setTimeout(expandAll, 500);
		}
	}, {
		text:'新建',
		iconCls:'icon-add',
		handler:function(){
			$('#win_add').dialog({  
				title: '新建部门',  
				href: '${WEB_PATH }/system/dept/view/add.do' 
			}).dialog('open');
		}
	}, {
		text:'删除',
		iconCls:'icon-remove',
		handler:function(){
			var row = $('#grid_dept').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			$.messager.confirm('操作确认', '你确认删除当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/system/dept/remove.do?deptId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_dept').treegrid('reload');
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
	},'-', {
		id: 'tbar',
		text:'展开',
		iconCls:'icon-017',
		handler:function(){
			$('#grid_dept').treegrid('expandAll');
		}
	}, {
		text:'关闭',
		iconCls:'icon-018',
		handler:function(){
			$('#grid_dept').treegrid('collapseAll');
		}
	}];
	
	$('#grid_dept').treegrid({
		toolbar: toolbar,
		onDblClickRow: function(data){
			var win = $('#win_edit').dialog({  
				title: '修改部门',  
				href: '${WEB_PATH }/system/dept/view/edit.do?deptId=' + data.id
			});
			win.dialog('open');
		},
		onLoadError: function(data){
			showErrorMsg(data);
		}
	});
	
	//自动展开
	function expandAll(){
		$('#grid_dept').treegrid('expandAll');
	}
	window.setTimeout(expandAll, 500);
});
</script>
<script>
function formatType(val,row){
	if(val == '1'){
		return '模块';
	}
	else if(val == '2'){
		return '<span style="color:#62A824">部门</span>';
	}
	return '<span style="color:#F00060">操作</span>';
}
</script>

<table id="grid_dept" class="easyui-treegrid" fit="true" border="false"
	nowrap="false" rownumbers="true" animate="true" fitColumns="true"
	collapsible="false" idField="id" treeField="text"
	url="${WEB_PATH }/system/dept/getDeptList.do">
	<thead>
		<tr>
			<th field="text" width="120">部门名称</th>

		</tr>
	</thead>
</table>
<div id="win_add" style="width: 380px; height: 180px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 380px; height: 180px; padding: 5px;"
	data-options="cache:false,modal:true"></div>

