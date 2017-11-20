package training.servlet.cookie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieShowAllGoods
 */
@WebServlet("/cookieShowAllGoods.do")
public class CookieShowAllGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用来 展示所有的商品和浏览历史记录，每次浏览后，由另一个serlvet来处理浏览历史cookie，再重定向到本servlet来展示最新数据
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 以下处理所有商品
		CookieGoodsDao dao = new CookieGoodsDao();
		Map<String, Goods> goodsList = dao.getAll();
		List<Goods> goodsList1 = new ArrayList<Goods>();
		if(goodsList != null && goodsList.size() > 0) {
			for(Map.Entry<String, Goods> goods : goodsList.entrySet()) {
				goodsList1.add(goods.getValue());
			}
			request.setAttribute("goodsList", goodsList1);
		}
		
		// 以下处理浏览历史
		List<Goods> goodsHistoryList = new ArrayList<Goods>();
		Cookie []cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("goodsHistory".equals(cookie.getName())) {
					String ids = cookie.getValue();
					String []idsArr = ids.split("\\,");
					if(idsArr != null && idsArr.length > 0) {
						for(String id : idsArr) {
							goodsHistoryList.add(dao.getAll().get(id));
						}
						request.setAttribute("goodsHistoryList", goodsHistoryList);
					}
				}
			}
		}
		
		request.getRequestDispatcher("/pages/cookie/showAllGoods.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
