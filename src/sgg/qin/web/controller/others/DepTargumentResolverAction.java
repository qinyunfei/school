package sgg.qin.web.controller.others;


import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;
import sgg.qin.framework.annotation.UserName;
import sgg.qin.service.DeptService;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;

/**
 * 
 * @Description: 自定义参数解析器
 * @author: Qin YunFei
 * @date: 2017年10月14日 上午9:26:52
 * @version V1.0
 */
@Controller
@RequestMapping("/targumentResolver")
public class DepTargumentResolverAction extends BaseController{
	
	/*
	 * 参数解析器就是解析参数值并绑定到参数上
	 * 
	 * 写一个类实现org.springframework.web.method.support.HandlerMethodArgumentResolver接口
	 * 
	 * 定义自己绑定逻辑
	 * 
	 * 将自己定义的类配置到ApplicationContext-mvc.xml的<mvc:annotation-driven>中
	 * 
	 * <mvc:annotation-driven conversion-service="conversionService">
		<!-- HandlerMethodArgumentResolver(自定义的参数解析器) -->
	   <mvc:argument-resolvers>
	   		 <bean class="sgg.qin.framework.springmvc.argumentresolvers.CurrentUserNameMethodArgumentResolver" />
	   </mvc:argument-resolvers>
	</mvc:annotation-driven>
	 * 
	 * 
	 */
	
	
	@Autowired
	private DeptService deptService;

	/**
	 * 
	 * Description : 获取部门分页信息 <br>
	 * PageDataKeys : <br>
	 * return : List<PageData<String,Object>>
	 */
	@RequestMapping(value="dept",method=RequestMethod.GET)
	@ResponseBody
	public PageData<String, Object> queryDeptlist(@UserName String userName,@RequestParam("rows") Integer showCount, @RequestParam("page") Integer currentPage){
		Page page = deptService.queryDeptlist(this.getPage());
		//之前获取用户名是这样的
		Subject subject = this.getSubject();
		System.out.println(">>>>>"+subject.getPrincipal());
		
		//以上2部完全可以使用自定义参数解析器来实现  只是简单演示详细使用请百度 或参考HandlerMethodArgumentResolver实现类
		System.out.println(">>>>>##"+userName);
		
		
		return this.getPageMsgPageData(page);
	}
	
	

}
