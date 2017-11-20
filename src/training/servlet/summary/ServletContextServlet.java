package training.servlet.summary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import training.java.ClassLoadReadSource;

/**
 * Web容器启动时，会为每个web应用创建一个ServletContext对象，代表整个web应用。
 * ServletConfig对象中维护了ServletContext对象的引用，因此ServletContext
 * 对象可以通过ServletConfig对象来获取
 * 作用
 * 1. 可以存储web应用共享数据
 * 2. 读取资源文件
 */
@WebServlet(
		urlPatterns = { "/servletContextServlet" }
		)
public class ServletContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletContextServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		String projectName = servletContext.getInitParameter("projectName");
		// servletContext中的数据，为整个web应用所共享
		servletContext.setAttribute("projectName", projectName);
		pw.println(servletContext.getAttribute("projectName"));
		// 读取在src目录下的资源文件
		getRourceBySrc(pw);
		// 读取在web根目录下的资源文件
		getRourceByWebInfo(pw);
		// 读取最新的资源文件
		getRourceByPath(pw);
		
		// 如果是java程序，只能通过类加载器去读取文件(有问题)
		ClassLoadReadSource cls = new ClassLoadReadSource();
		cls.readResource(pw);
		cls.readResourceUpdate(pw);
		pw.flush();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 读取在src目录下的资源文件
	 * @param pw
	 */
	private void getRourceBySrc(PrintWriter pw) {
		InputStream in = null;
		try {
			pw.println("---------------getRourceBySrc-----------------");
			ServletContext servletContext = this.getServletContext();
			in = servletContext.getResourceAsStream("/WEB-INF/classes/training/resources/db.properties");
			Properties prop = new Properties();
			prop.load(in);
			Iterator<Entry<Object, Object>> iter = prop.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<Object, Object> entry = iter.next();
				String param = entry.getKey().toString();
				String value = entry.getValue().toString();
				pw.println(param + ": " + value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 读取在web根目录下的资源文件
	 * @param pw
	 */
	private void getRourceByWebInfo(PrintWriter pw) {
		InputStream in = null;
		try {
			pw.println("---------------getRourceByWebInfo-----------------");
			ServletContext servletContext = this.getServletContext();
			in = servletContext.getResourceAsStream("/WEB-INF/resource/db.properties");
			Properties prop = new Properties();
			prop.load(in);
			Iterator<Entry<Object, Object>> iter = prop.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<Object, Object> entry = iter.next();
				String param = entry.getKey().toString();
				String value = entry.getValue().toString();
				pw.println(param + ": " + value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 通过文件路径来读取资源文件
	 * @param pw
	 */
	private void getRourceByPath(PrintWriter pw) {
		InputStream in = null;
		try {
			pw.println("---------------getRourceUpdate-----------------");
			ServletContext servletContext = this.getServletContext();
			String path = servletContext.getRealPath("/WEB-INF/resource/db.properties");
			in = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(in);
			Iterator<Entry<Object, Object>> iter = prop.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<Object, Object> entry = iter.next();
				String param = entry.getKey().toString();
				String value = entry.getValue().toString();
				pw.println(param + ": " + value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
