package training.servlet.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@WebServlet("/responseImageServlet.do")
public class ResponseImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * response.getWriter() 字符流
	 * response.getOutputStream 字节流
	 * 重点是要告诉浏览器你输出的文件是什么类型，需要浏览器使用什么去解析
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = this.getServletContext();
		String path = servletContext.getRealPath("/WEB-INF/pic/桌面.png");
		OutputStream out = response.getOutputStream();
		File file = new File(path);
		FileInputStream filein = new FileInputStream(file);
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(file.getName(), "UTF-8"));
		byte []buf = new byte[1024];
		int len = 0;
		while((len = filein.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		filein.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
