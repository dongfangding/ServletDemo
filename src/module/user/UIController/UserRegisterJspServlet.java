package module.user.UIController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.user.utils.Constants;
import module.user.utils.WebUtils;

/**
 * Servlet implementation class UserRegisterJspServlet
 */
@WebServlet("/userRegisterJspServlet.do")
public class UserRegisterJspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 跳转注册界面的servlet
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置表单令牌，用来防止表单重复提交
		String token = WebUtils.generateToken();
		HttpSession session = request.getSession();
		session.setAttribute("token", token);
		
		request.getRequestDispatcher(Constants.PAGE_ROOT + "/form/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
