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
	
	
	function mydg() {
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
	//修改角色
	function editRoler() {
		//获取选中对象
		var row = $('#dg').datagrid('getSelected');
		//判断对象是否为空
		if (row) {
			//打开弹窗并设置窗口名称
			$('#dlg').dialog('open')
					.dialog('setTitle', 'Edit Role');
			//设置窗口内容
			$('#fm').form('load', row);
			//设置url地址
			url = '${pageContext.request.contextPath}/role/updateRole';
		}
	}
	
	//添加一个对象
	function newRole() {
		//打开弹窗并设置窗口名称
		$('#dlg').dialog('open').dialog('setTitle', 'New Role');
		//清空from表单
		$('#fm').form('clear');
		//设置url地址
		url = '${pageContext.request.contextPath}/role/insertRole';
	}

	
	//角色权限弹窗
	function editRolerResource() {
	
		//获取选中对象
		var row = $('#dg').datagrid('getSelected');
		
		if (row) {
			$('#threedhk').dialog('open');
		    $('#tree').tree({
		        queryParams:{role:row.role}
		    });		    

		}
		
	
	}

	//提交from表单
	function submitRoleForm() {
		$('#fm').form('submit',{
								url : url,
								//在提交之前触发，返回false可以终止提交 
								onSubmit : function(param) {
									try {
										param.id=mydg('getSelected').id;
									} catch (e) {
										// TODO: handle exception
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
										$.messager.alert('My Title',
												result.msg, 'info',
												function(r) {
												});
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
	
	function removeRole() {
		//获取选中对象
		var row = mydg('getSelected');
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
											'${pageContext.request.contextPath}/role/deleteRoleById',
											{id : row.id},
											function(result) {
												if (result.success) {
													$.messager.alert('My Title',
																	 '删除成功!',
																	 'info',
																	function(r) {		
																			});
															mydg('reload'); // reload the user data
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
	
	function onLoadSuccess(node, data) {
		for ( var i in data) {
			if (!data[i].ischecked) {
				$("#tree").tree("uncheck",data[i].target)
			}   
			if (data[i].children.length>0) {
				onLoadSuccess(null,data[i].children)
			}	
		}
	}
	//修改角色资源关联关系
	function saveRoleResource() {
		var nodes = $('#tree').tree('getChecked');	// get checked nodes
		//获取选中对象
		var row = $('#dg').datagrid('getSelected');
		var list=[];
		var obj={}
		$.each(nodes,function(){
			list.push({roleId:row.id,resourceId:this.id});
		})
		
		$.post("role/updateRoleResourceByRoleId", {roleId:row.id,roleResourceIds:JSON.stringify(list)},
				function(result) {
			if (result.success) {
				$('#threedhk').dialog('close'); // close the dialog
				$.messager.alert('My Title',
						result.msg, 'info',
						function(r) {
						});
			} else {
				$.messager.show({
					title : 'Error',
					msg : result.msg
				});
			}
		});

	}
	//用户勾选复选框后 级联勾选父节点 级联清空子节点
	function onCheck(node, checked) {
     if(checked){
    		 try {
    			var fjd=$(this).tree("getParent",node.target);
    	 		$(this).tree("check",fjd.target);
		} catch (e) {
			// TODO: handle exception
		}
     }else{
    		 try {
    		  var zjd=$(this).tree("getChildren",node.target);
      	    $.each(zjd, function(){     
      	    	$('#tree').tree("uncheck",this.target);
      	     });   
		} catch (e) {
		}	

     }
	}

	
	</script>
	
	
	

</head>

<body>
		<table id="dg" class="easyui-datagrid" data-options="
															url:'${pageContext.request.contextPath}/role/queryRolelistPage',
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
								<th data-options="field:'role',width:50">角色名称</th>
								<th data-options="field:'description',width:50">角色描述</th>
							</tr>
						</thead>
					</table>
					
					
					
	<div id="toolbar">
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="newRole()">添加角色</a>
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editRolerResource()">角色资源权限</a>
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editRoler()">修改角色</a>
		<a  class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeRole()">删除角色</a>

	</div>
	

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 250px; padding: 10px 20px"
		data-options="closed:'true',buttons:'#bb'">
		<div class="ftitle">角色操作</div>
		<form id="fm" method="post">
		
			<div class="fitem">
				<label>角色名称:</label> <input name="role" class="easyui-textbox"
											data-options="min:0,precision:0,required:true">
			</div>
			<div class="fitem">
				<label>角色描述:</label> <input name="description" class="easyui-textbox"
											data-options="min:0,precision:0,required:true">
		</form>
	</div>
	<div id="bb">
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-ok'" onclick="submitRoleForm()">保存</a>
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="javascript: $('#dlg').dialog('close')">关闭</a>
	</div>

	 <div id="threedhk" class="easyui-dialog"  style="width:250px;height:400px;"
        data-options="title:'Edit RoleResource',closed:'true',buttons:'#threedhk_buttons'">
        <ul id="tree" data-options=" url:'role/findResourceByRoleId',checkbox:true,cascadeCheck:false,onLoadSuccess:onLoadSuccess,onCheck:onCheck"></ul>
    </div>
    
    	<div id="threedhk_buttons">
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-ok'" onclick="saveRoleResource()">保存</a>
		<a class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="javascript: $('#threedhk').dialog('close')">关闭</a>
	</div>
	
</body>


</html>