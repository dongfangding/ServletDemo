package training.servlet.summary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 演示ServletConfig对象
 * 当Servlet配置了初始化参数之后，web容器在创建serlvet实例对象的时候，会自动将初始化参数封装
 * 到ServletConfig对象之中，并在调用servlet的init方法的时候，将ServletConfig传递给servlet完成实例化。
 * 可以通过ServletConfig对象得到serlvet的初始化参数。
 */
@WebServlet(
		urlPatterns = { "/servletConfigServlet" }, 
		initParams = { 
				@WebInitParam(name = "author", value = "ddf"), 
				@WebInitParam(name = "email", value = "1041765757@qq.com")
		})
public class ServletConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println(request.getContextPath()+request.getServletPath());
		// 获得所有的初始化参数，实际上该行 语句底层使用的就是SerlvetConfig对象
		// Enumeration<String> params = this.getInitParameterNames();
		ServletConfig servletConfig = this.getServletConfig();
		Enumeration<String> params = servletConfig.getInitParameterNames();
		while(params.hasMoreElements()) {
			// 获得初始化参数的名
			String name = params.nextElement();
			// 获得初始化参数的值
			pw.println(name + ":" + this.getInitParameter(name));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
