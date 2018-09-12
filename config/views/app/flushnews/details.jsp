<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="${WEB_PATH }/import.jsp" />
<script>

function formatStatus(val,row){
	if(val == '3'){
		return '<span style="color:red">已禁用</span>';
	}
	return '已启用';
}

</script>
	<table id="grid_details" url="${WEB_PATH }/system/flushnews/getPage.do"
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
			<th field="id" width="120" hidden="true">id</th>
			<th field="title" width="120">标题</th>
			<th field="content" width="120">内容</th>
			<th field="created_at" width="120" formatter="formatTime">创建时间</th>
			<th field="status" width="120" formatter="formatStatus">状态</th>
			<th field="show_color" width="120">标记色</th>
		</tr>
	</thead>
</table>
