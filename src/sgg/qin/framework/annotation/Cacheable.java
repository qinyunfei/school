package sgg.qin.framework.annotation;

import java.lang.annotation.*;

/**
 * <p>redis缓存创建 采用hash存储</p>
 * 如果只有一个参数的话如果这个参数不是基本数据类型或者封装类 使用对象的hascode做为key 否则使用tostring做为key
   如果参数多个的话则使用所有参数的hashCode作为key 中间以，号分割
 *
 * @author xiaoqin 2017/9/14
 */
@Target({ElementType.METHOD})  //此注解用来注解方法
@Retention(RetentionPolicy.RUNTIME)//运行时注解
public @interface Cacheable {
    String key() default "";      	//has 的key 默认生成方法的全名的hashCode
    String fieldKey();			  	//has 的fieldKey 支持SPEL表达式 如果没有参数必须设置为0值
    String condition() default ""; 	//支持SPEL表达式 可以用来判断什么情况下缓存生效
    int expireTime() default 3600;	//ttl 超时时间
}
