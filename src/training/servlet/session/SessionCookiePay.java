package training.servlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionCookiePay
 */
@WebServlet("/sessionCookiePay.do")
public class SessionCookiePay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		if(session != null) {
			System.out.println(session.getAttribute("sessionCookieBuyName"));
		}
		request.getSession(); 
		// url编码，呆sessionId
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() 
				+ "/pages/session/pay.jsp"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
