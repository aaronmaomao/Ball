<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/easyui/themes/icon.css">
<style type="text/css">
body {
	margin: 0px;
}

.datagrid-header-row, .datagrid-row {
	height: 22px;
}

.panelHeader {
	height: 20px;
	padding-top: 2px;
	padding-bottom: 0;
	border-top: 0;
}
</style>
<script type="text/javascript" src="<%=basePath%>resource/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/easyui/ext/datagrid-detailview.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/easyui/ext/datagrid-cellediting.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/ssq.js"></script>
<script type="text/javascript">
	$(function() {
		//表格
		$('#ssqTable').datagrid({
			url : './ssq',
			method : 'get',
			queryParams : {
				fun : 'getRecords'
			},
			onBeforeLoad : function(param) {
				param.start = (param.page - 1) * param.rows;
				param.end = param.start + param.rows - 1;
			},
			loadFilter : function(data) {
				debugger
				if (data.isSuccess == "true") {
					return data.data;
				}
			},
			onLoadSuccess : function(data) {
				debugger
				$('#ssqTable').datagrid("scrollTo", data.rows.length - 1);
			}

		});
		
		//日期选择
		var curDate = new Date();
		$("#startDate").datebox({
			value : curDate.getFullYear()+"-01-01",
			onSelect:function(date){
				debugger
			}
		});
		$('#startDate').datebox().datebox('calendar').calendar({
			validator: function(date){
				var d1 = new Date("2003-02-23");
				if (date.getTime() < d1.getTime() || date.getTime() > curDate.getTime()) {
					return false; 
				}
				return true;
			}
		});

		$("#endDate").datebox({
			value : dateFormat(curDate)
		});
		$('#endDate').datebox().datebox('calendar').calendar({
			validator: function(date){
				var d1 = new Date("2003-02-23");
				if (date.getTime() < d1.getTime() || date.getTime() > curDate.getTime()) {
					return false;
				}
				return true;
			}
		});
	});

	function redCellStyler(value, row, index) {
		return "color:#ff0000";
	}

	function blueCellStyler(value, row, index) {
		return "color:#0000ff";
	}

	function redCellFormat(value, row, index) {
		return value;
	}

	function sum(){
		$.get("./ssq", {fun:"sum"}, function(data){
			$('#ssqTable').datagrid("loadData", data);
		}, "json");
	}
</script>
</head>
<body>
	<table id="ssqTable" style="width: 500" class="easyui-datagrid"
		data-options="width:'700',idField:'id',ctrlSelect:'true',fit:'true', pagination:true,pageSize:100, pageList:[10,30,100,99999], toolbar:'#toolbar'">
		<thead>
			<tr>
				<th data-options="field:'id',width:50">id</th>
				<th data-options="field:'date',width:70">date</th>
				<th data-options="field:'r1',width:40,align:'center', styler:redCellStyler, formatter:redCellFormat">r1</th>
				<th data-options="field:'r2',width:40,align:'center', styler:redCellStyler, formatter:redCellFormat">r2</th>
				<th data-options="field:'r3',width:40,align:'center', styler:redCellStyler, formatter:redCellFormat">r3</th>
				<th data-options="field:'r4',width:40,align:'center', styler:redCellStyler, formatter:redCellFormat">r4</th>
				<th data-options="field:'r5',width:40,align:'center', styler:redCellStyler, formatter:redCellFormat">r5</th>
				<th data-options="field:'r6',width:40,align:'center', styler:redCellStyler, formatter:redCellFormat">r6</th>
				<th data-options="field:'b',width:40,align:'center', styler:blueCellStyler, formatter:redCellFormat">b</th>
				<th data-options="field:'r1_asc',width:40,align:'right',hidden:true">r1-s</th>
				<th data-options="field:'r2_asc',width:40,align:'right',hidden:true">r2-s</th>
				<th data-options="field:'r3_asc',width:40,align:'right',hidden:true">r3-s</th>
				<th data-options="field:'r4_asc',width:40,align:'right',hidden:true">r4-s</th>
				<th data-options="field:'r5_asc',width:40,align:'right',hidden:true">r5-s</th>
				<th data-options="field:'r6_asc',width:40,align:'right'">r6-s</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<input id="startDate" class="easyui-datebox" data-options="width:110"><span style="margin-left: 3px;font-weight: bold;">-</span>
		<input id="endDate" class="easyui-datebox" data-options="width:110"> 
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="refreshHandler()"></a> 
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true" onclick="refreshHandler()"></a>
		<a href="javascript:void(0);" class="easyui-linkbutton" title="asd" data-options="iconCls:'',text:'syns', plain:true" onclick="synCh"></a>
		<a href="javascript:void(0);" class="easyui-linkbutton" title="asd" data-options="iconCls:'',text:'sum', plain:true" onclick="sum()"></a>
	</div>
</body>
</html>