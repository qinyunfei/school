<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<base href="${pageContext.request.contextPath}/school">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- 引入JQuery -->
<script type="text/javascript" src="resource/easyui/jquery.min.js"></script>
<!-- 引入EasyUI -->
<script type="text/javascript"
	src="resource/easyui/jquery.easyui.min.js"></script>
<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
<script type="text/javascript"
	src="resource/easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css"
	href="resource/easyui/demo/demo.css">
<!-- 引入EasyUI的样式文件-->
<link rel="stylesheet" href="resource/easyui/themes/default/easyui.css"
	type="text/css" />
<!-- 引入EasyUI的图标样式文件-->
<link rel="stylesheet" href="resource/easyui/themes/icon.css"
	type="text/css" />
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	color: #666;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>


</head>

<body>
	<table id="dg" class="easyui-datagrid"
		data-options="
															url:'${pageContext.request.contextPath}/targumentResolver/dept',
															fitColumns:true,
															idField:'id',
															treeField:'name',
															rownumbers:true,
															fit : true,
															fitColumn : false,
															toolbar:'#toolbar',
															pagination:true,
															striped:true,
															singleSelect:true,
															queryParams: {
																		_method: 'get'
																		}
		">
		<thead>
			<tr>
				<th data-options="field:'dname',width:50">部门名称</th>
				<th data-options="field:'creatData',width:50">部门创建时间</th>
			</tr>
		</thead>
	</table>

</body>


</html>