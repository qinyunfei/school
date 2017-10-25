<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<base href="${pageContext.request.contextPath}/school">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- 引入JQuery -->
<script type="text/javascript" src="resource/easyui/jquery.min.js"></script>
<!-- 引入EasyUI -->
<script type="text/javascript" src="resource/easyui/jquery.easyui.min.js"></script>
<!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
<script type="text/javascript" src="resource/easyui/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" type="text/css" href="resource/easyui/demo/demo.css">
<!-- 引入EasyUI的样式文件-->
<link rel="stylesheet" href="resource/easyui/themes/default/easyui.css"
	type="text/css" />
<!-- 引入EasyUI的图标样式文件-->
<link rel="stylesheet" href="resource/easyui/themes/icon.css" type="text/css" />
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
	
	
	function treegrid() {
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
		str = "$('#dg').treegrid" + str;
		var retu = eval(str);
		return retu;
	}
	
	
	
	//添加同级资源
	function newUser() {
		//获取选中对象
		var row = treegrid('getSelected');
		//获取父对象
		var Parent=treegrid("getParent",row.id);
		//判断对象是否为空
		if (row) {
			//打开弹窗并设置窗口名称
			$('#dlg').dialog('open').dialog('setTitle', '添加资源');
			//清空from表单
			$('#fm').form('clear');

			if (Parent!=null) {
				$("#span").text(Parent.name);
				$('#parentId').val(Parent.id);
			}else{
				$("#span").text("无父资源");
				$('#parentId').val('0');
			}
			$('#rid').val('0');
			
		}
		//设置url地址
		url = '${pageContext.request.contextPath}/resource/insertResource';
	}
	//添加子资源
	function newSubUser() {
		//获取选中对象
		var row = treegrid('getSelected');

		//判断对象是否为空
		if (row) {
			//打开弹窗并设置窗口名称
			$('#dlg').dialog('open').dialog('setTitle', '添加子资源');
			//清空from表单
			$('#fm').form('clear');

			$("#span").text(row.name);
			$('#parentId').val(row.id);
			$('#rid').val('0');
		
			
		}
		//设置url地址
		url = '${pageContext.request.contextPath}/resource/insertResource';
	}
	//修改对象
	function editUser() {

		//获取选中对象
		var row = treegrid('getSelected');
		//获取父对象
		var Parent=treegrid("getParent",row.id);
		
		if (Parent!=null) {
			$("#span").text(Parent.name);
		}else{
			$("#span").text("无父资源");
		}
		//判断对象是否为空
		if (row) {
			//打开弹窗并设置窗口名称
			$('#dlg').dialog('open').dialog('setTitle', '修改资源');
			//设置窗口内容
			$('#fm').form('load', row);
			//设置url地址
			url = '${pageContext.request.contextPath}/resource/updateResource';
		}
	}
	
	
	
	
	//提交from表单
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			//在提交之前触发，返回false可以终止提交 
			onSubmit : function() {
				//检查表单是否有效
				return $(this).form('validate');
			},
			//当表单提交成功时触发
			success : function(result) {
				//将json字符串转化为js对象
				var result = eval('(' + result + ')');
				console.log(result);
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog

					$.messager.alert('My Title', result.msg, 'info', function(r) {
					});
					$('#dg').treegrid('reload'); // reload the user data
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
	
	//删除对象
	function removeUser() {
		//获取选中对象
		var row = treegrid('getSelected');
		var sub= treegrid("getChildren",row.id);
		if (sub.length>0) {
			$.messager.alert('提醒','请先删除子资源');
			return;
		}
		
		//判断对象是否为空
		if (row) {
			//提示确认删除
			$.messager.confirm(
							'Confirm',
							'Are you sure you want to remove this resource?',
							function(r) {
								if (r) {
									//ajxa异步删除
									$.post(
													'${pageContext.request.contextPath}/resource/deleteResourceById',
													{
														id : row.id
													},
													function(result) {
														if (result.success) {
															$.messager.alert(
																			'My Title',
																			'删除成功!',
																			'info',
																			function(r) {
																			
																			});
															treegrid('reload'); // reload the user data
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
		<table id="dg" class="easyui-treegrid" data-options="
															url:'${pageContext.request.contextPath}/resource/allresource',
															fitColumns:true,
															idField:'id',
															treeField:'name',
															rownumbers:true,
															fit : true,
															fitColumn : false,
															toolbar:'#toolbar'
		">
						<thead>
							<tr>
								<th data-options="field:'name',width:50">资源名称</th>
								<th data-options="field:'type',width:50">资源类型</th>
								<th data-options="field:'url',width:50" >资源路径</th>
								<th data-options="field:'permission',width:50" >权限标识</th>
								<th data-options="field:'icon',width:50">图标</th>
							</tr>
						</thead>
					</table>
					
					
					
	<div id="toolbar">
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="newUser()">添加同级资源</a>
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="newSubUser()">添加子资源</a>
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editUser()">修改资源</a>
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeUser()">删除资源</a>

	</div>
	

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 300px; padding: 10px 20px"
		data-options="closed:'true',buttons:'#bb'">
		<div class="ftitle">父资源:<span id="span"></span></div>
		<form id="fm" method="post">
		
			<div class="fitem">
				<label>资源名称:</label> <input name="name" class="easyui-textbox"
					data-options="min:0,precision:0,required:true">
			</div>
			<div class="fitem">
				<label>资源类型:</label> 	
				<select id="cc" class="easyui-combobox" name="type" style="width:150px;">
        						<option value="menu">menu</option>
       						 <option value="button">button</option>
    				</select>
			</div>
			<div class="fitem">
				<label>资源路径:</label> <input name="url" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>权限标识:</label> <input name="permission" class="easyui-textbox">
			</div>
<!-- 			<div class="fitem">
				<label>图标:</label> <input name="icon" class="easyui-textbox">
			</div> -->
			<div class="fitem">
				<input id="parentId" name="parentId" type="hidden" >
				<input id="rid" name="id" type="hidden">
			</div>
			
		
		</form>
	</div>
	<div id="bb">
	<a class="easyui-linkbutton"  data-options="iconCls:'icon-ok'" onclick="saveUser()">保存</a>
	<a class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="javascript: $('#dlg').dialog('close')">关闭</a>
</div>
	
</body>


</html>