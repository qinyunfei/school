package sgg.qin.web.controller.sys;



import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import sgg.qin.domain.Page;
import sgg.qin.domain.sys.User;
import sgg.qin.service.RoleService;
import sgg.qin.service.UserService;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;
/**
 * 
 * @Description: 用户管理Action
 * @author: Qin YunFei
 * @date: 2017年9月29日 下午6:34:11
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	//分页数据
	@RequiresPermissions("user:menu")
	@RequestMapping("queryUserlistPage")
	@ResponseBody
	public PageData<String, Object> queryRolelistPage(@RequestParam("rows") Integer showCount, @RequestParam("page") Integer currentPage)throws Exception{
		Page page = userService.queryUserlistPage(this.getPage());
		return this.getPageMsgPageData(page);
	}
	
	
	//添加用户
	@RequiresPermissions("user:create")
	@RequestMapping("insertUserPageData")
	@ResponseBody
	public PageData<String, Object> insertUserPageData(User user,Integer rolesid)throws Exception{
		userService.insertUserPd(this.getPageData());
		return this.getMsgPageData(true, "添加成功");
	}
	
	
	//修改密码
	@RequiresPermissions("user:update")
	@RequestMapping("updateUserPasswordPageData")
	@ResponseBody
	public PageData<String, Object> updateUserPasswordPageData(String username,String password,String newpassword,String salt)throws Exception{
		
		userService.updateUserPassword(this.getPageData());
		
		return this.getMsgPageData(true, "修改成功");
	}
		
	//修改用户角色
	@RequiresPermissions("user:update")
	@RequestMapping("updateUserRolePageData")
	@ResponseBody
	public PageData<String, Object> updateUserRolePageData(@RequestParam("rolesid")Integer rolesId,@RequestParam("id")Integer userId)throws Exception{
		roleService.updateUserRoleByUserId(userId, rolesId);
		return this.getMsgPageData(true, "修改成功");
	}
	
	//根据用户id删除用户
	@RequiresPermissions("user:delete")
	@RequestMapping("deleteUserById")
	@ResponseBody
	public PageData<String, Object> deleteUserById(Integer id)throws Exception{
		userService.deleteUser(this.getPageData());
		return this.getMsgPageData(true, "删除成功");
	}
	
}
