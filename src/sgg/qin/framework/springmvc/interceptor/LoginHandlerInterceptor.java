package sgg.qin.framework.springmvc.interceptor;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//处理程序拦截器适配器
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	//System.out.println("Springmvc拦截器：sgg.qin.framework.springmvc.interceptor.LoginHandlerInterceptor");
//		HandlerMethod method =(HandlerMethod) handler;
//		Method method2 = method.getMethod();
//		Parameter[] parameters = method2.getParameters();
//		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
//		String[] parameterNames = u.getParameterNames(method2);
		
		return true;
	}
}
