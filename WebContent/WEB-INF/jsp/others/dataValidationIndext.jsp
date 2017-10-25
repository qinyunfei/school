<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

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
<script type="text/javascript">
	function Mydatagrid() {
		var str = '(';
		for (var i = 0; i < arguments.length; i++) {
			if (typeof arguments[i] == "string") {
				str += "'" + arguments[i] + "',"
			} else if (typeof arguments[i] == "object") {

				str += JSON.stringify(arguments[i]) + ","
			} else {
				str += +arguments[i] + ","
			}
		}
		str = (str.substring(str.length - 1) == ',') ? str.substring(0,
				str.length - 1) : str;
		str += ')'
		str = "$('#dg').datagrid" + str;
		var retu = eval(str);
		return retu;
	}

	//添加一个对象
	function addDept() {
		//打开弹窗并设置窗口名称
		$('#dlg').dialog('open').dialog('setTitle', 'New Dept');
		//清空from表单
		$('#fm').form('clear').form('load', {
			_method : 'post'
		});
	}

	//提交from表单
	function subfrom() {
		var row = Mydatagrid('getSelected');

		$('#fm').form('submit', {
			//在提交之前触发，返回false可以终止提交 
			onSubmit : function(param) {
				//判断是否需要附带参数id
				if (row != null && row._method == "put") {
					param.deptno = row.deptno;
				}
				//检查表单是否有效
				return $(this).form('validate');
			},
			//当表单提交成功时触发
			success : function(result) {
				//将json字符串转化为js对象
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					$.messager.alert('My Title', result.msg, 'info');
					Mydatagrid('reload'); // reload the user data
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}

	function delectDept() {
		//获取选中对象
		var row = Mydatagrid('getSelected');
		//判断对象是否为空
		if (row) {
			//提示确认删除
			$.messager
					.confirm(
							'Confirm',
							'Are you sure you want to remove this Dept?',
							function(r) {
								if (r) {
									//ajxa异步删除
									$
											.post(
													'${pageContext.request.contextPath}/deptDataValidation/dept',
													{
														deptno : row.deptno,
														_method : 'delete'
													},
													function(result) {
														if (result.success) {
															$.messager.alert(
																	'My Title',
																	'删除成功!',
																	'info');
															Mydatagrid('reload'); // reload the user data
														} else {
															$.messager
																	.show({ // show error message
																		title : 'Error',
																		msg : result.msg
																	});
														}
													}, 'json');
								}
							});
		}
	}
</script>

</head>

<body>
	<table id="dg" class="easyui-datagrid"
		data-options="
															url:'${pageContext.request.contextPath}/restful/dept',
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



	<div id="toolbar">
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" onclick="addDept()">添加部门</a>
	</div>


	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 200px; padding: 10px 20px"
		data-options="closed:'true',buttons:'#bb_role'">
		<form id="fm" method="post"
			action="${pageContext.request.contextPath}/deptDataValidation/dept">
			<input id="method_id" type="hidden" name="_method">
			<div class="fitem">
				<label>部门名称:</label> <input name="dname" class="easyui-textbox"
					data-options="min:0,precision:0,required:true"><br/>
			</div>
			<div class="fitem">
				<label>部门创建时间:</label> <input name="creatData" type="text"
					class="easyui-datebox"
					data-options="min:0,precision:0,required:true">
			</div>
		</form>
	</div>


	<div id="bb_role">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
			onclick="subfrom()">保存</a> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel'"
			onclick="javascript: $('#dlg').dialog('close')">关闭</a>
	</div>
</body>


</html>