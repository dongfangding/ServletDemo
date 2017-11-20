package training.servlet.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@WebServlet("/requestForward.do")
public class RequestForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 如果在调用forward之前，Servlet已经将数据真正的写入到了客户端，那么就会报异常
	 * Cannot forward after response has been committed
	 * 如果只是被写入到缓冲区，那么forward方法将能够正常运行，forward之后原来写入的数据将会被清空
	 * 已经写入HttpServletResponse对象中的相应头字段能够继续保持有效
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setAttribute("userName", "丁东方");
		request.getRequestDispatcher("/pages/request/forward.jsp").forward(request, response);*/
		test1(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 以下代码会报错
	 * Cannot forward after response has been committed
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void test1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		// 这段数据会在转发时被清空
		pw.println("测试写入数据！");
		// 这里如果不主动输出缓冲区，那么就不会报异常
		pw.flush();
		request.getRequestDispatcher("/pages/request/forward.jsp").forward(request, response);
	}
}
