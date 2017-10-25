package sgg.qin.framework.springmvc.localeresolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

public class MyLocaleResolver implements LocaleResolver{

	//获取Locale
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		//1、获取请求参数带来的Locale值  zh_CN  en_US
		String str = request.getParameter("locale");
		
		if(!StringUtils.isEmpty(str)){
			Locale locale = new Locale(str.split("_")[0], str.split("_")[1]);
			return locale;
		}
		//如果没带就是请求头的
		return request.getLocale();
	}
	
	//配合拦截器使用  提前在session或者别的地方设置好Locale
	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"Cannot change HTTP accept header - use a different locale resolution strategy");
		
	}

}
