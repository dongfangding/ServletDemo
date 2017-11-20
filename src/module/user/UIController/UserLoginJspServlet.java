package module.user.UIController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.user.utils.Constants;

/**
 * Servlet implementation class UserLoginJspServlet
 */
@WebServlet("/userLoginJspServlet.do")
public class UserLoginJspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 跳转到登录界面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 失效原session
		request.getSession().invalidate();
		request.getRequestDispatcher(Constants.PAGE_ROOT + "/form/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
