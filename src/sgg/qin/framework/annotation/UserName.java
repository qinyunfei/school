package sgg.qin.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Description: 给springmvc 参数解析器使用 获取登陆用户的用户名
 * @author: Qin YunFei
 * @date: 2017年10月15日 下午7:27:43
 * @version V1.0
 */
@Target({ElementType.PARAMETER})  //此注解用来注解参数 
@Retention(RetentionPolicy.RUNTIME)//运行时注解
public @interface UserName {

}
