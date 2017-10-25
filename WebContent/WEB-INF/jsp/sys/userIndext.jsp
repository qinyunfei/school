<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
	
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
//添加一个对象
function newUser() {
	//打开弹窗并设置窗口名称
	$('#dlg').dialog('open');
	//清空from表单
	$('#fm').form('clear');
}

//修改用户角色
function editUserRoler() {
	//获取选中对象
	var row = treegrid('getSelected');
	//判断对象是否为空
	if (row) {
		//打开弹窗并
		$('#dlgrole').dialog('open');
		//设置窗口内容
		$('#fmrole').form('load', row);

	}

}

//修改用户角色
function editPassword() {
	
	//获取选中对象
	var row = treegrid('getSelected');
	//判断对象是否为空
	if (row) {
		//打开弹窗并
		$('#dlgpassword').dialog('open');
		//设置窗口内容
		$('#fmpassword').form('load', row);

	}

}


//提交from表单
function subfrom(str) {
	$(str+'').form('submit', {
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
				$('#dlgpassword').dialog('close'); // close the dialog
				$('#dlgrole').dialog('close'); // close the dialog
				$('#fmpassword').form('clear');
				$.messager.alert('My Title', result.msg, 'info');
				$('#dg').datagrid('reload'); // reload the user data

			} else {
				$.messager.show({
					title : 'Error',
					msg : result.msg
				});
			}
		}
	});
}



function removeUser() {
	//获取选中对象
	var row = treegrid('getSelected');
	//判断对象是否为空
	if (row) {
		//提示确认删除
		$.messager.confirm(
						'Confirm',
						'Are you sure you want to remove this Role?',
						function(r) {
							if (r) {
								//ajxa异步删除
								$.post(
										'${pageContext.request.contextPath}/user/deleteUserById',
										{id : row.id},
										function(result) {
											if (result.success) {
												$.messager.alert('My Title',
																 '删除成功!',
																 'info');
												$('#dg').datagrid('reload'); // reload the user data
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
		<table id="dg" class="easyui-datagrid" data-options="
															url:'${pageContext.request.contextPath}/user/queryUserlistPage',
															fitColumns:true,
															idField:'id',
															treeField:'name',
															rownumbers:true,
															fit : true,
															fitColumn : false,
															toolbar:'#toolbar',
															pagination:true,
															striped:true,
															singleSelect:true
		">
						<thead>
							<tr>
								<th data-options="field:'username',width:50">用户名称</th>
								<th data-options="field:'description',width:50">用户角色</th>
								<th data-options="field:'locked',width:50">是否锁定</th>
							</tr>
						</thead>
					</table>
					
					
					
	<div id="toolbar">
	    <shiro:hasPermission name="user:create">
         	<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="newUser()">添加用户</a>             
        </shiro:hasPermission>
        
        <shiro:hasPermission name="user:update">
         	<a  class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editPassword()">修改密码</a>
			<a  class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editUserRoler()">修改用户角色</a>             
        </shiro:hasPermission>
        
        	<shiro:hasPermission name="user:delete">
        		<a  class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeUser()">删除用户</a>              
        </shiro:hasPermission>


	</div>
	

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 300px; padding: 10px 20px"
		data-options="closed:'true',buttons:'#bb'">
		<div class="ftitle">添加用户</div>
		<form id="fm" method="post" action="${pageContext.request.contextPath}/user/insertUserPageData">
		
			<div class="fitem">
				<label>用户名称:</label> <input name="username" class="easyui-textbox"
											data-options="min:0,precision:0,required:true">
			</div>
			<div class="fitem">
				<label>用户密码:</label> <input name="password" class="easyui-textbox"
											data-options="min:0,precision:0,required:true">
			</div>
			<div class="fitem">
				<label>用户是否锁定:</label> 			<select id="cc" class="easyui-combobox" name="locked" style="width:150px;">
        						<option value="true">true</option>
       						 <option value="false">false</option>
    				</select>
			</div>
			<div class="fitem">
				<label>用户角色:</label>  <input id="cc" class="easyui-combobox" name="rolesid"
        									data-options="valueField:'id',textField:'description',url:'${pageContext.request.contextPath}/role/queryRoleAll'">
			</div>
		
		</form>
	</div>
	
	
	<div id="dlgrole" class="easyui-dialog"
		style="width: 400px; height: 180px; padding: 10px 20px"
		data-options="closed:'true',buttons:'#bb_role'">
		<div class="ftitle">修改用户角色</div>
		<form id="fmrole" method="post" action="user/updateUserRolePageData">
			<div class="fitem">
				<label>用户角色:</label>  <input id="cc" class="easyui-combobox" name="rolesid"
        									data-options="valueField:'id',textField:'description',url:'${pageContext.request.contextPath}/role/queryRoleAll'">
			</div>
			 <input name="id" type="hidden">
			
			
		</form>
	</div>
	
		<div id="dlgpassword" class="easyui-dialog"
		style="width: 400px; height: 300px; padding: 10px 20px"
		data-options="closed:'true',buttons:'#b_passwod'">
		<div class="ftitle">修改密码</div>
		<form id="fmpassword" method="post" action="user/updateUserPasswordPageData">
			<div class="fitem">
				<label>密码:</label> <input name="password" class="easyui-textbox"
											data-options="min:0,precision:0,required:true">
			</div>
			
			<div class="fitem">
				<label>新密码:</label> <input name="newpassword" class="easyui-textbox"
											data-options="min:0,precision:0,required:true">
			</div>
			<input name="salt" type="hidden">	
			 <input name="username" type="hidden">
			
		</form>
	</div>
	
	
	<div id="bb">
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-ok'" onclick="subfrom('#fm')">保存</a>
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="javascript: $('#dlg').dialog('close')">关闭</a>
	</div>

	<div id="b_passwod">
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-ok'" onclick="subfrom('#fmpassword')">保存</a>
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="javascript: $('#dlgpassword').dialog('close')">关闭</a>
	</div>
	
	<div id="bb_role">
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-ok'" onclick="subfrom('#fmrole')">保存</a>
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="javascript: $('#dlgrole').dialog('close')">关闭</a>
	</div>
</body>


</html>