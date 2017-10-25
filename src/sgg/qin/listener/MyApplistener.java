package sgg.qin.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyApplistener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("项目监听器启动....");
		// TODO Auto-generated method stub
		ServletContext servletContext = sce.getServletContext();
		servletContext.setAttribute("ctp", servletContext.getContextPath());	
	}

}
