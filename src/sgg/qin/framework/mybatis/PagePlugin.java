package sgg.qin.framework.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import sgg.qin.domain.Page;

import sgg.qin.util.Tools;


/**
 * 
 * @Description: 自定义分页插件
 * @author: Qin YunFei
 * @date: 2017年10月16日 下午4:56:43
 * @version V1.0
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PagePlugin implements Interceptor {

	private static String dialect = ""; // 数据库方言

	// 拦截逻辑，参数是代理类
	/**
	 * 处理链路 |--- StatementHandler(包含BoundSql对象（包含sql信息和参数信息） 和BaseStatementHandler对象
	 * 不过是受保护的需要通过反射获取) |--- --- RoutingStatementHandler（StatementHandler的子类） |---
	 * ---
	 * BaseStatementHandler(包含了映射器MappedStatment（受保护的通过反射获取）和参数处理器PreparedStatementHandler)
	 * |--- --- --- PreparedStatementHandler //参数处理器
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		Page page = null;
		RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
		// MetaObject是Mybatis提供的一个用于方便、优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		BaseStatementHandler delegate = (BaseStatementHandler) metaObject.getValue("delegate");
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 获取BoundSql对象
		BoundSql boundSql = delegate.getBoundSql();
		Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
		if (parameterObject!=null&&parameterObject instanceof Page) { // 判断参数是否是Page
			page = (Page) parameterObject;
			// 判断是否开启分页
			if (page.isPage()) {
				// 获取数据库连接对象
				Connection connection = (Connection) invocation.getArgs()[0];
				// 获取当前sql
				String sql = boundSql.getSql();
				// 生成当前sql的记录统计sql
				String countSql = "select count(0) from (" + sql + ")  tmp_count";
				PreparedStatement countStmt = connection.prepareStatement(countSql);
				// 使用系统默认的参数处理器对对sql的 ？进行设值
				ParameterHandler ph = delegate.getParameterHandler();
				ph.setParameters(countStmt);
				ResultSet rs = countStmt.executeQuery();
				// 数据总数
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				// 关闭ResultSet和PreparedStatement对象
				rs.close();
				countStmt.close();
				page.setTotalResult(count);// 设置总数
				String pageSql = generatePageSql(sql, page);
				// 将修改后的sql放入boundSql对象
				metaObject.setValue("delegate.boundSql.sql", pageSql);
			}
		}

		return invocation.proceed();
	}

	/**
	 * 根据数据库方言，生成特定的分页sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePageSql(String sql, Page page) {
		if (page != null && Tools.notEmpty(dialect)) {
			StringBuffer pageSql = new StringBuffer();
			if ("oracle".equals(dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				// pageSql.append(") as tmp_tb where ROWNUM<=");
				pageSql.append(") tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentResult() + page.getShowCount());
				pageSql.append(") where row_id>");
				pageSql.append(page.getCurrentResult());
			}

			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit ");
				pageSql.append(page.getCurrentResult());
				pageSql.append(",");
				pageSql.append(page.getShowCount());

			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	// 加载插件，一般使用Plugin.wrap(target, this); 使用当前的这个拦截器实现对目标对象的代理（内部实现时Java的动态代理）
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		return Plugin.wrap(arg0, this);
	}

	// 初始化属性
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (Tools.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect 属性是没有找到!");
			} catch (PropertyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}