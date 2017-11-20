package module.user.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.user.dao.UserDao;
import module.user.entity.User;
import module.user.formBean.UserForm;
import module.user.service.UserService;
import module.user.utils.Constants;
import module.user.utils.WebUtils;

/**
 * 用户注册
 */
@WebServlet("/userRegisterServlet.do")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService us = new UserService();
    private UserDao ud = UserDao.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			HttpSession session = request.getSession(false);
			String token = request.getParameter("token");
			// 验证客户端的令牌和服务器端是否一致
			boolean isRepet = false;
			if(token == null || "".equals(token.trim())) {
				isRepet = true;
			}
			
			if(session == null) {
				isRepet = true;
			} else {
				Object serverToken = session.getAttribute("token");
				if(serverToken == null || !serverToken.toString().equals(token)) {
					isRepet = true;
				}
			}
			if(isRepet) {
				request.setAttribute("message", "请不要重复提交界面！");
				request.getRequestDispatcher(Constants.PAGE_ROOT + "/global/message.jsp").forward(request, response);
				return;
			}
			
			// 封装请求参数
			Enumeration<String> params = request.getParameterNames();
			UserForm uf = new UserForm();
			while(params.hasMoreElements()) {
				String param = params.nextElement();
				if(!"token".equals(param)) {
					String value = request.getParameter(param).trim();
					WebUtils.setProperty(uf, param, value);
				}
			}
			// 检查验证码
			String serverCode = (String) session.getAttribute("code");
			boolean isValid = true;
			if(uf.getCode() == null || "".equals(uf.getCode())) {
				isValid = false;
				uf.getErrors().put("code", "⭐验证码不能为空！⭐");
			} else if(!uf.getCode().equalsIgnoreCase(serverCode)) {
				isValid = false;
				uf.getErrors().put("code", "⭐验证码错误!⭐");
			}
			
			if(isValid && uf.validate()) {
				if(ud.userIsExist(uf.getUsername())) {
					uf.getErrors().put("username", "⭐用户名已存在！⭐");
					request.setAttribute("userForm", uf);
					request.getRequestDispatcher(Constants.PAGE_ROOT + "/form/register.jsp").forward(request, response);
				} else {
					// 使用removed的话，页面退回到注册表单，必须重新刷新，设置新的令牌
					session.removeAttribute("token");
					User newUser = new User();
					WebUtils.copyBean(uf, newUser, new String[]{"password2", "errors", "code"});
					us.registerUser(newUser);
					// 定时跳转界面
					request.setAttribute("message", "恭喜你注册成功！ 网页将在3秒内调回到首页<meta http-equiv='refresh' "
							+ "content='3;url="+request.getContextPath()+"/userIndexJspServlet.do'><br>"
									+ "如没有正常跳转，请<a href='"+request.getContextPath()+"/userIndexJspServlet.do'>点击此处</a>");
					request.getRequestDispatcher(Constants.PAGE_ROOT + "/global/message.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("userForm", uf);
				request.getRequestDispatcher(Constants.PAGE_ROOT + "/form/register.jsp").forward(request, response);
				return;
			}
		} catch(Exception e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher(Constants.PAGE_ROOT + "/global/message.jsp").forward(request, response);
		}
	}

}
