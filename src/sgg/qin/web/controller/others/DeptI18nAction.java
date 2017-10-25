package sgg.qin.web.controller.others;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;
import sgg.qin.service.DeptService;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;

/**
 * 
 * @Description: 国际化
 * @author: Qin YunFei
 * @date: 2017年10月14日 上午9:26:52
 * @version V1.0
 */
@Controller
@RequestMapping("/i18n")
public class DeptI18nAction extends BaseController {
	/*
	 * LocalResolver  springmvc九大组件之一
	 */
	
	/*
	 * 国际化有2个重要的东西
	 * 	1 首先是国际化的配置文件
	 * 	2 然后是Local区域信息
	 * 	
	 * 	之前原始的用法是使用java的工具类ResourceBundle加载配置文件和区域信息 在使用ResourceBundle对象获取key
	 * 
	 * 			ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
	 * 			String string = bundle.getString(key)
	 * 
	 *  使用spring原理一样
	 *  		使用MessageSource(接口)组件来管理国际化的配置文件 一般使用org.springframework.context.support.ResourceBundleMessageSource
	 *  	
	 *  有了国际化配置文件的管理还需要区域信息管理
	 *     springmvc 的LocalResolver(接口)组件来负责Local区域信息的配置  默认是直接从request中获取获取
	 *     	可以自己定义实现
	 *     扩展
	 *     LocalResolver有一个实现类SessionLocaleResolver 将默认的LocalResolver改为它 它是从session中获取Local区域信息
	 *     从session中获取Local区域信息我们也可以不用自己写   对与 SessionLocaleResolver 有一个拦截器 LocaleChangeInterceptor 
	 *     LocaleChangeInterceptor 是获取请求参数的key=locale  参数值  根据参数值生成Local对象  将Local对象放入session中
	 *     session的key是固定的 叫什么我忘记了 去看LocaleChangeInterceptor实现就知道了
	 *     
	 *     不过这些麻烦  我们还是自定义LocalResolver比较好
	 *     
	 */
	
	
	
	@Qualifier("messageSource")
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping("/show")
	public String handlex(Locale locale,HttpServletRequest request) {
		//原始的用法
		// 从请求中获取区域信息对象；Accept-Language:en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4
		  Locale locale2 = request.getLocale();
		// 获取操作系统默认的区域信息
		Locale default1 = Locale.getDefault();
		//还可以这样获取
		Locale english = Locale.US;
		// 绑定国际化资源文件
		ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n", locale2);
		//根据区域信息获取知道key的值
		String string = resourceBundle.getString("login.username");
		System.out.println(string);
		
		//springmvc的国际化使用
		String message = messageSource.getMessage("login.username", null, locale);
		System.out.println("获取到的值："+message);
		
		
		
		
		

		return "others/i18nIndext";
	}

}
