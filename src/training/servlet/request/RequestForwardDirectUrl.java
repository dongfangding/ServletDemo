package training.servlet.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestForwardDirectUrl
 */
@WebServlet("/requestForwardDirectUrl.do")
public class RequestForwardDirectUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 转发是给服务端程序使用，所以是当前web应用，不需要加上下文
		request.getRequestDispatcher("/index.jsp");
		// 重定向是给浏览器使用重新访问服务端，所以给浏览器使用的全部需要加全路径。
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
