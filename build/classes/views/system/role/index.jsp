<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />

<script>
$(function(){
	
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_role').datagrid('reload');
		}
	}, {
		text:'新建',
		iconCls:'icon-add',
		handler:function(){
			var win = $('#win_add').dialog({  
				title: '新建角色',  
				href: '${WEB_PATH }/system/role/view/add.do'
			});
			win.dialog('open');
		}
	}, {
		text:'删除',
		iconCls:'icon-remove',
		handler:function(){
			var row = $('#grid_role').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			$.messager.confirm('操作确认', '你确认删除当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/system/role/remove.do?roleId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_role').datagrid('reload');
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
		text:'菜单配置',
		iconCls:'icon-09',
		handler:function(){
			var row = $('#grid_role').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			var win = $('#win_set').dialog({  
				title: '菜单配置',  
				href: '${WEB_PATH }/system/role/view/set.do'
			});
			win.dialog('open');
		}
	}];
	
	
	$('#grid_role').datagrid({
		toolbar: toolbar,
		onDblClickRow: function(rowIndex, data){
			var win = $('#win_edit').dialog({  
				title: '修改角色',  
				href: '${WEB_PATH }/system/role/view/edit.do?roleId=' + data.id
			});
			
			win.dialog('open');
		},
		onLoadError: function(data){
			showErrorMsg(data);
		}
	});
	
});
</script>


<table id="grid_role" url="${WEB_PATH }/system/role/getPage.do"
	pagination="true" fit="true" border="false" fitColumns="true"
	rownumbers="true" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }">
	<thead>
		<tr>
			<th field="id" width="120" hidden="true">id</th>
			<th field="name" width="120">名称</th>
		</tr>
	</thead>
</table>



<div id="win_add" style="width: 380px; height: 150px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 380px; height: 150px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_set" style="width: 280px; height: 500px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
