package training.servlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionRepetSubmit
 */
@WebServlet("/sessionRegistert.do")
public class SessionRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username != null && !"".equals(username) && password != null &&
				!"".equals(password)) {
			/**
			 * 获得页面生成的验证码和用户填写的验证码进行对比
			 */
			HttpSession session = request.getSession();
			String clientToken = request.getParameter("token");
			Object serverToken = session.getAttribute("token");
			if(clientToken != null && !"".equals(clientToken) && serverToken != null) {
				if(!clientToken.equalsIgnoreCase((String) serverToken)) {
					throw new RuntimeException("验证码错误                                                                                                                                     ！！");
				}
			} else {
				throw new RuntimeException("验证码不能为空！");
			}
			
			SessionUserDao userDao = new SessionUserDao();
			userDao.addSessionUser(new SessionUser(username, password));
			request.setAttribute("userList", userDao.getAll());
			request.getRequestDispatcher("/pages/session/userList.jsp").forward(request, response);
		} else {
			throw new RuntimeException("用户名或密码不能为空！");
		}
	}
}
