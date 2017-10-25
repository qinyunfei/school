package sgg.qin.web.controller.others;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/view")
public class DeptViewAction extends BaseController{
	
	/*
	 * ViewResolver接口(视图解析器) 是springMVC九大组件之一
	 * 
	 * ViewResolver用来将String类型的视图名（也叫逻辑视图）和Locale解析为View类型的视图
	 * 也就是说ViewResolver是用来找使用哪个View（视图）进行页面渲染的
	 * 详细信息进sgg.qin.framework.springmvc.viewresolver.ImgViewResolver查看
	 * 
	 * 
	 * View接口（视图）负责渲染页面
	 * 由ViewResolver来进行逻辑视图的解析来找能处理该视图的View
	 * 找到后由该View进行页面渲染
	 * 详细信息进sgg.qin.framework.springmvc.viewresolver.ImgView查看
	 * 
	 */
	
	
	/*
	 * 自定义使用方法
	 * 写一个类实现ViewResolver接口  系统有很多ViewResolver按顺序解析 有可能在我们自定义的视图解析器之前被别的视图解析器解析
	 * 解决方法 实现ViewResolver接口的同时实现Ordered接口 会返回一个int类型的值  值越小优先级越高
	 * 
	 * 写一个了类实现View接口
	 * 写自己的处理逻辑
	 * 
	 * 将自己的ViewResolver配置在ApplicationContext-mvc.xml中
	 */
	
	@Autowired
	private DeptService deptService;
	

	//根据返回值来显示一张图片
	//1、写一个自定义的视图解析器，来解析我们这些自定义的返回值
	//2、写一个自定义的视图对象，视图对象来给浏览器显示图片
	@RequestMapping("/img/{name}")
	public String handlex(@PathVariable("name")String name){
		return "show:"+name;
	}
	
	

}
