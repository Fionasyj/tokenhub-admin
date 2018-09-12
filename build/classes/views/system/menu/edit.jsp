<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	
	
	$('#btn_edit').click(function(){
		$.messager.progress();
		$('#form_edit').form('submit', {  
			url: '${WEB_PATH }/system/menu/edit.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_edit').dialog('close');
					$('#grid_menu').treegrid('reload');
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
					<td width="30%" align="right">菜单名称：</td>
					<td><input type="hidden" name="id" value="${menu.id}" /> <input
						type="hidden" name="leaf" value="${menu.leaf}" /> <input
						type="hidden" name="state" value="${menu.state}" /> <input
						class="easyui-validatebox" type="text" name="text"
						value="${menu.text}" required="true" /></td>
				</tr>

				<tr>
					<td align="right">菜单类型：</td>
					<td><select id="combo_type" class="easyui-combobox"
						name="type" editable=false required="true" style="width: 135"
						panelHeight="80" url="${WEB_PATH }/global/data/getMenuTypeList.do"
						data-options="onLoadSuccess:function(node, data){
								 $(this).combobox('setValue', '${menu.type}');
							}">
					</select></td>
				</tr>

				<tr>
					<td align="right">上级菜单：</td>
					<td><input class="easyui-combotree" name="pid" required="true"
						editable=true
						url="${WEB_PATH }/system/menu/getMenuComboTreeList.do"
						value="${menu.pid}"
						data-options="onLoadSuccess:function(node, data){
								 $(this).tree('expandAll');
							}">
					</td>
				</tr>

				<tr>
					<td align="right">资源地址：</td>
					<td><select id="combo_resourceId" class="easyui-combobox"
						name="resourceId" valueField="id" textField="name" editable=false
						required="true" style="width: 135"
						url="${WEB_PATH }/system/resource/getResourceList.do"
						data-options="onLoadSuccess:function(node, data){
								 $(this).combobox('setValue', '${menu.resourceId }');
							}">
					</select></td>
				</tr>

				<tr>
					<td align="right">参数：</td>
					<td><input class="easyui-validatebox" type="text" name="param"
						value="${menu.param}" /></td>
				</tr>

				<tr>
					<td align="right">图标：</td>
					<td><input class="easyui-validatebox" type="text"
						name="iconCls" value="${menu.iconCls}" /></td>
				</tr>

				<tr>
					<td align="right">排序：</td>
					<td><input class="easyui-numberbox" type="text" name="soft"
						value="${menu.soft}" /></td>
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
