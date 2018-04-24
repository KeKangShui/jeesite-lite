<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Excel++管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading();
					form.submit();
				}
			});
		});
	</script>
</head>
<body>
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li><a href="${ctx}/sys/excel/list">Excel列表</a></li>
			<li class="layui-this"><a href="${ctx}/sys/excel/form"></a>Excel添加</li>
		</ul>
	</div><br/>
	<form action="/a/sys/excel/mytest.do" method="post" enctype="multipart/form-data">
		<h1>请上传文件</h1>
		<input type="file" name="file"><br><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>