package sgg.qin.web.controller.others;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

import sgg.qin.domain.Dept;
import sgg.qin.domain.Page;
import sgg.qin.service.DeptService;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;

/**
 * 
 * @Description: 消息转换器
 * @author: Qin YunFei
 * @date: 2017年10月14日 上午9:26:52
 * @version V1.0
 */
@Controller
@RequestMapping("/messageConverter")
public class MessageConverterIndexAction extends BaseController{
	

	
	/*
	 * @ResponseBody:将返回值写出去；	
	 * 		produces="text/html;charset=utf-8":可以解决写出去的字符乱码
	 * @RequestBody:获取到请求体的所有数据（只有post请求有请求体）
	 * 
	 * HttpEntity：@RequestBody只能获取请求体；
	 * 		HttpEntity：请求的所有数据都能获取到，请求体，请求头
	 * <username=dsadsa&password=214343,{host=[localhost:8080], connection=[keep-alive],accept-encoding=[gzip, deflate, br], accept-language=[en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4], cookie=[JSESSIONID=ADD0BC15151A52F74E47C4DF268CF3E1; Idea-87813150=28538c9b-bc7c-4e17-aa14-a29408d0d789; _ga=GA1.1.1178830811.1503628201], Content-Type=[application/x-www-form-urlencoded;charset=UTF-8]}>
	 * 具体的某个请求头：@RequestHeader("host")
	 * 
	 * ResponseEntity：@ResponseBody只是将数据写出在响应体中；
	 * 		ResponseEntity：既能写出数据在响应体（内容），也能自定义响应头（对浏览器的指令）
	 * 
	 */


	
	@RequestMapping(value="/1",produces="text/html;charset=utf-8")
	@ResponseBody
	public String test1(){

		return "小黑你好";
	}
	
	
	/**
	 * jackson-annotations-2.1.5.jar
	 * jackson-core-2.1.5.jar
	 * jackson-databind-2.1.5.jar
	 * @return
	 */
	//jackson支持将对象写成json
	@RequestMapping("/2")
	@ResponseBody
	public PageData<String, Object> test2(){
		PageData<String, Object> pageData=new PageData<String, Object>();
		pageData.put("姓名", "小黑");
		pageData.put("性别", "男");
		return pageData;
	}
	
	
	/*
	 * @RequestBody可以获取请求体信息（post请求才有请求体）
	 * @RequestHeader可以获取请求头信息
	 *   也可以获取指定的请求头
	 *    例如@RequestHeader("host") 就是获取请求头中的host
	 */
	@ResponseBody
	@RequestMapping(value="/3",method=RequestMethod.POST)
	public String requestBody(@RequestBody String body,@RequestHeader HttpHeaders header){
	
		System.out.println("请求体："+body);
		
		System.out.println("请求头"+header);
		
		return "success";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/4")
	public String requestBody(HttpEntity<String> request,@RequestHeader("host")String host){
		System.out.println("请求数据："+request);
		System.out.println(host);
		//请求体
		String body = request.getBody();
		
		//请求头
		HttpHeaders headers = request.getHeaders();
		//获取某个请求头的值
		List<String> list = headers.get("host");
		System.out.println(list);
		return "success";
	}
	
	/**
	 * ResponseEntity<byte[]>:泛型代表要响应给浏览器的数据类型；定制响应
	 * 1)、响应头、响应体、响应状态码都可以定制
	 * 2)、把定制的ResponseEntity返回
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/5")
	public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException{
	
		ServletContext servletContext = request.getSession().getServletContext();
		InputStream stream = servletContext.getResourceAsStream("/resource/imgs/wanglikun.jpg");
		//1、响应体流数据
		byte[] body = new byte[stream.available()];
		stream.read(body);
		stream.close();
		//文件下载需要一个定制响应头；浏览器对于认识的数据默认是打开操作
		//使用以下响应头命令浏览器进行下载；固定格式
		// Content-Disposition:attachment; filename=aaa.js
		
		//2、定制响应头
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=wanglikun.jpg");
		headers.add("Haha", "heihei");
		//3、定制响应状态码
		HttpStatus statusCode = HttpStatus.OK;
		//参数分别为响应体  响应头 和响应状态码
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body, headers, statusCode);
		
		return responseEntity;
	}
	

}
