<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	
	
	$('#btn_edit').click(function(){
		$.messager.progress();
		$('#form_edit').form('submit', {  
			url: '${WEB_PATH }/app/user/edit.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_edit').dialog('close');
					$('#grid_user').datagrid('reload');
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
					<td width="30%" align="right">帐号：</td>
					<td><input type="hidden" name="id" value="${user.id}" /> <input
						class="easyui-validatebox" type="text" name="account"
						value="${user.account }" readonly required="true" /></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input class="easyui-validatebox" type="password"
						name="pass" /></td>
				</tr>
				<tr>
					<td align="right">姓名：</td>
					<td><input class="easyui-validatebox" type="text" name="name"
						value="${user.name }" required="true" /></td>
				</tr>

				<tr>
					<td align="right">部门：</td>
					<td><input class="easyui-combotree" name="deptId"
						required="true" value="${user.deptId }"
						url="${WEB_PATH }/system/dept/getDeptList.do"
						data-options="onLoadSuccess:function(node, data){
								 $(this).tree('expandAll');
							 }">
					</td>
				</tr>

				<tr>
					<td align="right">年龄：</td>
					<td><input class="easyui-numberbox" type="text" name="age"
						value="${user.age }" /></td>
				</tr>

				<tr>
					<td align="right">电话：</td>
					<td><input class="easyui-validatebox" type="text" name="phone"
						value="${user.phone }" /></td>
				</tr>

				<tr>
					<td align="right">状态：</td>
					<td><select id="combo_status" class="easyui-combobox"
						name="status" editable=false style="width: 80" panelHeight="80"
						url="${WEB_PATH }/global/data/getUserStatusList.do"
						data-options="onLoadSuccess:function(node, data){
								 $(this).combobox('setValue', '${user.status }');
							 }">
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
