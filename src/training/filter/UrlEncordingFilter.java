package training.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * URL编码的拦截器。处理所有的请求的编码
 * <filter>
  	<filter-name>encordingFilter</filter-name>
  	<filter-class>training.filter.UrlEncordingFilter</filter-class>
  	<init-param>
  		<param-name>encording</param-name>
  		<param-value>UTF-8</param-value>
  	</init-param>
  </filter>
  
  <filter-mapping>
  	<filter-name>encordingFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  	<dispatcher>REQUEST</dispatcher>
  </filter-mapping>
 * @author Administrator
 *
 */
public class UrlEncordingFilter extends HttpFilter{
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException {
		String encoring = super.getServletContext().getInitParameter("ENCORDING");
		System.out.println("UrlEncordingFilter working..............设置当前编码为：" + encoring);
		try {
			request.setCharacterEncoding(encoring);
			response.setCharacterEncoding(encoring);
			
			// 同时禁止所有的浏览器缓存行为
			response.setHeader("Cache-Control", "no-cache");  
			response.setHeader("Pragma", "no-cache");  
			response.setDateHeader("Expires", 0);  
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("UrlEncordingFilter over..........");
		filterChain.doFilter(request, response);
	}
}
