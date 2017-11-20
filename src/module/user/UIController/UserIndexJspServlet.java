package module.user.UIController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserIndexServlet
 */
@WebServlet("/userIndexJspServlet.do")
public class UserIndexJspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * 首页servlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.sendRedirect(request.getContextPath() + "/WEB-INF/module/user/index.jsp");
		// WEB-INF下的资源只有服务器能够访问到，所以必须使用forward。否则访问不到资源。
		request.getRequestDispatcher("/WEB-INF/module/user/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
