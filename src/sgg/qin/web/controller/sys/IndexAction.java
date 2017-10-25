package sgg.qin.web.controller.sys;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sgg.qin.domain.sys.Resource;
import sgg.qin.service.ResourceService;

@Controller
public class IndexAction {

	// 访问系统首页
	@RequestMapping("/")
	public String Index() throws Exception {
		return "index";
	}

	// 资源管理首页
	@RequiresPermissions("menu:menu")
	@RequestMapping("/resourceIndext")
	public String resourceIndext() {
		return "sys/resourceIndext";
	}

	// 角色管理首页
	@RequiresPermissions("role:menu")
	@RequestMapping("/roleIndext")
	public String roleIndext() {
		return "sys/roleIndext";
	}

	// 用户管理管理首页
	@RequiresPermissions("user:menu")
	@RequestMapping("/userIndext")
	public String userIndext() {
		return "sys/userIndext";
	}
	
	//restUrlCRUD
	@RequiresPermissions("restUrlCRUD:menu")
	@RequestMapping("/restUrlIndext")
	public String restUrlIndext() {
		return "others/restUrlIndext";
	}
	
	//数据校验
	@RequiresPermissions("dataValidation:menu")
	@RequestMapping("/dataValidationIndext")
	public String dataValidationIndex() {
		return "others/dataValidationIndext";
	}
	
	//自定义类型转换器
	@RequiresPermissions("conversionService:menu")
	@RequestMapping("/conversionServiceIndext")
	public String conversionServiceIndext() {
		return "others/conversionServiceIndext";
	}
	
	
	//数据格式化
	@RequiresPermissions("formatting:menu")
	@RequestMapping("/formattingIndex")
	public String formattingIndex() {
		return "others/formattingIndex";
	}
	
	//自定义视图解析器与视图
	@RequiresPermissions("view:menu")
	@RequestMapping("/viewIndext")
	public String viewIndext() {
		//重定向到/view/img
		return "redirect:/view/img/wanglikun";
	}
	
	//自定义参数解析器
	@RequiresPermissions("argumentResolver:menu")
	@RequestMapping("/argumentResolverIndext")
	public String argumentResolverIndext() {
		return "others/argumentResolverIndext";
	}
	
	
	//国际化
	@RequiresPermissions("i18n:menu")
	@RequestMapping("/i18nIndext")
	public String i18nIndext() {
		//重定向到/i18n/show
		return "redirect:/i18n/show";
	}
	
	//消息转换器
	@RequiresPermissions("messageConverter:menu")
	@RequestMapping("/messageConverterIndex")
	public String messageConverterIndex() {
		return "others/messageConverterIndex";
	}
	
	

}
