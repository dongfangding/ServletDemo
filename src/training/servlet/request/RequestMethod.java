package training.servlet.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestMethod
 */
@WebServlet("/requestMethod")
public class RequestMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientAddress = request.getRemoteAddr();
		String clientHost = request.getRemoteHost();
		int clientPort = request.getRemotePort();
		String clientUser = request.getRemoteUser();
		String queryString = request.getQueryString();
		String method = request.getMethod();
		System.out.println(clientHost+clientAddress+clientPort+clientUser+queryString+method);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
