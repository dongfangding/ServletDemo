package training.servlet.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestParameterCharset
 */
@WebServlet("/requestCharset.do")
public class RequestCharset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 对于get请求的乱码，只能通过手动修改。默认编码是ISO-8859-1
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String userName = request.getParameter("userName");
		userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(userName);
		response.getWriter().println(userName);
	}
	/**
	 * 接收类型参数，解决POST乱码问题
	 * RequestURL: http://localhost:8081/ServletDemo/pages/request/requestParameter.jsp
	 * 还有一个可以通过Tomcat的连接器Connector配置URIEncoding="UTF-8",通过服务器来配置，受限于服务器。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 只对post请求有效
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		String sex = request.getParameter("sex");
		String []hobby = request.getParameterValues("hobby");
		RequestCharsetUser user = new RequestCharsetUser();
		if(userName != null && !"".equals(userName)) {
			user.setUserName(userName);
		}
		if(password != null && !"".equals(password)) {
			user.setPassword(password);
		}
		if(password1 != null && !"".equals(password1)) {
			user.setPassword1(password1);
		}
		if(sex != null && !"".equals(sex)) {
			user.setSex(sex);
		}
		if(hobby != null && hobby.length > 0) {
			user.setHobby(hobby);
		}
		System.out.println(user.toString());
		test(request, response);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(user.toString());
	}
	
	/**
	 * 如下一段代码并不会发生乱码，获得时候用正确的编码获得后，在改变response的编码以及浏览器的打开编码，保持一致，数据就不会乱码
	 */
	private void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		response.setContentType("text/html;charset=gb2312");
		response.getWriter().print(userName);
	}

}
