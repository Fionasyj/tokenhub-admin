<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	
	
	$('#btn_edit').click(function(){
		$.messager.progress();
		$('#form_edit').form('submit', {  
			url: '${WEB_PATH }/system/dept/edit.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_edit').dialog('close');
					$('#grid_dept').treegrid('reload');
					showMsg(data.msg, 'success');
				}else{
					showErrorMsg(result);
				}
			}
		});
	});
	
});
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="centerdiv">
		<form id="form_edit" method="post">
			<table class="centertable">
				<tr>
					<td width="30%" align="right">部门名称：</td>
					<td><input type="hidden" name="id" value="${dept.id}" /> <input
						type="hidden" name="leaf" value="${dept.leaf}" /> <input
						type="hidden" name="state" value="${dept.state}" /> <input
						class="easyui-validatebox" type="text" name="name"
						value="${dept.name }" required="true" /></td>
				</tr>

				<tr>
					<td align="right">上级部门：</td>
					<td><input class="easyui-combotree" name="pid" required="true"
						url="${WEB_PATH }/system/dept/getDeptList.do" value="${dept.pid }"
						data-options="onLoadSuccess:function(node, data){
								 $(this).tree('expandAll');
							 }">
					</td>
				</tr>

			</table>
		</form>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_edit" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">修改</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_edit').window('close');">关闭</a>
	</div>
</div>