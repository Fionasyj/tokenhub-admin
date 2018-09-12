<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />
<script>
$(function(){
	
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_resource').datagrid('reload');
		}
	}, {
		text:'新建',
		iconCls:'icon-add',
		handler:function(){
			var win = $('#win_add').dialog({  
				title: '新建资源',  
				href: '${WEB_PATH }/system/resource/view/add.do'
			});
			win.dialog('open');
		}
	}, {
		text:'删除',
		iconCls:'icon-remove',
		handler:function(){
			var row = $('#grid_resource').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			$.messager.confirm('操作确认', '你确认删除当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/system/resource/remove.do?resourceId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_resource').datagrid('reload');
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
	
	
	$('#grid_resource').datagrid({
		toolbar: toolbar,
		onDblClickRow: function(rowIndex, data){
			var win = $('#win_edit').dialog({  
				title: '修改资源',  
				cache: false,  
				href: '${WEB_PATH }/system/resource/view/edit.do?resourceId=' + data.id,  
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
function formatIsLog(val,row){
	if(val == '1'){
		return '是';
	}
	return '';
}
</script>


<table id="grid_resource" url="${WEB_PATH }/system/resource/getPage.do"
	pagination="true" fit="true" border="false" fitColumns="true"
	rownumbers="true" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }">
	<thead>
		<tr>
			<th field="id" width="120" hidden="true">id</th>
			<th field="name" width="120">名称</th>
			<th field="url" width="120">链接</th>
			<th field="remark" width="120">备注</th>
			<th field="isLog" width="120" formatter="formatIsLog">日志</th>
		</tr>
	</thead>
</table>



<div id="win_add" style="width: 380px; height: 250px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 380px; height: 250px; padding: 5px;"
	data-options="cache:false,modal:true"></div>

