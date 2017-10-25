package sgg.qin.web.controller.sys;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sgg.qin.domain.sys.Resource;
import sgg.qin.service.ResourceService;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;

@Controller
@RequestMapping("/resource")
public class ResourceAction extends BaseController{
	//@Qualifier("resourceService") 别名设置
	@Autowired
	private ResourceService resourceService;

	// ajax请求菜单资源
	@RequestMapping("/menus")
	@ResponseBody
	public List<Resource> menus() throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		String principal = (String) currentUser.getPrincipal();
		List<Resource> menus = resourceService.findMenus(principal);
		return menus;
	}

	// 获取所有资源
	@RequestMapping("/allresource")
	@ResponseBody
	public List<Resource> allresource() throws Exception {
		List<Resource> menus = resourceService.listSubResourceById(0);
		return menus;
	}

	// 添加资源
	@RequestMapping("/insertResource")
	@ResponseBody
	public PageData<String, Object> insertResource(Resource resource) throws Exception {
		resource.setIcon("menu-icon glyphicon glyphicon-leaf");
		resourceService.insertResource(resource);
		return this.getMsgPageData(true, "添加成功");
	}
	
	//修改资源
	@RequestMapping("/updateResource")
	@ResponseBody
	public PageData<String, Object> updateResource(Resource resource) throws Exception {
		resource.setIcon("menu-icon glyphicon glyphicon-leaf");
		System.out.println(resource);
		resourceService.updateResource(resource);
		return this.getMsgPageData(true, "修改成功");
	}
	//根据ID删除资源
	@RequestMapping("/deleteResourceById")
	@ResponseBody
	public PageData<String, Object> deleteResourceById(int id) throws Exception {
		resourceService.deleteResourceById(id);
		return this.getMsgPageData(true, "删除成功");
	}
}
