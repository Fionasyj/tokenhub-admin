<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
$(function(){
	$('#btn_edit').click(function(){
		$.messager.progress();
		$('#form_edit').form('submit', {  
			url: '${WEB_PATH }/app/carousel/edit.do',  
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

<script>
	
$(function() {
    //头像预览
    $("#headPic").click(function () {
        $("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
        $("#upload").on("change",function(){
            var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#headPic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
            }
        });
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
</script>
<div class="easyui-layout" fit="true">
	<div region="center" border="false" class="centerdiv">
		<form id="form_edit" method="post" enctype="multipart/form-data">
			<table class="centertable">
				<tr>
					<td width="15%" align="right">标题：<input type="hidden" name="id" value="${carousel.id}" /><input type="hidden" name="news_type" value="${carousel.news_type}" /> <input type="hidden" name="status" value="${carousel.status}" /><input type="hidden" name="pic_url" value="${carousel.pic_url}" /></td>
					<td><input class="easyui-validatebox" type="text"
						name="title" required="true" style="width:280px" value="${carousel.title }"/></td>
				</tr>
				<tr>
					<div style="margin-left: 17px;" >
					    <label width="15%">上传图片：</label><br><br>
						<input id="upPic" type="button" value="upload"><br>
					    <img id="headPic"   width="280px" height="150px" style="padding-left: 60px;" src="<%=request.getContextPath() %>/images/${carousel.pic_url}">
					   <input id="upload" name="file" accept="image/*" type="file" style="display: none"/>
					
					</div>
				</tr>
				<tr>
					<td width="15%"  align="right">详情链接：</td>
					<td><input class="easyui-validatebox" type="text" name="url"
						required="true" style="width:280px" value="${carousel.url }"/></td>
				</tr>
			</table>
		</form>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_edit" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">修改</a> &nbsp;&nbsp;&nbsp;&nbsp; 
			<a class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_edit').window('close');">关闭</a>
	</div>
</div>
