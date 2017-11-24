package training.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Servlet 一共提供了三个层面的Listeners
 * 
 * 1. 提供整个应用层面的ServletContextListener，可以用来监听应用程序的创建和销毁
 * 2. HttpSessionListener，监听HttpSession的创建和销毁
 * 3. HttpRequestListener，监听请求的创建和销毁
 * 
 *  其中以ServletContextListener用的最多，可以在应用启动的时候初始化框架和参数，如SpringIOC的启动就是依赖于Listener。
 *  
 *  需要在web.xml中配置<listener></listener>
 * @author Administrator
 *
 */
public class FirstServletContextListener implements ServletContextListener {
	
	
	/**
	 * ServletContext创建的时候被调用
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(sce.getServletContext().getServletContextName() + "--FirstServletContextListener创建");
	}
	
	/**
	 * ServletContext销毁的时候被调用
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(sce.getServletContext().getServletContextName()  + "--FirstServletContextListener销毁");
	}

}
