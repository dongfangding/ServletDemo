package training.servlet.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestReferer
 */
@WebServlet("/requestReferer.do")
public class RequestReferer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 利用请求头的referer来实现防盗链.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String referer = request.getHeader("referer");
		System.out.println(referer);
		if(referer == null || "".equals(referer) ||
				!referer.startsWith("http://localhost")) {
			response.sendRedirect("/index.jsp");
		}
		String data = "资源数据";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
