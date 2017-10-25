package sgg.qin.framework.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


import sgg.qin.framework.annotation.CacheEvict;
import sgg.qin.framework.annotation.Cacheable;
import sgg.qin.framework.redis.RedisClientTemplate;
import sgg.qin.util.PageData;
import sgg.qin.web.controller.base.BaseController;

/*
 * @author xiaoqin 2017/9/14
 * */
@Aspect // 声明切面 该切面是需要注入到spring配置文件中
public class CacheAspect {

	@Autowired
	private RedisClientTemplate redisClientTemplate;

	// 定义环绕通知
	@Around("@annotation(sgg.qin.framework.annotation.Cacheable)") // 定义切面为实现了Cacheable注解的方法
	public Object cache(ProceedingJoinPoint pjp) throws Throwable {
		String key = null;
		String fieldKey = null;
		Object retVal = null;
		int expireTime = 0;
		// 获取被拦截的方法对象;
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		String MethodName = method.getName();
		String TargetClass = pjp.getTarget().getClass().getName().toString();
		key = (TargetClass + "." + MethodName).hashCode() + "";
		// 获取方法的注解对象
		Cacheable cacheable = method.getAnnotation(Cacheable.class);
		if (!cacheable.key().equals("")) {
			key = cacheable.key();
		}
		expireTime = cacheable.expireTime();

		fieldKey = parseKey(cacheable.fieldKey(), cacheable.condition(), method, pjp.getArgs());
		// fieldKey为""表示condition表达式判断失败无需进行缓存
		if (fieldKey.equals("")) {
			retVal = pjp.proceed();
			return retVal;
		}

		// System.out.println("fieldKey:" + fieldKey);
		if (!redisClientTemplate.exists(key)) {
			retVal = pjp.proceed();
			String value = JSON.toJSONString(retVal);
			redisClientTemplate.hset(key, fieldKey, value);
			redisClientTemplate.expire(key, expireTime);
		} else {
			if (redisClientTemplate.hexists(key, fieldKey)) {
				Object parse = JSON.parseObject(redisClientTemplate.hget(key, fieldKey), method.getReturnType());
				return parse;
			} else {
				retVal = pjp.proceed();
				String value = JSON.toJSONString(retVal);
				redisClientTemplate.hset(key, fieldKey, value);
			}

		}
		return retVal;
	}

	// 定义环绕通知 删除缓存
	@Around("@annotation(sgg.qin.framework.annotation.CacheEvict)") // 定义切面为实现了Cacheable注解的方法
	public Object decach(ProceedingJoinPoint pjp) throws Throwable {

		String key = "";
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		// 获取方法的注解对象
		CacheEvict cacheable = method.getAnnotation(CacheEvict.class);
		if (!cacheable.fun()) {
			key = cacheable.key();
		} else {
			key = cacheable.key().hashCode() + "";
		}
		redisClientTemplate.del(key);
		Object retVal = pjp.proceed();
		return retVal;
	}


	

	/**
	 * 获取缓存的key 并判断condition表达式 如果为fasle则返回空字符串 key 定义在注解上，支持SPEL表达式
	 * 
	 * @param pjp
	 * @return
	 */
	private String parseKey(String fieldKey, String condition, Method method, Object[] args) {
		String strretur = "";
		boolean condi = true;
		// 获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);
		// 使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		// SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		// 把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		if (!condition.equals("")) {
			// 发生异常直接返回“” 表示停止缓存
			try {
				condi = parser.parseExpression(condition).getValue(context, Boolean.class);
			} catch (EvaluationException e) {
				// TODO Auto-generated catch block
				System.out.println("condition表达式有异常 终止缓存");
				return "";
			}
		}

		if (condi) {
			String[] split = fieldKey.split(",");
			if (split.length > 1) {
				for (String string : split) {
					if (string.startsWith("#")) {
						strretur += parser.parseExpression(string).getValue(context, Object.class).hashCode();
					} else {
						strretur += string.hashCode();
					}
				}
				return strretur;
			} else {
				if (fieldKey.startsWith("#")) {
					return getfieldKey(parser.parseExpression(fieldKey).getValue(context, Object.class));
				} else {
					return fieldKey;
				}
			}

		} else {
			return "";
		}

	}

	/**
	 * 判断对象的数据类型是调用String 还是调用hashCode
	 * 
	 * @param pjp
	 * @return
	 */
	private String getfieldKey(Object param) {

		if (param instanceof Integer || param instanceof String || param instanceof Double || param instanceof Float
				|| param instanceof Long || param instanceof Long || param instanceof Boolean
				|| param instanceof Date) {
			return param.toString();
		}
		return param.hashCode() + "";
	}
}
