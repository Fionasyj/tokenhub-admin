<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	
	
	$('#btn_add').click(function(){
		$.messager.progress();
		$('#form_add').form('submit', {  
			url: '${WEB_PATH }/system/resource/add.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_add').dialog('close');
					$('#grid_resource').datagrid('reload');
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
		<form id="form_add" method="post">
			<table class="centertable">
				<tr>
					<td width="25%" align="right">资源名称：</td>
					<td><input class="easyui-validatebox" type="text" name="name"
						required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td align="right">链接：</td>
					<td><input class="easyui-validatebox" type="text" name="url"
						required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td><input class="easyui-validatebox" type="text"
						name="remark" style="width: 200px" /></td>
				</tr>

				<tr>
					<td align="right">状态：</td>
					<td><select class="easyui-combobox" name="status"
						editable=false style="width: 80" panelHeight="80">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
				</tr>

			</table>
		</form>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_add" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">保存</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_add').window('close');">关闭</a>
	</div>
</div>