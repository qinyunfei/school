package sgg.qin.service;



import java.util.List;
import java.util.Set;

import sgg.qin.domain.Page;
import sgg.qin.util.PageData;

/**
 * 
 * @Description: 用户管理接口（UserService）
 * @author: Qin YunFei
 * @date: 2017年9月29日 下午6:42:05
 * @version V1.0
 */
public interface UserService {
	
	/**
	 * 
	 * Description : 分页获取用户列表 <br>
	 * PageDataKeys : <br>
	 * return : Page
	 */
	Page queryUserlistPage(Page page) throws Exception;

    /**
     * 创建用户
     * @param PageData :user
     * @throws Exception 
     */
    public void insertUserPd(PageData<String,Object> pd) throws Exception;
    
    /**
     * 更新用户 :user
     * @param PageData:user
     * @throws Exception 
     */
    public void updateUser(PageData<String,Object> pd) throws Exception;
    /**
     * 删除用户
     * @param PageData: id
     * @throws Exception 
     */
    public void deleteUser(PageData<String,Object> pd) throws Exception;

    /**
     * 修改密码
     * @param PageData:userId,newPassword
     * 
     */
    public void changePassword(PageData<String,Object> pd);
    

    /**
     * 获取所有用户
     * @param 
     * @throws Exception 
     * 
     */
    List<PageData<String,Object>> findAll() throws Exception;
    
    /**
     * 根据用户id查找用户
     * @param PageData:id
     * @throws Exception 
     * 
     */
    PageData<String,Object> findByUserId(PageData<String,Object> pd) throws Exception;
    
    /**
     * 根据用户名查找用户
     * @param PageData :username
     * @return
     * @throws Exception 
     */
     PageData<String,Object> findByUserName(PageData<String,Object>  pd) throws Exception;

    /**
     * 根据用户名查找其角色
     * @param PageData :username
     * @return
     * @throws Exception 
     */
    public Set<String> findRoles(PageData<String,Object> pd) throws Exception;
    
    
	/**
	 * 
	 * Description :	根据用户名修改用户密码				<br>
	 * PageDataKeys :password,salt,username			<br>
	 * return : void
	 */
	void updateUserPassword(PageData<String, Object> pageData)throws Exception;

  

}
