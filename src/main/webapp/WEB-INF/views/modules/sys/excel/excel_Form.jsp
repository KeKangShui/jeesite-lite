<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>Excel管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li><a href="${ctx}/sys/excel/">Excel列表</a></li>
        <li class="layui-this"><a href="${ctx}/sys/excel/form?id=${excel.id}">Excel<shiro:hasPermission name="sys:excel:edit">${not empty excel.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:excel:edit">查看</shiro:lacksPermission></a></li>
    </ul>
</div><br/>
<form:form id="inputForm" modelAttribute="excel" action="${ctx}/sys/excel/mytest.do" method="post" enctype="multipart/form-data" class="layui-form">
    <%--<form:hidden path="id"/>--%>
    <sys:message content="${message}"/>

        <h1>请上传文件</h1>
        <input type="file" name="file"><br><br>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <shiro:hasPermission name="sys:excel:edit"><input id="btnSubmit" class="layui-btn" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
            <input id="btnCancel" class="layui-btn layui-btn-normal" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </div>

</form:form>
</body>
</html>