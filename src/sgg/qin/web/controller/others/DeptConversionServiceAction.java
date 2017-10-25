package sgg.qin.web.controller.others;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@RequestMapping("/deptConverter")
public class DeptConversionServiceAction extends BaseController {

	/*
	 * 1、ConversionService 是 Spring 类型转换体系的核心接口。
	 *  2、可以利用 ConversionServiceFactoryBean  在 Spring 的 IOC 容器中定义一个 ConversionService. Spring 将自动识别出 IOC 容器中的ConersionService，并在 Bean 属性配置及 Spring MVC 处理方法入参绑定等场合使用它进行数据的转换
	 * 3、可通过ConversionServiceFactoryBean 的 converters 属性注册自定义的类型转换器（注意
	 * ConversionServiceFactoryBean创建的ConversionService没有格式化器（格式化失效） 推荐使用FormattingConversionServiceFactoryBean以后推荐使用这个，他创建的ConversionService具有格式化器和转换器）
	 * 
	 * 
	 * 4：Spring 定义了 3 种类型的转换器接口，实现任意一个转换器接口都可以作为自定义转换器注册到 ConversionServiceFactoryBean中：
	 * 	1：Converter<S,T>：将 S 类型对象转为 T 类型对象（一般用这个）
	 * 	2：ConverterFactory：将相同系列多个 “同质” Converter封装在一起。如果希望将一种类型的对象转换为另一种类型及其子类的对象（例如将 String 转换为 Number 及 Number
	 * 		子类（Integer、Long、Double 等）对象）可使用该转换器工厂类
	 * 	3:GenericConverter：会根据源类对象及目标类对象所在的宿主类中的上下文信息进行类型转换
	 */
	
	/*
	 * 使用方法：
	 * 	 1 写一个类实现Converter<S,T>接口  sgg.qin.framework.springmvc.converter.StrringToDeptConvertor
	 * 
	 * 	 2 在将自定义的Converter（StrringToDeptConvertor） 注册到 FormattingConversionServiceFactoryBean中(
	 * 		在ApplicationContext-mvc.xml 中注册FormattingConversionServiceFactoryBean biean  将自定义的Converter注册到该bean的converters属性中)
	 * 
	 * 	3：在<mvc:annotation-driven ／>的conversion-service属性中指定FormattingConversionServiceFactoryBean的id
	 * 
	 * 如下所示
	 * 
	 *  	<bean id="conversionService"
		class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<property name="converters">
			<set>
				<bean class="sgg.qin.framework.springmvc.converter.StrringToDeptConvertor"></bean>
			</set>
		</property>
		</bean>
		
		<mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>

		
	 * 
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
	 * 
	 * 
	 * */
	
	//页面提交 deptstr=市场部&&2017-08-09
	//编写一个自定义类型转换器。来负责将String转为Dept
	@RequestMapping(value = "dept", method = RequestMethod.POST)
	@ResponseBody
	public PageData<String, Object> saveDept(@RequestParam("deptstr")Dept dept) {
		deptService.saveDept(dept);
		return this.getMsgPageData(true, "添加成功");
	};

}
