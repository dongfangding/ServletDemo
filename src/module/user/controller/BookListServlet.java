package module.user.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.user.entity.Book;
import module.user.service.BookService;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/bookListServlet.do")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Collection<Book> bookList = bookService.getAll();
		if(bookList != null && bookList.size() > 0) {
			request.setAttribute("bookList", bookList);
		}
		request.getRequestDispatcher("/WEB-INF/module/user/list/bookList.jsp").forward(request, response);
	}

}
