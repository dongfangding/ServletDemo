package training.servlet.request;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestParamter
 * RequestURL: http://localhost:8081/ServletDemo/requestParameter.do?userName=ddf&userName=yichen
 */
@WebServlet("/requestParameter.do")
public class RequestParameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getParameterEnum(request, response);
		getParameterValues(request, response);
		getParameterMap(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 获得所有的请求参数值，然后根据参数名获得参数值
	 * @param request
	 * @param response
	 */
	private void getParameterEnum(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("-------------getParameterEnum---------");
		Enumeration<String> paramEnum = request.getParameterNames();
		String name = "";
		while(paramEnum.hasMoreElements()) {
			name = paramEnum.nextElement();
			System.out.println("参数:" + name + ",值：" + request.getParameter(name));
		}
	} 
	
	/**
	 * 获得同名参数值
	 * @param request
	 * @param response
	 */
	private void getParameterValues(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("----------getParameterValues---------");
		String []values = request.getParameterValues("userName");
		if(values != null && values.length > 0) {
			for(String value : values) {
				System.out.println(value);
			}
		}
	}
	
	/**
	 * 获得参数的Map形式
	 * @param request
	 * @param response
	 */
	private void getParameterMap(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("----------getParameterMap-----------");
		Map<String, String[]> paramMap = request.getParameterMap();
		for(Entry<String, String[]> param : paramMap.entrySet()) {
			String []values = param.getValue();
			if(values != null && values.length > 0) {
				for(String value : values) {
					System.out.println(value);
				}
			}
		}
	}
}
