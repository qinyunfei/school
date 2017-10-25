package sgg.qin.dao.sys;

import java.util.List;
import java.util.Map;

import sgg.qin.domain.sys.Resource;
import sgg.qin.util.PageData;
/**
 * 
 * @Description: 资源管理接口(DAO)
 * @author: Qin YunFei
 * @date: 2017年9月27日 下午3:13:06
 * @version V1.0
 */
public interface ResourceDao {

	/**
	 * 
	 * Description : 根据角色名获取权限集合 <br>
	 * PageDataKeys :role <br>
	 * return : List<String>
	 */
	List<String> findPermissions(String role)throws Exception;
	
	/**
	 * 
	 * Description : 根据用户名获取权限集合 <br>
	 * PageDataKeys : <br>
	 * return : List<String>
	 */
	List<String> findPermissionsByUserName(String username)throws Exception;

	/**
	 * 
	 * Description : 通过资源ID获取其子一级资源 <br>
	 * PageDataKeys : id <br>
	 * return : List<Resource>
	 */
	List<Resource> listSubResourceById(int id)throws Exception;

	/**
	 * 
	 * Description :添加资源 <br>
	 * PageDataKeys : resource<br>
	 * return : void
	 */
	void insertResource(Resource resource) throws Exception;

	/**
	 * 
	 * Description :更新资源 <br>
	 * PageDataKeys : resource<br>
	 * return : void
	 */
	void updateResource(Resource resource) throws Exception;
	
	
	 /**
	  * 
	  * Description :根据id删除资源		<br>
	  * PageDataKeys :					<br>
	  * return : void
	  */
	 void deleteResourceById(int id) throws Exception;
	 
	 /**
	  * 
	  * Description :根据角色id删除权限关联关系	<br>
	  * PageDataKeys :roleId				<br>
	  * return : void
	  */
	 void deleteRoleResourceByRoleId(int roleId) throws Exception;
	 
	 /**
	  * 
	  * Description :根据权限id删除权限关联关系	<br>
	  * PageDataKeys :roleId				<br>
	  * return : void
	  */
	 void deleteRoleResourceByResourceId(int resourceId) throws Exception;
	 
	 
	 /**
	  * 
	  * Description :添加角色权限管理关系	<br>
	  * PageDataKeys :roleId,resourceId	<br>
	  * return : void
	  */
	 void insertRoleResourceAndRole(List<Map<String, Object>> list) throws Exception;
	 
	
	 
}
