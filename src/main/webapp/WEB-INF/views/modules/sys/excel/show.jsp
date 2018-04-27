<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>显示</title>
    <meta name="decorator" content="default"/>
    <style>
        table {
            width: 55%;
            margin: 0px auto;
            margin-top: 40px;
        }

        td {
            width: 60px;
            height: 40px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="layui-tab">
    <ul class="layui-tab-title">
        <li><a href="${ctx}/sys/excel/">Excel列表</a></li>
        <li class="layui-this"><a href="${ctx}/sys/excel/form?id=${excel.id}">Excel<shiro:hasPermission
                name="sys:excel:edit">${not empty excel.id?'添加':'修改'}</shiro:hasPermission><shiro:lacksPermission
                name="sys:excel:edit">查看</shiro:lacksPermission></a></li>
    </ul>
</div>
<br/>
<div class="layui-form-item">
    <div class="layui-input-block">
        <%--这里是从数据库读取的信息--%>
        <h1>我是show页面</h1>
        <% Object test =request.getAttribute("excel");
            String text = test.toString();
             String[] split = text.split(",json=|,type=");
             text = split[1];
            text = text.replace("{\"json\":\"","");
            text =text.replace("\"}","");
            text = text.replace("\\","");
            text = text.replace("nnnn","");
            out.print(text);
            %>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="layui-btn" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                    <input id="btnCancel" class="layui-btn layui-btn-normal" type="button" value="返 回" onclick="history.go(-1)"/>
                </div>
            </div>
    </div>
</div>
<script>
    <%--console.log('${excel}');--%>
    console.log("helloworld");
    console.log('<%=test%>')
</script>
</body>
</html>
