package module.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.user.dao.BookDao;
import module.user.entity.Book;
import module.user.entity.BookCart;
import module.user.entity.User;

/**
 * Servlet implementation class BookBuyServlet
 */
@WebServlet("/bookBuyServlet.do")
public class BookBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDao bookDao = BookDao.getInstance();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings({ "unchecked"})
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("bookId").trim();
		if(bookId != null && !"".equals(bookId)) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				if(session.getAttribute("user") == null) {
					request.getRequestDispatcher("/WEB-INF/module/user/form/login.jsp").forward(request, response);
				} else {
					System.out.println("session:" + session);
					System.out.println(((User) session.getAttribute("user")).getUsername());
					Map<String, BookCart> bookCartList = (Map<String, BookCart>) session.getAttribute("bookCartList");
					Book book = bookDao.getBookById(bookId);
					if(bookCartList == null) {
						BookCart bookCart = new BookCart();
						bookCart.setBook(book);
						bookCart.setNum(1);
						bookCartList = new HashMap<String, BookCart>();
						bookCartList.put(bookId, bookCart);
					} else {
						BookCart bookCart = bookCartList.get(bookId);
						if(bookCart == null) {
							bookCart = new BookCart();
							bookCart.setBook(book);
							bookCart.setNum(1);
							bookCartList.put(bookId, bookCart);
						} else {
							bookCart.setBook(book);
							bookCart.setNum(bookCart.getNum() + 1);
							bookCartList.put(bookId, bookCart);
						}
					}
					session.setAttribute("bookCartList", bookCartList);
					request.getRequestDispatcher("/showBookCartServlet.do").forward(request, response);
				}
			} 
		}
	}
}
