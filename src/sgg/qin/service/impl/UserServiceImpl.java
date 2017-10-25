package sgg.qin.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import sgg.qin.dao.sys.RoleDao;
import sgg.qin.dao.sys.UserDao;
import sgg.qin.domain.Page;
import sgg.qin.domain.sys.User;
import sgg.qin.framework.shiro.PasswordHelper;
import sgg.qin.service.RoleService;
import sgg.qin.service.UserService;
import sgg.qin.util.PageData;
import sgg.qin.util.Tools;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao dao;
	@Autowired
	private PasswordHelper passwordHelper;
	@Autowired
	private RoleDao roleDao;
	

	@Override
	//@sgg.qin.framework.annotation.CacheEvict(key ="sgg.qin.service.impl.UserServiceImpl.queryUserlistPage",fun=true)//我自己的注解 清空缓存
    //@CacheEvict(value="myCache",allEntries=true)//spring的注解 表示要清空的缓存
	public void insertUserPd(PageData<String,Object> pageeData) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) pageeData.get("user");
		passwordHelper.encryptPassword(user);
		pageeData.put("user", user);
		dao.insertUserpd(pageeData);
		roleDao.insertUserRole(user.getId(), (int)pageeData.get("rolesid"));	
	}

	@Override
	public void updateUser(PageData<String,Object> pd) throws Exception {
		// TODO Auto-generated method stub
		User user = (User) pd.get("user");
		passwordHelper.encryptPassword(user);
		pd.put("user", user);
		dao.updateUser(pd);
	}

	@Override
	public void deleteUser(PageData<String,Object> pd) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteUser(pd);
		roleDao.deleteUserRoleByUserId((int)pd.get("id"));
		
	}

	@Override
	public void changePassword(PageData<String,Object> pd) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PageData<String, Object>> findAll() throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData<String, Object>>) dao.findByUserConditions();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageData<String,Object> findByUserId(PageData<String,Object> pd) throws Exception {
		// TODO Auto-generated method stub
		if (pd == null || pd.get("id")==null) {
			throw new NullPointerException("id 不能为空");
		}
		return (PageData<String, Object>) dao.findByUserConditions(pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageData<String, Object> findByUserName(PageData<String, Object> pd) throws Exception {
		// TODO Auto-generated method stub
		if (pd == null || Tools.isEmpty((String) pd.get("username"))) {
			throw new NullPointerException("username 不能为空");
		}
		return (PageData<String, Object>) dao.findByUserConditions(pd);
	}

	@SuppressWarnings("unchecked")
	@Override
	//@Cacheable(value="findRoles",key="#pd.hashCode()") 
	public Set<String> findRoles(PageData<String,Object> pd) throws Exception {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>((Collection<? extends String>) dao.findRoles(pd));
		return set;
	}

	@Override
	//@sgg.qin.framework.annotation.Cacheable(fieldKey = "#page")//我自己的注解 注意导包
    //@Cacheable(value="myCache",key="#page.hashCode()") //spring的cach 这里需要注意，要实现缓存的实体必须要序列化
	public Page queryUserlistPage(Page page) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("查询数据库");
			List<PageData<String,Object>> list = dao.queryUserlistPage(page);
			page.setRetlist(list);
		return page;
	}

	@Override
	public void updateUserPassword(PageData<String, Object> pageData) throws Exception {
		// TODO Auto-generated method stub
		//获取加密后的密码
		String password = passwordHelper.encryptPassword((String)pageData.get("username"), (String)pageData.get("password"), (String)pageData.get("salt"));
		PageData<String,Object> data = dao.findByUserConditions(pageData);
		if (password.equals(dao.findByUserConditions(pageData).get("password"))) {
			String[] strings = passwordHelper.encryptPassword((String)pageData.get("username"), (String)pageData.get("newpassword"));
			pageData.put("newpassword", strings[0]);
			pageData.put("salt", strings[1]);
			System.out.println(pageData);
			dao.updateUserPassword(pageData);
		}else {
			throw new Exception("#密码错误");
		}

	}

	

}
