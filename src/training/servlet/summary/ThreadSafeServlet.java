package training.servlet.summary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该例用来显示serlvet的线程安全问题
 * 由于serlvet在被用户请求的时候只会被创建一次，随后驻留在内存中以供后续的请求使用。所以如果在serlvet的类创建了
 * 类成员变量共享数据，就会引发线程安全问题。而由于每个请求都会创建新的HttpServletRequest和HttpServletResponse对象，
 * 所以正常的在doXXX方法中写的数据则不会被共享。
 */
@WebServlet("/threadSafeServlet")
public class ThreadSafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int i = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw = response.getWriter();
			pw.println(request.getContextPath()+request.getServletPath());
			i ++;
			Thread.sleep(1000*3);
			// 事实上对于每个客户端假定有一个独有的i变量，并不期望别的客户端的操作会对数据造成改变和影响。
			// 那么在这里如果有两个请求同时访问这个servlet的话，第二个servlet就会得到第一个servelt已经处理过的错误数据。
			// 这里直接写数字会导致下载，不知道为什么。
			pw.println(i+"----------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
