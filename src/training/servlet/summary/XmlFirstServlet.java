package training.servlet.summary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 需要在web.xml做servlet映射，才能被客户端访问
 * Servlet的生命周期相关的方法life-cycle method
 * 1. servlet一旦被请求，将加载Servlet相关的config，进行创建，接着就会调用它的init方法
 * 2. 客户端任意的请求都会导致service方法被执行
 * 3. service方法处理完请求后，servlet的destory方法将被调用来摧毁servlet，然后垃圾回收期回收。
 * 
 * 针对客户端的多次Servlet请求，通常情况下，服务器只会创建一次Servlet，直至web容器退出，servlet实例对象才会被销毁
 * 也就是说在整个servlet的生命周期内，init方法只会被调用一次，而每次针对servlet的请求，
 * 每次都会重新创建HttpServletRequest对象和HttpServletResponse对象，作为参数给service方法调用。
 * HttpServlet封装了service方法，会根据客户端的请求去调用doXXX方法。所以通常只需要继承HttpServlet该类，
 * 去重写对应的doXXX方法即可。
 * @author DingDongfang
 *
 */
public class XmlFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户端请求的时候才会创建该servelt实例
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("XmlFirstServlet init");
		super.init();
	}

	@Override
	public void destroy() {
		System.out.println("XmlFirstServlet destory");
		super.destroy();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("XmlFirstServlet Served at: ").append(request.getContextPath()+request.getServletPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
