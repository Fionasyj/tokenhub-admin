<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function(){
	
	
	$('#btn_edit').click(function(){
		$.messager.progress();
		$('#form_edit').form('submit', {  
			url: '${WEB_PATH }/system/resource/edit.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_edit').dialog('close');
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
		<form id="form_edit" method="post">
			<table class="centertable">
				<tr>
					<td width="25%" align="right">资源名称：</td>
					<td><input type="hidden" name="id" value="${resource.id}" /> <input
						class="easyui-validatebox" type="text" name="name"
						value="${resource.name }" required="true" style="width: 200px" />
					</td>
				</tr>
				<tr>
					<td align="right">链接：</td>
					<td><input class="easyui-validatebox" type="text" name="url"
						value="${resource.url }" required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td><input class="easyui-validatebox" type="text"
						name="remark" value="${resource.remark }" style="width: 200px" />
					</td>
				</tr>

				<tr>
					<td align="right">状态：</td>
					<td><select class="easyui-combobox" name="isLog"
						editable=false style="width: 80" panelHeight="80">
							<option value="0"
								<c:if test="${resource.isLog=='0' }">selected</c:if>>否</option>
							<option value="1"
								<c:if test="${resource.isLog=='1' }">selected</c:if>>是</option>
					</select></td>
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