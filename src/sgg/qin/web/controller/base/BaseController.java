package sgg.qin.web.controller.base;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import sgg.qin.domain.Page;
import sgg.qin.util.Logger;
import sgg.qin.util.PageData;
import sgg.qin.util.UuidUtil;


/**
 * @author xiaoqing
 * 修改时间：2017、9、17
 */
public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;
	
	//action的参数封装 线程安全的
	public static ThreadLocal<PageData<String,Object>> threadLocal = new ThreadLocal<PageData<String,Object>>();

	
	/** 方法参数封装的PageData对象
	 * @return
	 */
	public PageData<String,Object> getPageData(){
		return threadLocal.get();
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	/**得到分页列表的信息 得到分页列表的信息 (默认方法参数)
	 * @return
	 */
	public Page getPage(){
		Page page = new Page();
		page.setPd(threadLocal.get());
		return page;
	}
	
	/**得到分页列表的信息 (自定义参数)
	 * @return
	 */
	public Page getPage(PageData pd){
		Page page = new Page();
		page.setPd(pd);
		return page;
	}
	
	/**设置Msg消息 
	 * @return
	 */
	public PageData<String, Object> getMsgPageData(Boolean success,String msg) {	
		PageData<String,Object> pageData=new PageData<String,Object>();
		pageData.put("success", success);//请求是否成功
		pageData.put("msg", msg);//提示消息
		return pageData;
	}
	
	/**设置Msg消息 （带异常信息）
	 * @return
	 */
	public PageData<String, Object> getMsgPageData(Boolean success,String msg,Exception ex) {	
		PageData<String,Object> pageData=new PageData<String,Object>();
		pageData.put("success", success);//请求是否成功
		pageData.put("msg", msg);//提示消息
		pageData.put("exception", ex);//错误详细信息
		return pageData;
	}
	/**设置pageMsg消息 (分页数据返回)
	 * @return
	 */
	public PageData<String, Object> getPageMsgPageData(Page page) {	
		PageData<String,Object> pageData=new PageData<String,Object>();
		pageData.put("total", page.getTotalResult());//总记录数
		pageData.put("rows", page.getRetlist());//分页数据
		return pageData;
	}
	
	
	/**获取shiro 的Subject对象
	 * @return
	 */
	public Subject getSubject() {	
		return  SecurityUtils.getSubject();
	}
	

	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
}