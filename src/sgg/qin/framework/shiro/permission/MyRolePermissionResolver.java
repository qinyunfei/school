package sgg.qin.framework.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;

import sgg.qin.service.ResourceService;
import sgg.qin.util.PageData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @Description: 根据角色获取对应的权限
 * @author: Qin YunFei
 * @date: 2017年10月9日 下午7:42:19
 * @version V1.0
 */
public class MyRolePermissionResolver implements RolePermissionResolver {

	@Autowired
	private ResourceService resourceService;

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		Collection<Permission> coll = new ArrayList();
		try {
			List<String> permissions = resourceService.findPermissions(roleString);
			for (String string : permissions) {
				coll.add((Permission) new WildcardPermission(string));
			}
			return coll;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
