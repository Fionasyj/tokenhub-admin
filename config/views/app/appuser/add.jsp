<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	$('#btn_add').click(function(){
		$.messager.progress();
		$('#form_add').form('submit', {  
			url: '${WEB_PATH }/app/user/add.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_add').dialog('close');
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
		<form id="form_add" method="post">
			<table class="centertable">
				<tr>
					<td width="20%" align="right">帐号：</td>
					<td><input class="easyui-validatebox" type="text"
						name="account" required="true" style="width:280px" /></td>
				</tr>
				<tr>
					<td width="20%" align="right">密码：</td>
					<td><input class="easyui-validatebox" type="password"
						name="pass" required="true" style="width:280px"/></td>
				</tr>
				<tr>
					<td width="20%"  align="right">姓名：</td>
					<td><input class="easyui-validatebox" type="text" name="name"
						required="true" style="width:280px"/></td>
				</tr>
				<tr>
					<td width="20%"  align="right">部门：</td>
					<td><input class="easyui-combotree" name="deptId"
						required="true" url="${WEB_PATH }/system/dept/getDeptLists.do"
						data-options="onLoadSuccess:function(node, data){
								 $(this).tree('expandAll');
							 }" style="width:280px">
					</td>
				</tr>
				<tr>
					<td width="20%"  align="right">年龄：</td>
					<td><input class="easyui-numberbox" type="text" name="age" style="width:280px"/>
					</td>
				</tr>
				<tr>
					<td width="20%"  align="right">电话：</td>
					<td><input class="easyui-validatebox" type="text" name="phone" style="width:280px"/>
					</td>
				</tr>
				<tr>
					<td width="20%"  align="right">状态：</td>
					<td>
						<select id="combo_status" class="easyui-combobox"
						name="status" editable=false required="true" style="width: 80"
						panelHeight="80"
						url="${WEB_PATH }/global/data/getUserStatusList.do"
						data-options="onLoadSuccess:function(node, data){
								 $(this).combobox('setValue', '0');
							 }">
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