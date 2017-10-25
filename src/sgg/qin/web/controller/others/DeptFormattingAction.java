package sgg.qin.web.controller.others;


import java.util.Date;

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
@RequestMapping("/formatting")
public class DeptFormattingAction extends BaseController{
	
	/*
	 * 
	 *  1:对属性对象的输入/输出进行格式化，从其本质上讲依然属于 “类型转换” 的范畴。
		2:Spring 在格式化模块中定义了一个实现 ConversionService 接口的
			FormattingConversionService 实现类，该实现类扩展了 GenericConversionService，因此它既具有类型转换的功能，又具有格式化的功能
		3:FormattingConversionService 拥有一个 FormattingConversionServiceFactroyBean 工厂类，后者用于在 Spring 上下文中构造前者，FormattingConversionServiceFactroyBean 内部已经注册了 :
		4:NumberFormatAnnotationFormatterFactroy：支持对数字类型的属性使用
 			@NumberFormat 注解
		5:JodaDateTimeFormatAnnotationFormatterFactroy：支持对日期类型的属性使用
 			@DateTimeFormat 注解
 			
 		有很多注解请百度
		6:装配了 FormattingConversionServiceFactroyBean 后，就可以在 Spring MVC 入参绑定及模型数据输出时使用注解驱动了。
				<mvc:annotation-driven/> 默认创建的 ConversionService 实例即为DefaultFormattingConversionService 
				
		基本上不需要配置   使用<mvc:annotation-driven/> 就可以了   也可以自定义请百度
	 */

	
	@Autowired
	private DeptService deptService;
	

	/**
	 * 
	 * Description : 添加部门 <br>
	 * PageDataKeys : <br>
	 * return : void
	 */
	
	/*
	 * 页面提交的日期类型默认2017/07／09
	 * 使用日期格式注解  设置页面提交的日期类型为2017-07-09否则400错误
	 * 
	 */
	@RequestMapping(value="dept",method=RequestMethod.POST)
	@ResponseBody
	public PageData<String, Object> saveDept(Dept dept) {
		deptService.saveDept(dept);
		return this.getMsgPageData(true, "添加成功");
	};

	

}
