<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />

<script>
$(function(){
	
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_user').datagrid('reload');
		}
	}, {
		text:'新建',
		iconCls:'icon-add',
		handler:function(){
			var win = $('#win_add').dialog({  
				title: '新建用户',  
				href: '${WEB_PATH }/system/user/view/add.do'
			});
			win.dialog('open');
		}
	}, {
		text:'授权',
		iconCls:'icon-010',
		handler:function(){
			var row = $('#grid_user').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			var win = $('#win_acc').dialog({  
				title: '用户授权',  
				href: '${WEB_PATH }/system/user/view/acc.do'
			});
			win.dialog('open');
		}
	}, {
		text:'查询',
		iconCls:'icon-query',
		handler:function(){
			var win = $('#win_query').dialog({  
				title: '用户查询'
			});
			win.dialog('open');
		}
	}];
	
	//用户查询
	$('#btn_query').click(function(){
		var account = $('#q_account').val();
		var deptId = $('#q_deptId').combotree('getValue');
		$('#grid_user').datagrid('load', {account: account, deptId: deptId});
		$('#win_query').window('close');
	});
		
		
	
	$('#grid_user').datagrid({
		toolbar: toolbar,
		onDblClickRow: function(rowIndex, data){
			var win = $('#win_edit').dialog({  
				title: '修改用户',  
				href: '${WEB_PATH }/system/user/view/edit.do?userId=' + data.id
			});
			win.dialog('open');
		},
		onLoadError: function(data){
			showErrorMsg(data);
		}
	});
	
});
</script>

<script>
function formatStatus(val,row){
	if(val == '1'){
		return '<span style="color:red">停用</span>';
	}
	return '正常';
}
</script>

<table id="grid_user" url="${WEB_PATH }/system/user/getPage.do"
	pagination="true" fit="true" border="false" fitColumns="true"
	rownumbers="true" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }">
	<thead>
		<tr>
			<th field="id" width="120" hidden="true">id</th>
			<th field="account" width="120">帐号</th>
			<th field="name" width="120">姓名</th>
			<th field="age" width="120">年龄</th>
			<th field="phone" width="120">电话</th>
			<th field="deptName"  >部门</th>
			<th field="roleNames" width="120">角色组</th>
			<th field="ctime" width="120" formatter="formatTime">创建日期</th>
			<th field="status" width="120" formatter="formatStatus">状态</th>
		</tr>
	</thead>
</table>
<div id="win_add" style="width: 450px; height: 340px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 450px; height: 340px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_acc" style="width: 280px; height: 340px; padding: 5px;"
	data-options="cache:false,modal:true"></div>


<div id="win_query" style="width: 340px; height: 200px; padding: 5px;"
	data-options="cache:false,modal:true">
	<div region="center" border="false" class="centerdiv"
		style="height: 90px">
		<table class="centertable">
			<tr>
				<td width="30%" align="right">帐号：</td>
				<td><input class="easyui-validatebox" type="text"
					id="q_account" name="q_account" /></td>
			</tr>

			<tr>
				<td align="right">部门：</td>
				<td><input class="easyui-combotree" id="q_deptId"
					name="q_deptId" url="${WEB_PATH }/system/dept/getDeptList.do"
					data-options="onLoadSuccess:function(node, data){
								 $(this).tree('expandAll');
							 }">
				</td>
			</tr>
		</table>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_query" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">查询</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_query').window('close');">关闭</a>
	</div>
</div>

