package sgg.qin.dao.sys;

import java.util.List;

import sgg.qin.domain.Page;
import sgg.qin.util.PageData;

/**
 * 
 * @Description: 用户管理接口（dao）
 * @author: Qin YunFei
 * @date: 2017年9月29日 下午6:35:23
 * @version V1.0
 */
public interface UserDao {
	
	
	void insertUserpd(PageData<String,Object> pd);
	
	void updateUser(PageData<String,Object> pd);
	
	void deleteUser(PageData<String,Object> pd);
	
	List<PageData<String, Object>> findByUserConditions();
	
	PageData<String, Object> findByUserConditions(PageData<String,Object> pd);
	
	List<String> findRoles(PageData<String,Object> pd);
	
	/**
	 * 
	 * Description : 分页获取用户列表 <br>
	 * PageDataKeys : <br>
	 * return : List<PageData>
	 */
	List<PageData<String, Object>> queryUserlistPage(Page page) throws Exception;
	
	/**
	 * 
	 * Description :	根据用户名修改用户密码				<br>
	 * PageDataKeys :password,salt,username			<br>
	 * return : void
	 */
	void updateUserPassword(PageData<String, Object> pageData)throws Exception;
}
