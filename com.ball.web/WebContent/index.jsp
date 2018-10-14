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
<meta http-equiv="pragma" content="no-cache">
<title>ball</title>
<link rel="stylesheet" href="<%=basePath%>resource/layui/css/layui.css">
<script src="<%=basePath%>resource/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript">
	layui.use([ 'layer', 'form' ], function() {
		var layer = layui.layer, form = layui.form;
	});
</script>
</head>
<body style="margin: 0;">
	<div style="height: 80px; width: 100%; background: #2F4056; text-align: center;">
		<span style="color: white; line-height: 80px; font-size: 30px; font-family: '微软雅黑'; font-weight: bold;">Good Luck!</span>
	</div>
	<div style="padding: 80px 100px;">
		<a href="./ssq.jsp" class="layui-btn">SSQ</a>
	</div>
</body>
</html>