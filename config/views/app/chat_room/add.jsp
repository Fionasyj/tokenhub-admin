<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	
	
	$('#btn_add').click(function(){
		$.messager.progress();
		$('#form_add').form('submit', {  
			url: '${WEB_PATH }/app/chat_room/add.do',  
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

<script>
$(function() {
    //头像预览
    $("#upPic").click(function () {
        $("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
        $("#upload").on("change",function(){
            var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#headPic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
            }
        });
    });

//建立一個可存取到該file的url
function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}
});

</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="centerdiv">
		<form id="form_add" method="post" enctype="multipart/form-data">
			<table class="centertable">
				<tr>
					<td align="right">聊天室名字：</td>
					<td><input class="easyui-validatebox" type="text" name="room_name"
						required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<div style="margin-left: 62px;" >
					    <label>聊天室头像：</label><br><br>
						<input id="upPic" type="button" value="upload"><br>
					    <img id="headPic"   width="200px" height="150px" style="padding-left: 80px;">
					   <input id="upload" name="file" accept="image/*" type="file" style="display: none"/>
					
					</div>
				</tr>
				<tr>
					<td width="25%" align="right">聊天室ID：</td>
					<td><input class="easyui-validatebox" type="text" name="room_id"
						required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td align="right">聊天室描述：</td>
					<td><input class="easyui-validatebox" type="text" name=description
						required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td align="right">聊天室公告：</td>
					<td><input class="easyui-validatebox" type="text"
						name="notice" style="width: 200px" /></td>
				</tr>
				<tr>
					<td align="right">聊天室公告详情：</td>
					<td><input class="easyui-validatebox" type="text"
						name="notice_url" style="width: 200px" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div region="south" class="buttomdiv" style="width: 900xp;">
		<a id="btn_add" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">保存</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_add').window('close');">关闭</a>
	</div>
</div>