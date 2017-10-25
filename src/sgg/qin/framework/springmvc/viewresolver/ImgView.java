package sgg.qin.framework.springmvc.viewresolver;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.View;

/**
 * 
 * @Description: 自定义的视图对象
 * @author: Qin YunFei
 * @date: 2017年10月15日 下午4:51:48
 * @version V1.0
 */
public class ImgView implements View{
	
	
	private String path;
	
	//传入图片路径
	public ImgView(String path) {
		super();
		this.path = path;
	}

	 /**
     * 视图类型
     */
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "text/html;charset=utf-8";
	}

    /**
     * 渲染视图
     */
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//获取ServletContext对象 一个web项目对应一个ServletContext；
		ServletContext context = request.getServletContext();
		//获取指定文件的输入流
		InputStream in = context.getResourceAsStream("resource/imgs/"+path);
		//获取输出流
		ServletOutputStream out = response.getOutputStream();
		//将输入流的内容拷贝到输出流
		IOUtils.copy(in, out);
		in.close();
		out.close();
	}

}
