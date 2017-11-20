package training.servlet.cookie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GoodsDetail
 */
@WebServlet("/goodsDetail.do")
public class GoodsDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id != null && !"".equals(id)) {
			CookieGoodsDao dao = new CookieGoodsDao();
			Goods goods = dao.getAll().get(id);
			if(goods != null) {
				// 以下处理浏览历史
				List<Goods> goodsHistoryList = new ArrayList<Goods>();
				Cookie []cookies = request.getCookies();
				String joinId = "";
				String goodsHistory = null;
				if(cookies != null && cookies.length > 0) {
					for(Cookie cookie : cookies) {
						if("goodsHistory".equals(cookie.getName())) {
							goodsHistory = cookie.getValue();
							break;
						}
					}
					if(goodsHistory == null) {
						joinId = id;
					} else {
						// 最多带三个历史记录，每次都把最新的先展示
						LinkedList<String> idsList = new LinkedList<String>(Arrays.asList(
								goodsHistory.split("\\,")));
						if(idsList.contains(id)) {
							idsList.remove(id);
							idsList.addFirst(id);
						} else {
							if(idsList.size() >= 3) {
								idsList.removeLast();
								idsList.addFirst(id);
							} else {
								idsList.addFirst(id);
							}
						}
						for(String listId : idsList) {
							if(joinId != "") {
								joinId += ",";
							}
							joinId += listId;
						}
					}
					request.setAttribute("goodsHistoryList", goodsHistoryList);
					
					Cookie cookie = new Cookie("goodsHistory", joinId);
					cookie.setMaxAge(24*3600);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
					// 这里必须使用重定向，如果是转发的话，浏览器不请求，则不能更新最后一条请求的浏览数据。
					response.sendRedirect(request.getContextPath() + "/cookieShowAllGoods.do");
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
