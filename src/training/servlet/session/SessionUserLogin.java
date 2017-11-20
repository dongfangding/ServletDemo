package training.servlet.session;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionUserLogin
 */
@WebServlet("/sessionUserLogin.do")
public class SessionUserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/**
	 * 模拟用户登录，将用户的sessionId以cookie形式返回到浏览器中
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					throw new RuntimeException("验证码错误！！");
				}
			} else {
				throw new RuntimeException("验证码不能为空！");
			}
			
			SessionUserDao userDao = new SessionUserDao();
			Collection<SessionUser> userList = userDao.getAll();
			for(SessionUser user : userList) {
				if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					session.setAttribute("user", user);
					Cookie cookie = new Cookie("JSESSIONID", session.getId());
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(3600);
					response.addCookie(cookie);
					response.sendRedirect(request.getContextPath() + "/index.jsp");
					return;
				}
			}
			throw new RuntimeException("用户名或密码错误，登录失败！");
		}
	}

}
