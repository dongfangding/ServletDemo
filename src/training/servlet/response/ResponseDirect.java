package training.servlet.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseDirect
 */
@WebServlet("/responseDirect.do")
public class ResponseDirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 重定向的特点
	 * 1. 浏览器地址栏会发生变化，重定向后的数据需要向服务器发送请求，所以重定向会发生两次请求
	 * 2. 两次请求的request与response是不共享的。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 跳转是针对浏览器的,浏览器访问资源要加上web应用
		response.sendRedirect("/ServletDemo/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
