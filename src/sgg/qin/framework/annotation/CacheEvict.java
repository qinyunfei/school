package sgg.qin.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>redis缓存删除</p>
 * 
 *
 * @author xiaoqin 2017/9/14
 */
@Target({ElementType.METHOD})  //此注解用来注解方法
@Retention(RetentionPolicy.RUNTIME)//运行时注解
public @interface CacheEvict {
    String key();        //hash的key
    boolean fun() default false; //是否以方法作为key
}
