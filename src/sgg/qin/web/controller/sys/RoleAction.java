package sgg.qin.web.controller.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import sgg.qin.domain.Page;
import sgg.qin.domain.sys.Role;
import sgg.qin.service.ResourceService;
import sgg.qin.service.RoleService;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;
/**
 * 
 * @Description: 角色管理控制器
 * @author: Qin YunFei
 * @date: 2017年9月28日 上午9:51:51
 * @version V1.0
 */
@Controller
@RequestMapping("/role")
public class RoleAction extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	//分页数据
	@RequestMapping("queryRolelistPage")
	@ResponseBody
	public PageData<String, Object> queryRolelistPage(@RequestParam("rows") Integer showCount, @RequestParam("page") Integer currentPage)throws Exception{
		Page page = roleService.queryRolelistPage(this.getPage());
		return this.getPageMsgPageData(page);
	}
	
	//全部角色数据
	@RequestMapping("queryRoleAll")
	@ResponseBody
	public List queryRoleAll()throws Exception{
		Page page = roleService.queryRolelistPage(this.getPage());
		return  page.getRetlist();
	}
	
	//添加数据
	@RequestMapping("insertRole")
	@ResponseBody
	public PageData<String, Object> insertRole(Role role) throws Exception {
		roleService.insertRole(role);
		return this.getMsgPageData(true, "添加成功");
	}
	
	//修改数据
	@RequestMapping("updateRole")
	@ResponseBody
	public PageData<String, Object> updateRole(Role role) throws Exception {
		roleService.updateRole(role);
		return this.getMsgPageData(true, "修改成功");
	}
	
	//根据id删除数据
	@RequestMapping("deleteRoleById")
	@ResponseBody
	public PageData<String, Object> deleteRoleById(int id) throws Exception {
		roleService.deleteRoleById(id);
		return this.getMsgPageData(true, "删除成功");
	}
	
	//获取角色资源树菜单
	@RequestMapping("findResourceByRoleId")
	@ResponseBody
	public List<Map<String,Object>> findResourceByRoleId(String role) throws Exception {
		List<Map<String,Object>> list = resourceService.findResourceById(role);
		return list;
	}
	
	//修改角色资源关联关系
	@RequestMapping("updateRoleResourceByRoleId")
	@ResponseBody
	public PageData<String, Object> updateRoleResourceByRoleId(int roleId,String roleResourceIds) throws Exception {
		 Map<String,Object> map=new HashMap<>();
		 List<Map<String,Object>> list = (List<Map<String, Object>>) JSON.parseArray(roleResourceIds, map.getClass());
		 resourceService.insertRoleResourceAndRole(roleId,list);
		 return this.getMsgPageData(true, "修改成功");
	}
}
