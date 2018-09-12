<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>系统登陆</title>
<link rel="shortcut icon" href="${WEB_PATH }/favicon.ico">
<c:import url="${WEB_PATH }/import.jsp" />

<script>
$(function(){
	$('#btn_reset').click(function(){
		$('#account').val('');
		$('#pass').val('');
		$('#code').val('');
	});
	$('#loginAccountSel').combobox({
		onSelect:function(record){
			var curValue=$('#loginAccountSel').combobox('getValue');
			$("#account").val(curValue);
		}
	});
	/*
	$('#btn_login').click(function(){
		var account = $('#account').val();
		var pass = $('#pass').val();
		var code = $('#code').val();
		
		if(account == ''){
			$('#account').focus();
			return false;
		}
		if(pass == ''){
			$('#pass').focus();
			return false;
		}
		
		$.messager.progress();
		
		
		$.ajax({
			url : '${WEB_PATH }/system/user/login.do',  
			cache : false,
			type : 'POST',
			data: {account: account, pass: pass, code: code},
			success : function(result) {
				$.messager.progress('close'); 
				
				var data = $.parseJSON(result);
				if(data.success == true){
					top.location.href="${WEB_PATH }/index.do";
				}
			},
			error: function(result) {
				showErrorMsg(result);
			}
		});
		
	});
	
	*/
	
	
});

function submit(){
	var account = $('#account').val();
	var pass = $('#pass').val();
	var code = $('#code').val();
	
	if(account == ''){
		$('#account').focus();
		return false;
	}
	if(pass == ''){
		$('#pass').focus();
		return false;
	}
	$.messager.progress();
	$.ajax({
		url : '${WEB_PATH }/system/user/login.do',  
		cache : false,
		type : 'POST',
		data: {account: account, pass: pass, code: code},
		success : function(result) {
			$.messager.progress('close'); 
			
			var data = $.parseJSON(result);
			if(data.success == true){
				top.location.href="${WEB_PATH }/index.do";
			}
		},
		error: function(result) {
			showErrorMsg(result);
		}
	});
}

function _submit() 
{     
	if (event.keyCode == 13) 
	{      
		submit();
	}
}

</script>
<style type="text/css">
.login-body{
	background-size:100% 100%;
}
</style>
<script type="text/javascript">
$(function(){
	$('#login-dlg').panel({title: "<font color=\"blue\" style='font-weight:bold;font-family:KaiTi;' size='4'>TokenHub后台管理系统</font>"});
});
</script>
</head>
<body class="login-body">
<div class="easyui-dialog" title=" "
	data-options="closable:false,modal:true"
	style="width: 450px; height: 250px; padding: 10px" id="login-dlg"  >
	<div class="easyui-layout" fit="true">
		<div region="center" border="false" class="centerdiv">
			<table class="centertable" style="border-collapse:separate; border-spacing:0px 15px;">
				<tr>
					<td width="25%" align="right">帐&nbsp;号：</td>
					<td><input class="easyui-validatebox" type="text" id="account"
						name="account" id="account" required="true" style="width: 250px" value=""/></td>
				</tr>
				<tr>
					<td align="right">密&nbsp;码：</td>
					<td><input class="easyui-validatebox" type="password"
						id="pass" name="pass" required="true" style="width: 250px" /></td>
				</tr>
				<tr>
					<td width="25%" align="right">验证码：</td>
					<td><input type="text" id="code" name="code"
						onkeypress="_submit()" required="true"
						style="width: 60px; vertical-align: middle;" /> <img
						src="${WEB_PATH }/image.jsp"
						style="padding-top: 0px; height: 23px; vertical-align: middle;" />
					</td>
				</tr>
			</table>
		</div>
		<div region="south" class="buttomdiv">
			<a id="btn_login" class="easyui-linkbutton" iconCls="icon-ok"
				href="javascript:void(0)" onclick="submit()">登陆</a>

			&nbsp;&nbsp;&nbsp;&nbsp; <a id="btn_reset" class="easyui-linkbutton"
				iconCls="icon-cancel" href="javascript:void(0)" onclick="">重置</a>
		</div>
	</div>
</div>
</body>