package training.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 对每个请求进行登陆拦截验证
 * @author Administrator
 *
 */
public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException {
		System.out.println("LoginFilter working...........");
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		// 忽略的url验证列表
		String exIncludeUrl = super.getFilterConfig().getInitParameter("excludeUrls");
		if (exIncludeUrl != null && !"".equals(exIncludeUrl)) {
			System.out.println("忽略验证登陆的Url为： " + exIncludeUrl);
			List<String> excludeUrlList = Arrays.asList(exIncludeUrl.split(","));
			if (excludeUrlList != null && excludeUrlList.size() > 0) {
				for (String url : excludeUrlList) {
					if (url.contains("/*")) {
						if (servletPath.startsWith(url.substring(0, url.length() - 2))) {
							filterChain.doFilter(request, response);
							return;
						}
					}
					if (url.equals(servletPath)) {
						filterChain.doFilter(request, response);
						return;
					}
				}
			}
		} else {
			throw new RuntimeException("忽略验证的url为空，当前会出现死循环！");
		}
		if (session.getAttribute("user") == null) {
			try {
				super.getServletContext().setAttribute("LAST_PATH", servletPath);
				request.getRequestDispatcher("/userLoginJspServlet.do").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
	
}
