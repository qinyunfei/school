package sgg.qin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sgg.qin.domain.Page;
import sgg.qin.domain.sys.Role;
import sgg.qin.util.PageData;

/**
 * 
 * @Description: 角色管理接口（service）
 * @author: Qin YunFei
 * @date: 2017年9月27日 下午8:38:40
 * @version V1.0
 */
public interface RoleService {
	
	/**
	 * 
	 * Description :		分页获取角色列表	<br>
	 * PageDataKeys :					<br>
	 * return : List<PageData>
	 */
	Page queryRolelistPage(Page page) throws Exception;

	/**
	 * 
	 * Description : 添加角色 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	void insertRole(Role role) throws Exception;
	
	/**
	 * 
	 * Description : 添加用户角色关联关系 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	void insertUserRole(int userId,int roleId) throws Exception;
	
	
	/**
	 * 
	 * Description :	修改角色				<br>
	 * PageDataKeys :					<br>
	 * return : void
	 */
	void updateRole(Role role) throws Exception;
	
	/**
	 * 
	 * Description :根据用户id修改用户角色关联关系				<br>
	 * PageDataKeys :					<br>
	 * return : void
	 */
	void updateUserRoleByUserId(int userId,int roleId) throws Exception;
	
	/**
	 * 
	 * Description :	删除角色					<br>
	 * PageDataKeys :					<br>
	 * return : void
	 */
	void deleteRoleById(int id) throws Exception;
	
	
	/**
	 * 
	 * Description :	根据用户id删除用户角色关联关系				<br>
	 * PageDataKeys :					<br>
	 * return : void
	 */
	void deleteUserRoleByUserId(int userId) throws Exception;
	
}
