package module.user.service;

import java.util.Collection;

import module.user.dao.BookDao;
import module.user.entity.Book;

public class BookService {
	private BookDao bookDao = BookDao.getInstance();
	
	public Collection<Book> getAll() {
		return bookDao.getAll();
	}
}
