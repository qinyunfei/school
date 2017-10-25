package sgg.qin.web.controller.others;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;
import sgg.qin.service.DeptService;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;

/**
 * 
 * @Description: 部门管理Action
 * @author: Qin YunFei
 * @date: 2017年10月14日 上午9:26:52
 * @version V1.0
 */
@Controller
@RequestMapping("/restful")
public class DeptRestfulAction extends BaseController{
	
	/*
	 * restful原理这里不多说 自己百度
	 * 实现步骤  要将请求模拟为 get put post delete等请求
	 * 页面统一发post请求 在请求参数中带一个key为 _method 的参数 参数值为要模拟的请求
	 * 在web.xml配置一个filter如下所示
	 * 
	<!--支持rest风格的filter  -->
	<filter>
		<filter-name>HiddenHttpMethodFilter</filter-name>
		<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>HiddenHttpMethodFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	 * 
	 * 
	 * 最后在@RequestMapping 中限定请求方式即可
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
	public PageData<String, Object> queryDeptlist(@RequestParam("rows") Integer showCount, @RequestParam("page") Integer currentPage){
		Page page = deptService.queryDeptlist(this.getPage());
		return this.getPageMsgPageData(page);
	}
	
	/**
	 * 
	 * Description : 根据部门id更新部门信息 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	@RequestMapping(value="dept",method=RequestMethod.PUT)
	@ResponseBody
	public PageData<String, Object> upadteDeptByDeptno(Dept dept) {
		deptService.upadteDeptByDeptno(dept);
		return this.getMsgPageData(true, "更新成功");
	};

	/**
	 * 
	 * Description : 添加部门 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	@RequestMapping(value="dept",method=RequestMethod.POST)
	@ResponseBody
	public PageData<String, Object> saveDept(Dept dept) {
		deptService.saveDept(dept);
		return this.getMsgPageData(true, "添加成功");
	};

	/**
	 * 
	 * Description : 根据部门id删除部门 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	@RequestMapping(value="dept",method=RequestMethod.DELETE)
	@ResponseBody
	public PageData<String, Object> deleteDeptByDeptno(int deptno) {
		deptService.deleteDeptByDeptno(deptno);
		return this.getMsgPageData(true, "删除成功");
	};
	
	

}
