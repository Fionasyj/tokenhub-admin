<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	$('#btn_add').click(function(){
		$.messager.progress();
		$('#form_add').form('submit', {  
			url: '${WEB_PATH }/app/chat_room/addNotice.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_add').dialog('close');
					$('#grid_chat_room').datagrid('reload');
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
					<td width="15%" align="right">公告内容：</td><br/>
					<input type="hidden" name="id"	value="${chat_roomId}" />
					<td><textarea style="width: 100%" class="easyui-validatebox" required="true" name="notice" rows="12" cols="60"></textarea></td>
					
				</tr>
				<tr>
					<td width="15%" align="right">公告url：</td>
					<td><input class="easyui-validatebox" type="text" name="notice_url"
						required="true" style="width: 100%" /></td>
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