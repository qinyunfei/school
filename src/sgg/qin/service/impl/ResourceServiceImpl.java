package sgg.qin.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import sgg.qin.dao.sys.ResourceDao;
import sgg.qin.domain.sys.Resource;
import sgg.qin.service.ResourceService;
import sgg.qin.util.PageData;
import sgg.qin.util.Tools;

/**
 * 
 * @Description: 资源管理接口实现类
 * @author: Qin YunFei
 * @date: 2017年9月21日 下午2:24:44
 * @version V1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao dao;

	@Override
	public List<String> findPermissions(String role) throws Exception {
		// TODO Auto-generated method stub
		if (Tools.isEmpty(role)) {
			return null;
		}
		return (List<String>) dao.findPermissions( role);
	}

	@Override
	public List<Resource> listSubResourceById(int id) throws Exception {
		// TODO Auto-generated method stub
		return (List<Resource>) dao.listSubResourceById( id);
	}

	@Override
	public List<String> findPermissionsByUserName(String username) throws Exception {
		// TODO Auto-generated method stub
		if (Tools.isEmpty(username)) {
			return null;
		}
		return (List<String>) dao.findPermissionsByUserName(username);
	}

	@Override
	public List<Resource> findMenus(String username) throws Exception {
		// TODO Auto-generated method stub
		// 获取用户权限
		List<String> Permissions = this.findPermissionsByUserName(username);
		// 系统资源
		List<Resource> listAllResource = dao.listSubResourceById(0);
		return checkMenus(Permissions,listAllResource);
	}
	
	//根据权限和资源获取有权限的菜单资源(递归处理)
	private  List<Resource> checkMenus(List<String> Permissions,List<Resource> listAllResource){
		List<Resource> menus = new ArrayList<Resource>();
		for (Resource resource : listAllResource) {
			if (resource.getType()!=Resource.ResourceType.menu) {
				continue;
			}
			if (!hasPermission(Permissions,resource.getPermission())) {
				continue;
			}
			if (resource.getChildren()!=null) {
				resource.setChildren(checkMenus(Permissions,resource.getChildren()));
			}
			menus.add(resource);
		}
		
		return menus;
	}
	

	@Override
	public void insertResource(Resource resource) throws Exception {
		// TODO Auto-generated method stub
		dao.insertResource(resource);
	}

	@Override
	public void updateResource(Resource resource) throws Exception {
		// TODO Auto-generated method stub
		dao.updateResource(resource);
	}

	@Override
	public void deleteResourceById(int id) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteResourceById(id);
		dao.deleteRoleResourceByResourceId(id);
		
	}

	@Override
	public List<Map<String, Object>> findResourceById(String role) throws Exception {
		// TODO Auto-generated method stub
		 List<String> permissions = this.findPermissions(role);
		//获取所有系统资源
		List<Resource> listAllResource = dao.listSubResourceById(0);
		return checkResource(permissions,listAllResource);
	}
	
	//根据权限和资源获取有权限的菜单资源(递归处理)
		private  List<Map<String, Object>> checkResource(List<String> Permissions,List<Resource> listAllResource){
			List<Map<String, Object>> resourceList = new ArrayList<Map<String, Object>>();
			
			for (Resource resource : listAllResource) {
				Map<String, Object> map=new HashMap<>();
				map.put("id", resource.getId());
				map.put("text", resource.getName());
				if (hasPermission(Permissions,resource.getPermission())) {
					map.put("checked", true);
					map.put("ischecked", true);
				}else {
					map.put("checked", true);
					map.put("ischecked", false);
				}
				if (resource.getChildren()!=null) {
					map.put("children", checkResource(Permissions,resource.getChildren()));
				}
				resourceList.add(map);
			}
			
			return resourceList;
		}
		
		//判断是否具有该资源的权限22
		private boolean hasPermission(List<String> permissions, String permission_u) {
			if (Tools.isEmpty(permission_u)) {
				return true;
			}
			for (String permission : permissions) {
				WildcardPermission p1 = new WildcardPermission(permission);
				WildcardPermission p2 = new WildcardPermission(permission_u);
				if (p1.implies(p2) || p2.implies(p1)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public void insertRoleResourceAndRole(int roleId,List<Map<String, Object>> list) throws Exception {
			// TODO Auto-generated method stub
			dao.deleteRoleResourceByRoleId(roleId);
			if (list.size()>0) {
			dao.insertRoleResourceAndRole(list);

			}
		}

		@Override
		public void deleteRoleResourceByRoleId(int roleId) throws Exception {
			// TODO Auto-generated method stub
			dao.deleteRoleResourceByRoleId(roleId);
		}

}
