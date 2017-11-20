package module.user.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.user.dao.UserDao;
import module.user.entity.User;
import module.user.utils.WebUtils;

/**
 * 用户登录
 */
@WebServlet("/userLoginServlet.do")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = UserDao.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Map<String, String> errors = new HashMap<String, String>();
		boolean isValid = true;
		if (username == null || "".equals(username.trim())) {
			errors.put("username", "⭐用户名不能为空！⭐");
			isValid = false;
		}

		if (password == null || "".equals(password.trim())) {
			errors.put("password", "⭐密码不能为空！⭐");
			isValid = false;
		}

		if (isValid) {
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("user");
			User user = userDao.getUser(username, WebUtils.md5(password));
			if (user != null) {
				// 判断用户是否重复登录
				if(loginUser == null || !loginUser.getUsername().equals(user.getUsername())) {
					session.setAttribute("user", user);
					Cookie[] cookies = request.getCookies();
					String times = null;
					// 判断lastLoginDate的cookie是否属于当前的用户
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals(username+"LastLoginDate")) {
							times = cookie.getValue();
							Date date = new Date(Long.parseLong(times));
							session.setAttribute(username+"LastLoginDate", date.toLocaleString());
						}
					}
					// 回送当前用户最新登录时间
					Cookie loginDateCookie = new Cookie(username+"LastLoginDate", System.currentTimeMillis() + "");
					loginDateCookie.setMaxAge(60 * 1 * 60);
					loginDateCookie.setPath(request.getContextPath());
					response.addCookie(loginDateCookie);
					request.getRequestDispatcher("/userIndexJspServlet.do").forward(request, response);
				} else {
					// request.setAttribute("loginRepet", "请不要重复登录！");
					request.getRequestDispatcher("/userIndexJspServlet.do").forward(request, response);
				}
			} else {
				errors.put("username", "用户名或密码不匹配！");
				isValid = false;
			}
		}

		if (!isValid) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/userLoginJspServlet.do").forward(request, response);
		}
	}
}
