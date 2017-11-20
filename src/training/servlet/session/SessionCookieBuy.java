package training.servlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionCookie
 * Session是基于cookie的，服务器创建每个用户的session时创建sessionId并以cookie的方式回写给 浏览器，
 * 这样当浏览器每次访问的时候携带这sessionId，就可以找到用户对应的服务端创建的session。
 * session默认的时一个浏览器独占一个session对象，并且默认生命为一个会话，浏览器关闭则session清除。
 * 可以通过<session-config>在web.xml中来配置session的不操作的过期时间。
 * 
 * 
 * session与cookie的区别
 * 	Cookie是把用户的数据写给用户的浏览器
 * 	Session把用户的数据写到服务端用户独占的session中。
 */
@WebServlet("/sessionCookieBuy.do")
public class SessionCookieBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 默认的一个浏览器一个session，当浏览器关闭后，session信息丢失
		// browserLife(request);
		// 以cookie的方式存入sessionId,并设置过期时间
		cookieLife(request, response);
		
		
	}
	
	/**
	 * 以cookie的方式存入sessionId,并设置过期时间，当浏览器关闭后，只要sessionId未过期，
	 * 重新启动也能访问到上次操作的数据
	 * @param request
	 * @param response
	 */
	private void cookieLife(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sessioId = session.getId();
		// JSESSIONID是tomcat对sessionId在cookie中存的key值
		Cookie sessionCookie = new Cookie("JSESSIONID", sessioId);
		sessionCookie.setPath(request.getContextPath());
		sessionCookie.setMaxAge(3600);
		response.addCookie(sessionCookie);
		session.setAttribute("sessionCookieBuyName", "洗衣机");
	}
	
	/**
	 * 浏览器关闭后，sessionId失效
	 * @param request
	 */
	private void browserLife(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("sessionCookieBuyName", "洗衣机");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
