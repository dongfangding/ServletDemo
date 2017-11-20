package training.forward;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JspForwardServlet
 */
@WebServlet("/jspForwardServlet.do")
public class JspForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Enumeration<String> params = request.getParameterNames();
		pw.write("接收到参数<br/>");
		while(params.hasMoreElements()) {
			String param = params.nextElement();
			pw.write(param + ":" + request.getParameter(param));
			pw.write("<br/>");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
