<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Excel---管理</title>
	<meta name="decorator" content="default"/>
	<style>
		table{
			width: 55%;
			margin: 0px auto;
			margin-top: 40px;
		}
		td{
			width: 60px;
			height: 40px;
			text-align: center;
		}
	</style>
</head>
<body>
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li class="layui-this"><a href="${ctx}/sys/excel/list">Excel列表</a></li>
			<li><a href="${ctx}/sys/excel/form">Excel添加</a></li>
		</ul>
	</div><br/>
	${requestScope.test}

	<sys:message content="${message}"/>
	<script type="text/html" id="bar">
		<a href="javascript:void(0)" class="layui-btn layui-btn-sm" lay-event="edit"><i class="layui-icon">&#xe642;</i>修改</a>
		<a href="javascript:void(0)" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
		<a href="javascript:void(0)" class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe608;</i>添加键值</a>
	</script>
	<%--<script type="text/javascript">
	function typeFilter(type) {
		$('#type').val(type);
		layui.form.render();
		reloadTable();
	}
	$(document).ready(function() {
		var table = layui.table;
		//执行渲染
		table.render({
		    url: '${ctx}/sys/excel/data' //数据接口
		    ,cols: [[ //表头
		       {type: 'checkbox', fixed:'left'}
		      ,{field: 'value',title: '键值'}
		      ,{title: '标签', templet: function(d) {
		          return '<a href="${ctx}/sys/excel/form?id='+d.id+'" class="layui-table-link">'+d.label+'</a>'
		       }}
		      ,{title: '类型', templet: function(d) {
		          return '<a href="javascript:void(0)" class="layui-table-link" onclick="typeFilter(\''+d.type+'\')">'+d.type+'</a>'
		       }} 
		      ,{field: 'description', title: '描述'}
		      ,{field: 'sort', title: '排序'}
		      <shiro:hasPermission name="sys:excel:excel">
		      ,{fixed:'right', align:'center', width:300, title: '操作', toolbar:'#bar'}
		      </shiro:hasPermission>
		    ]]
		});
		//监听工具条
		table.on('tool', function(obj){
		  var data = obj.data; //获得当前行数据
		  var layEvent = obj.event; //获得 lay-event 对应的值
		  if(layEvent === 'edit'){ //修改
              location = '${ctx}/sys/excel/form?id='+data.id;
		  } else if(layEvent === 'del'){ //删除
			  confirmx('确认要删除该字典吗？', '${ctx}/sys/excel/delete?id='+data.id)
		  } else if(layEvent === 'add'){ //添加键值
              location = '${ctx}/sys/excel/form?description='+data.description+'&type='+data.type+'&sort='+(data.sort+10);
		  }
		});
		//批量删除
		$('#btnDelete').on('click', function(){
			batchDelete('${ctx}/sys/excel/batchDelete');
		});
	});
	</script--%>>
</body>
</html>