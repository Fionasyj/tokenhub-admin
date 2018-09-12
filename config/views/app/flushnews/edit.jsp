<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(function(){
	
	
	$('#btn_edit').click(function(){
		$.messager.progress();
		$('#form_edit').form('submit', {  
			url: '${WEB_PATH }/app/flushnews/edit.do',  
			cache : false,
			onSubmit: function(){  
				return validSubmit(this);
			},  
			success:function(result){  
				$.messager.progress('close'); 
				var data = $.parseJSON(result);
				if(data.success == true){
					$('#win_edit').dialog('close');
					$('#grid_flushnews').datagrid('reload');
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
					<td width="15%" align="right">内容：</td>
					<td><input type="hidden" name="id" value="${flushnews.id }"/><input type="hidden" name="source_media" value="${flushnews.source_media }"/><input type="hidden" name="status" value="${flushnews.status }"/><input type="hidden" name="content_color" value="${flushnews.content_color }"/>
					<textarea rows="10"  style="width: 100%;padding-top: 50px;" cols="50" name="content" id="content" class="easyui-validatebox" required="true">${flushnews.content }</textarea></td>
				</tr>
				<tr>
					<td width="15%" align="right">原文链接：</td>
					<td><input name="url" id="url" value="${flushnews.url }" class="easyui-validatebox" style="width: 100%"></td>
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
