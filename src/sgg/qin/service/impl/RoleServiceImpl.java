package sgg.qin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sgg.qin.dao.sys.ResourceDao;
import sgg.qin.dao.sys.RoleDao;
import sgg.qin.domain.Page;
import sgg.qin.domain.sys.Role;
import sgg.qin.service.RoleService;
import sgg.qin.util.PageData;
/**
 * 
 * @Description: 角色管理接口实现类
 * @author: Qin YunFei
 * @date: 2017年9月27日 下午8:58:23
 * @version V1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private ResourceDao resourceDao;
	


	@Override
	public Page queryRolelistPage(Page page) throws Exception{
		// TODO Auto-generated method stub
		List<PageData<String,Object>> list = roleDao.queryRolelistPage(page);
		page.setRetlist(list);
		return page;
	}

	@Override
	public void insertRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		roleDao.insertRole(role);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		// TODO Auto-generated method stub
		roleDao.updateRole(role);
	}

	@Override
	public void deleteRoleById(int id) throws Exception {
		// TODO Auto-generated method stub
		roleDao.deleteRoleById(id);
		resourceDao.deleteRoleResourceByRoleId(id);
		
	}

	@Override
	public void insertUserRole(int userId, int roleId) throws Exception {
		// TODO Auto-generated method stub
		roleDao.insertUserRole(userId, roleId);
	}

	@Override
	public void deleteUserRoleByUserId(int userId) throws Exception {
		// TODO Auto-generated method stub
		roleDao.deleteUserRoleByUserId(userId);
	}

	@Override
	public void updateUserRoleByUserId(int userId, int roleId) throws Exception {
		// TODO Auto-generated method stub
		roleDao.updateUserRoleByUserId(userId, roleId);
	}

}
