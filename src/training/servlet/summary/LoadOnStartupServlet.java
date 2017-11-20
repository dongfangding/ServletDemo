package training.servlet.summary;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoadOnStartupServlet用来实现init-param和load-on-startup两个配置的作用
 * init-param可以用来为servlet配置初始化参数
 * load-on-startup可以使serlvet在web服务器初始化的时候就会实例化servlet，而不是客户端请求的时候。
 * @author DingDongfang
 *
 */
public class LoadOnStartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * web容器创建的时候就会创建该servlet实例
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("LoadOnStartupServlet init ");
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println(request.getContextPath()+request.getServletPath());
		// 获得所有的初始化参数
		Enumeration<String> params = this.getInitParameterNames();
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
