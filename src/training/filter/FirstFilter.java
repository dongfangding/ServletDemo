package training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 实现一个Filter的步骤
 * 1. 实现Filter接口，重写doFilter方法
 * 2. 在web.xml中配置<filter-name></filter-name>和<filter-mapping></filter-mapping>
 * 	
 * 		  <filter>
		  	<description>配置一个最基本的Filter，介绍使用方法</description>
		  	<filter-name>firstFilter</filter-name>
		  	<filter-class>training.filter.FirstFilter</filter-class>
		  	<init-param>
		  		<param-name>firstFilter</param-name>
		  		<param-value>firstFilterValue1</param-value>
		  	</init-param>
		  	
		  </filter>
		  
		  <filter-mapping>
		  	<filter-name>firstFilter</filter-name>
		  	<url-pattern>/pages/filter/firstFilter.jsp</url-pattern>
		  	<dispatcher>REQUEST</dispatcher>
  			<dispatcher>FORWARD</dispatcher>
		  </filter-mapping>
 * 
 * 3. 在u<filter-mapping>配置<dispatcher></dispatcher>属性，REQUEST.
 * 	  该界面用来配置拦截的方法，默认REQUEST,即对所有的请求加入拦截范围。
 *   常用的还有FORWARD.即如果是使用的JSP的转发也会被加入到拦截范围，如果需要多种范围公用，可以配置多个，如下。
 *   	<dispatcher>REQUEST</dispatcher>
  		<dispatcher>FORWARD</dispatcher>
 * 
 * 
 * @author Administrator
 *
 */
public class FirstFilter implements Filter {
	// 定义FilterConfig参数，将init方法里的FilterConfig引用过来使用
	private FilterConfig filterConfig;

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("FirstFilter destroy.......");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("FirstFilter Working.........");
		// 拦截到请求后的处理代码逻辑写在这里,如果当前Filter拦截到的firstFilter参数不是firstFilterValue，
		// 则把请求映射到另外一个地址终止原请求资源的继续访问
		String paramValue = filterConfig.getInitParameter("firstFilter");
		if (!"firstFilterValue".equals(paramValue)) {
			request.setAttribute("message", "FirstFilter Working.........,当前firstFilter参数的值不是firstFilterValue，不能继续访问！");
			request.getRequestDispatcher("/pages/filter/filterResult.jsp").forward(request, response);
			// or return
		}
		
		/**
		 * FilterChain的执行顺序
		 * 如果对同一个资源配置了多个Filter，那么他们doFilter执行的顺序依赖于在web.xml中<filter-mapping></filter-mapping>的顺序，
		 * 具体执行代码的顺序为
		 * 	界面发起对目标资源的访问请求，被第一个Filter的doFilter拦截，在处理完业务逻辑代码之后执行chain.doFilter(request, response)方法，如果此方法之后还有业务逻辑
		 *  代码，那么不会立即执行，而是跳转到第二个Filter的doFilter方法，在这里先处理第二个Filter调用chain.doFilter(request, response)方法之前所有的业务代码逻辑，
		 *  处理结束之后开始执行第二个Filter的chain.doFilter(request, response)。此时所有的Filter都执行完毕，这个时候就会访问到目标资源。然后才会按照倒序开始处理
		 *  第二个Filter在chain.doFilter(request, response)方法之后的代码，处理结束之后，返回到第二个Filter中，开始执行第一个Filter在chain.doFilter(request, response)
		 *  方法之后的代码。这个时候整个FilterChain执行结束
		 * 
		 */
		// pass the request along the filter chain
		chain.doFilter(request, response);
		System.out.println("FirstFilter over...........");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("FirstFilter init.......");
		this.filterConfig = fConfig;
	}

}
