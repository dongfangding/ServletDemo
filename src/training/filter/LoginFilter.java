package training.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws IOException, ServletException {
		System.out.println("LoginFilter working...........");
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		if ("/codeGenerateServlet.do".equals(servletPath)) {
			filterChain.doFilter(request, response);
			return;
		}
		if (session.getAttribute("user") == null) {
			try {
				request.getRequestDispatcher("/userLoginJspServlet.do").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
