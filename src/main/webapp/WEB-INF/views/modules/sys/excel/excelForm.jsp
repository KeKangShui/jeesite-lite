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
			<li class="layui-this"><a href="${ctx}/sys/excel/form?id=${excel.id}"></a>Excel添加</li>
		</ul>
	</div><br/>
	<form action="/a/sys/excel/mytest.do" method="post" enctype="multipart/form-data">
		<h1>请上传文件</h1>
		<input type="file" name="file"><br><br>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<shiro:hasPermission name="sys:excel:edit"><input id="btnSubmit" class="layui-btn" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
				<input id="btnCancel" class="layui-btn layui-btn-normal" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</div>
	</form>
</body>
</html>