<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />

<script>
$(function(){
	
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_carousel').datagrid('reload');
		}
	}, {
		text:'新建',
		iconCls:'icon-add',
		handler:function(){
			var win = $('#win_add').dialog({  
				title: '新建轮播',  
				href: '${WEB_PATH }/app/carousel/view/add.do'
			});
			win.dialog('open');
		}
	}, {
		text:'删除',
		iconCls:'icon-cancel',
		handler:function(){
			var row = $('#grid_carousel').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			$.messager.confirm('操作确认', '你确认删除当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/carousel/remove.do?carouselId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_carousel').datagrid('reload');
								showMsg(data.msg, 'success');
							}
						},
						error: function(result) {
							showErrorMsg(result);
						}
					});
				}
			});
		}
	}, {
		text:'禁用',
		iconCls:'icon-remove',
		handler:function(){
			var row = $('#grid_carousel').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			$.messager.confirm('操作确认', '你确认禁用当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/carousel/forbidden.do?carouselId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_carousel').datagrid('reload');
								showMsg(data.msg, 'success');
							}
						},
						error: function(result) {
							showErrorMsg(result);
						}
					});
				}
			});
		}
	}, {
		text:'启用',
		iconCls:'icon-start',
		handler:function(){
			var row = $('#grid_carousel').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			$.messager.confirm('操作确认', '你确认启用当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/carousel/release.do?carouselId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_carousel').datagrid('reload');
								showMsg(data.msg, 'success');
							}
						},
						error: function(result) {
							showErrorMsg(result);
						}
					});
				}
			});
		}
	}, {
		text:'推送',
		iconCls:'icon-push',
		handler:function(){
			var row = $('#grid_carousel').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			var win = $('#win_push').dialog({  
					title: '推送消息',
					content:'你确定【推送】id为【'+row.id+'】内容为【'+row.content+'】',
					buttons:[{
						text:'确定',
						handler:function(){
							$.ajax({
								url : '${WEB_PATH }/app/carousel/pushCarousel.do?carouselId=' + row.id,
								cache : false,
								type : 'POST',
								success : function(result) {
									$.messager.progress('close'); 
									var data = $.parseJSON(result);
									if(data.success == true){
										$('#win_push').window('close');
										$('#grid_carousel').datagrid('reload');
										showMsg(data.msg, 'success');
									}
								},
								error: function(result) {
									showErrorMsg(result);
								}
							});
						}
					},{
						text:'关闭',
						handler:function(){
							$('#win_push').window('close');
						}
					}]
				});
				win.dialog('open');
		}
	}];
	
	//用户查询
	$('#btn_query').click(function(){
		var nick = $('#nick').val();
		var mobile = $('#mobile').combotree('getValue');
		$('#grid_carousel').datagrid('load', {nick: nick, mobile: mobile});
		$('#win_query').window('close');
	});
		
		
	
	$('#grid_carousel').datagrid({
		toolbar: toolbar,
		onDblClickRow: function(rowIndex, data){
			var win = $('#win_edit').dialog({  
				title: '修改轮播',  
				href: '${WEB_PATH }/app/carousel/view/edit.do?carouselId=' + data.id
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
	if(val == '3'){
		return '<span style="color:red">禁用</span>';
	}
	return '启用';
}
</script>

<table id="grid_carousel" url="${WEB_PATH }/app/carousel/getPage.do"
	pagination="true" fit="true" border="false" fitColumns="true"
	rownumbers="true" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }">
	<thead>
		<tr>
			<th field="id" width="120" hidden="true">id</th>
			<th field="title" width="120">标题</th>
			<th field="created_at" width="120" formatter="formatTime">创建时间</th>
			<th field="status" width="120" formatter="formatStatus">状态</th>
		</tr>
	</thead>
</table>
<div id="win_add" style="width: 500px; height: 400px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 500px; height: 400px; padding: 5px;"
	data-options="cache:false,modal:true"></div>

<div id="win_push" style="width: 340px; height: 200px; padding: 5px;"
	data-options="cache:false,modal:true">
	<div region="center" border="false" class="centerdiv"
		style="height: 90px">
				
	</div>

</div>