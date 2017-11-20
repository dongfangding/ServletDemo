package training.servlet.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieSumary
 * Cookie是客户端技术，程序把每个用户的数据以cookie的形式写给浏览器，当下次浏览器再访问服务端的时候就
 * 会带着写好的cookie来访问服务器。
 * 默认情况下,cookie是一个会话级别的cookie,如果希望能够保存到硬盘中，需要使用maxAge方法，参数为0时是删除cookie>
 * 删除cookie的时候必须保证path和设置cookie时的path一致，否则不会删除。
 * 一般浏览器只允许存放300个cookie，每个站点最多存放20个cookie，每个cookie的大小为4kb
 * 
 * 可以用来做自动登录
 */
@WebServlet("/cookieSumary.do")
public class CookieSumary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Cookie []cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("lastAccessTime".equals(cookie.getName())) {
					pw.println("您上次访问的时间是:" + cookie.getValue());
				}
			}
		}
		
		Cookie cookie = new Cookie("lastAccessTime", new Date().toLocaleString());
		// 设置浏览器保存cookie的最大时间，默认一个会话期
		cookie.setMaxAge(24*3600);
		// 设置浏览器请求携带cookie的路径，默认为Servet所在的路径，指定为整个web应用
		cookie.setPath(request.getContextPath()); 
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
