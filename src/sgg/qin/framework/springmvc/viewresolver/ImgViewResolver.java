package sgg.qin.framework.springmvc.viewresolver;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 
 * @Description: 自定义视图解析器，根据视图名返回一个View对象；
 * @author: Qin YunFei
 * @date: 2017年10月15日 下午4:48:34
 * @version V1.0
 */
public class ImgViewResolver implements ViewResolver, Ordered {

	private Integer order;

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return this.order;
	}

	/**
	 * viewName：收到的是方法的返回值 逻辑视图 
	 * Locale：国际化 
	 * View：得到一个能显示图片的view对象
	 */
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		//能解析的解析返回，不能解析的，解析返回null。让下一个视图解析器工作
		
		//show:wanglikun
		if(viewName.startsWith("show:")){
			String substring = viewName.substring(5);
			String imgPath  = substring+".jpg";
			
			//图片视图对象；
			ImgView imgView = new ImgView(imgPath);
			return imgView;
			
		}
		return null;
	}

}
