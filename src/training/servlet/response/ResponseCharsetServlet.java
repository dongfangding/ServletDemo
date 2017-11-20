package training.servlet.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseCharsetServlet.do")
public class ResponseCharsetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 输出中文的问题(编码必须写在获取PrintWriter流的前面)
	 * getOutputStream和getWriter这两个方法互斥，调用了其中一个方法，再调用另外一个就会报错。
	 * Servlet程序向ServletOutputStream和PrintWriter对象写入的数据将被Servlet引擎从
	 * Response对象中获取，然后Servlet引擎将这些数据当作响应消息的正文，然后在与响应状态行和各相应头
	 * 组合后输出给客户端
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*// 控制response中的数据使用什么码表
		response.setCharacterEncoding("UTF-8");
		// 控制浏览器使用什么样的码表来打开数据
		response.setHeader("content-type", "text/html;charset=UTF-8");*/
		// 作用等同上面两句
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("PrintWriter 输出中文!");
		pw.flush();
		// Servlet的service方法结束后，Servlet引擎将检查输出流对象是否已调用close方法，如果没有
		// Servlet引擎将调用close方法来关闭输出流
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
