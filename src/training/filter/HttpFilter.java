package training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 对需要实现Filter接口写过滤器代码的程序做一些优化，可以简化其他有业务逻辑的Filter
 * @author Administrator
 *
 */
public abstract class HttpFilter implements Filter {
	// 定义FilterConfig对象，把Filter出初始化时的FilterConfig引用过来以供子类使用
	private FilterConfig filterConfig;
	private ServletContext servletContext;
	
	protected FilterConfig getFilterConfig() {
		return filterConfig; 
	}

	protected ServletContext getServletContext() {
		return this.servletContext;
	}
	
	@Override
	public void destroy() {
		System.out.println("HttpFilter destroy.......");
	}

	/**
	 * 原生的Filter接口的doFilter代码
	 */
	/*@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		filterChain.doFilter(request, response);
	}*/
	
	
	/**
	 * 对原生的Filter接口的doFilter代码进行编写，将ServletRequest转换为HttpServletRequest对象以供子类使用
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("HttpFilter working...........");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		doFilter(httpRequest, httpResponse, filterChain);
		System.out.println("HttpFilter over...........");
	}
	
	/**
	 * 如果需要使用Filter，必须要实现的方法
	 * @param request
	 * @param response
	 * @param filterChain
	 */
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.servletContext = filterConfig.getServletContext();
		System.out.println("HttpFilter init.......");
	}

}
