<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />
<style type="text/css">
            .datagrid-cell, .datagrid-cell-group, .datagrid-header-rownumber, .datagrid-cell-rownumber
            {
                text-overflow: ellipsis;
            }
   </style> 
<script>
$(function(){
	var toolbar = [{
		text:'刷新',
		iconCls:'icon-reload',
		handler:function(){
			$('#grid_flushnews').datagrid('reload');
		}
	} , {
		text:'新建',
		iconCls:'icon-add',
		handler:function(){
			var win = $('#win_add').dialog({  
				title: '新建快讯',
				href: '${WEB_PATH }/app/flushnews/view/add.do'
			});
			win.dialog('open');
		}
	}, {
		text:'删除',
		iconCls:'icon-cancel',
		handler:function(){
			var row = $('#grid_flushnews').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			
			$.messager.confirm('操作确认', '你确认删除当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/flushnews/remove.do?flushnewsId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_flushnews').datagrid('reload');
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
			var row = $('#grid_flushnews').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if(row.status == 3){
				$.messager.alert('温馨提示', '当前记录已是禁用状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认禁用当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/flushnews/forbidden.do?flushId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_flushnews').datagrid('reload');
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
			var row = $('#grid_flushnews').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			if(row.status != 3){
				$.messager.alert('温馨提示', '当前记录已是启用状态！');
				return false;
			}
			$.messager.confirm('操作确认', '你确认启用当前记录？', function(btn) {
				if (btn) {
					$.messager.progress();
					$.ajax({
						url : '${WEB_PATH }/app/flushnews/release.do?flushId=' + row.id,
						cache : false,
						type : 'POST',
						success : function(result) {
							$.messager.progress('close'); 
							var data = $.parseJSON(result);
							if(data.success == true){
								$('#grid_flushnews').datagrid('reload');
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
		text:'标记颜色',
		iconCls:'icon-mark',
		handler:function(){
			var row = $('#grid_flushnews').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			var win = $('#win_color').dialog({  
					title: '标记颜色',
				});
			$("#redcolor").attr("checked","checked");
				win.dialog('open');
				var colorValue;
				$('#btn_mark').unbind('click');
				 $('#btn_mark').click(function(){
					 colorValue=$('input:radio[name="color"]:checked').val();
					 if(colorValue == undefined || colorValue ==""){
						 showMsg("请选择标记颜色");
					 }else{
						$('#win_color').window('close');
						$.ajax({
							url : '${WEB_PATH }/app/flushnews/markColor.do?flushId=' + row.id +'&color='+colorValue,
							cache : false,
							type : 'POST',
							success : function(result) {
								$.messager.progress('close'); 
								
								var data = $.parseJSON(result);
								if(data.success == true){
									$('#grid_flushnews').datagrid('reload');
									showMsg(data.msg, 'success');
								}
								
							},
							error: function(result) {
								showErrorMsg(result);
							}
						});
					 }
					 return;
					});
		}
	}, {
		text:'推送',
		iconCls:'icon-push',
		handler:function(){
			var row = $('#grid_flushnews').datagrid('getSelected');
			if (row == null) {
				$.messager.alert('温馨提示', '请先选择一条记录！');
				return false;
			}
			var win = $('#win_push').dialog({  
					title: '推送消息',
					content:"你确定【推送】id为【"+row.id+"】内容为【"+row.content+"】 <br/> 并标记该消息为  <input id='pushredcolor' name='color' type='radio' value='255,0,0' checked='checked' onclick='setPushColorState(0)'/> 红色  <input id='pushyellowcolor'  name='color' value='255,255,0' type='radio' onclick='setPushColorState(1)'/> 黄色 <input id='pushbluecolor'  name='color' value='0,0,255'  type='radio' onclick='setPushColorState(2)'/> 蓝色 ",
					buttons:[{
						text:'确定',
						handler:function(){
							var setcolor;
							if($("#pushredcolor").prop('checked')){
								setcolor = "255,0,0"
							}else if($("#pushbluecolor").prop('checked')){
								setcolor = "0,0,255"
							}else if($("#pushyellowcolor").prop('checked')){
								setcolor = "255,255,0"
							}
							$.ajax({
								url : '${WEB_PATH }/app/flushnews/pushNews.do?flushId=' + row.id +'&content_color=' +setcolor,
								cache : false,
								type : 'POST',
								success : function(result) {
									$.messager.progress('close'); 
									
									var data = $.parseJSON(result);
									if(data.success == true){
										$('#win_push').window('close');
										$('#grid_flushnews').datagrid('reload');
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
	
	$('#grid_flushnews').datagrid({
		toolbar: toolbar,
		onDblClickRow: function(rowIndex, data){
			var win = $('#win_edit').dialog({  
				title: '修改快讯',  
				href: '${WEB_PATH }/app/flushnews/view/edit.do?flushnewsId=' + data.id
			});
			win.dialog('open');
		},
		onLoadError: function(data){
			showErrorMsg(data);
		},
		render: function(target, container, frozen){
			alert(target);
		}
	});
	
	//快讯查询
	$('#btn_query').click(function(){
		var q_content = $('#q_content').val();
		$('#grid_flushnews').datagrid('load', {content: q_content});
		$('#win_query').window('close');
	});
});
</script>
<script>
//推送信息时标记颜色
function setPushColorState(color){
	$("#pushbluecolor").removeAttr("checked");
	$("#pushredcolor").removeAttr("checked");
	$("#pushyellowcolor").removeAttr("checked");
	if(color == "0"){
		$("#pushredcolor").attr("checked","checked");
	}else if(color == "2"){
		$("#pushbluecolor").attr("checked","checked");
	}else if(color == "1"){
		$("#pushyellowcolor").attr("checked","checked");
	}
};

//标记颜色
function setColorState(color){
	$("#bluecolor").removeAttr("checked");
	$("#redcolor").removeAttr("checked");
	$("#yellowcolor").removeAttr("checked");
	if(color == "255,0,0"){
		$("#redcolor").attr("checked","checked");
	}else if(color == "255,255,0"){
		$("#yellowcolor").attr("checked","checked");
	}else if(color == "0,0,255"){
		$("#bluecolor").attr("checked","checked");
	}
}

function formatStatus(val,row){
	if(val == '3'){
		return '<span style="color:red">已禁用</span>';
	}
	return '已启用';
}
// 根据来源类型数据设置来源
function setSource_mediaColor(val,row){
		if(row.content_color == "#FF0000") {
			if(row.source_media == 1){
				return '<span style="color:rgb(255,0,0)">币世界</span>';
			}else if(row.source_media == 2){
				return '<span style="color:rgb(255,0,0)">金色财经</span>';
			}else if(row.source_media == 3){
				return '<span style="color:rgb(255,0,0)">TokenHub</span>';
			}
		}else if(row.content_color == "#0000FF") {
			if(row.source_media == 1){
				return '<span style="color:rgb(0,0,255)">币世界</span>';
			}else if(row.source_media == 2){
				return '<span style="color:rgb(0,0,255)">金色财经</span>';
			}else if(row.source_media == 3){
				return '<span style="color:rgb(0,0,255)">TokenHub</span>';
			}
		}else if(row.content_color == "#FFFF00") {
			if(row.source_media == 1){
				return '<span style="color:rgb(255,255,0)">币世界</span>';
			}else if(row.source_media == 2){
				return '<span style="color:rgb(255,255,0)">金色财经</span>';
			}else if(row.source_media == 3){
				return '<span style="color:rgb(255,255,0)">TokenHub</span>';
			}
		}else{
			if(row.source_media == 1){
				return '<span style="">币世界</span>';
			}else if(row.source_media == 2){
				return '<span style="">金色财经</span>';
			}else if(row.source_media == 3){
				return '<span style="">TokenHub</span>';
			}
		}
}
function setContentColor(val,row){
	if(row.content_color == "#FF0000") {
		return '<span style="color:rgb(255,0,0)">'+ row.content +'</span>';
	}else if(row.content_color == "#0000FF") {
		return '<span style="color:rgb(0,0,255)">'+ row.content +'</span>';
	}else if(row.content_color == "#FFFF00") {
		return '<span style="color:rgb(255,255,0)">'+ row.content +'</span>';
	}else{
		return '<span style="">'+ row.content +'</span>';
	}
	
}
function setContent_colorColor(val,row){
	if(row.content_color == "#FF0000") {
		return '<span style="color:rgb(255,0,0)">'+ row.content_color +'</span>';
	}else if(row.content_color == "#0000FF") {
		return '<span style="color:rgb(0,0,255)">'+ row.content_color +'</span>';
	}else if(row.content_color == "#FFFF00") {
		return '<span style="color:rgb(255,255,0)">'+ row.content_color +'</span>';
	}else{
		return '<span style="">'+ row.content_color +'</span>';
	}
}
function setPush_timesColor(val,row){
	if(row.content_color == "#FF0000") {
		return '<span style="color:rgb(255,0,0)">'+ row.push_times +'</span>';
	}else if(row.content_color == "#0000FF") {
		return '<span style="color:rgb(0,0,255)">'+ row.push_times +'</span>';
	}else if(row.content_color == "#FFFF00") {
		return '<span style="color:rgb(255,255,0)">'+ row.push_times +'</span>';
	}else{
		return '<span style="">'+ row.push_times +'</span>';
	}
}
function groupFormatter(fvalue, rows){
	return fvalue + ' - <span style="color:red">' + rows.length + ' rows</span>';
}
// 格式化时间，将时间戳转换成时间
function formatTime(val, row){
	/*时间戳为10位需*1000，时间戳为13位的话不需乘1000*/
	var date = new Date(row.created_at);
	var Year = date.getFullYear() + '-';
	var Month = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	var Day = date.getDate() + ' ';
	var hours = date.getHours() + ':';
	var minutes = date.getMinutes() + ':';
	var second = date.getSeconds();
	if(row.content_color == "#FF0000") {
		return '<span style="color:rgb(255,0,0)">'+ Year+Month+Day+hours+minutes+second +'</span>';
	}else if(row.content_color =="#0000FF") {
		return '<span style="color:rgb(0,0,255)">'+ Year+Month+Day+hours+minutes+second +'</span>';
	}else if(row.content_color =="#FFFF00") {
		return '<span style="color:rgb(255,255,0)">'+ Year+Month+Day+hours+minutes+second +'</span>';
	}else{
		return '<span style="">'+ Year+Month+Day+hours+minutes+second +'</span>';
	}
}
</script>
	<table id="grid_flushnews" url="${WEB_PATH }/app/flushnews/getPage.do"
	pagination="true" fit="true" border="false" fitColumns="true"
	rownumbers="true" singleSelect="true" pageSize="${PAGE_SIZE }"
	pageList="${PAGE_LIST }" data-options="
				url: 'propertygrid_data1.json',
				method: 'get',
				showGroup: true,
				scrollbarSize: 0,
				groupFormatter: groupFormatter
			">
	<thead>
		<tr>
			<th hidden="hidden" field="id"></th>
			<th field="content" width="120" formatter="setContentColor">内容</th>
			<th field="source_media" width="120" formatter="setSource_mediaColor">数据来源</th>
			<th field="created_at" width="120" formatter="formatTime">创建时间</th>
			<th field="content_color" width="120" formatter="setContent_colorColor">标色RGB值</th>
			<th field="status" width="120" formatter="formatStatus">状态</th>
			<th field="push_times" width="120" formatter="setPush_timesColor">推送次数</th>
		</tr>
	</thead>
</table>



<div id="win_add" style="width: 600px; height: 400px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<div id="win_edit" style="width: 600px; height: 400px; padding: 5px;"
	data-options="cache:false,modal:true"></div>
<!-- 标记颜色 -->	
<div id="win_color" style="width: 340px; height: 200px; padding: 5px;"
	data-options="cache:false,modal:true">
	<div region="center" border="false" class="centerdiv"
		style="height: 90px">
				<input class="" id="redcolor" name="color" type="radio" value="255,0,0" onclick="setColorState('255,0,0');"/> 红色
				<input class="" id="yellowcolor"  name="color" value="255,255,0"  type="radio" onclick="setColorState('255,255,0');"> 黄色
				<input class="" id="bluecolor"  name="color" value="0,0,255"   type="radio" onclick="setColorState('0,0,255');"> 蓝色
				
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_mark" class="easyui-linkbutton" iconCls="icon-ok"
			href="javascript:void(0)">标记</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_color').window('close');">关闭</a>
	</div>
</div>


<div id="win_push" style="width: 600px; height: 300px; padding: 5px;"
	data-options="cache:false,modal:true">
	<div region="center" border="false" class="centerdiv"
		style="height: 90px">
				
	</div>

</div>


<div id="win_query" style="width: 340px; height: 200px; padding: 5px;"
	data-options="cache:false,modal:true">
	<div region="center" border="false" class="centerdiv"
		style="height: 90px">
		<table class="centertable">
			<tr>
				<td width="30%" align="right">内容：</td>
				<td><input class="easyui-validatebox" type="text"
					id="q_content" name="q_content" /></td>
			</tr>
		</table>
	</div>

	<div region="south" class="buttomdiv">
		<a id="btn_query" class="easyui-linkbutton l-btn" iconCls="icon-ok"
			href="javascript:void(0)">查询</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			class="easyui-linkbutton" iconCls="icon-cancel"
			href="javascript:void(0)" onclick="$('#win_query').window('close');">关闭</a>
	</div>
</div>




