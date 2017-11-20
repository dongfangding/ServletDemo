package module.user.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import module.user.entity.Book;

public class BookDao {
	private static final BookDao bookDao = new BookDao();
	private static Map<String, Book> bookMap = new LinkedHashMap<String, Book>();
	
	private BookDao() {}
	public static BookDao getInstance() {
		return bookDao;
	}
	
	static {
		bookMap.put("1", new Book("1", "Header First JAVA", 60d, "JAVA基础"));
		bookMap.put("2", new Book("2", "Header First 设计模式", 75d, "JAVA设计模式详解"));
		bookMap.put("3", new Book("3", "Spring In Action", 80d, "Spring框架实战应用"));
		bookMap.put("4", new Book("4", "Hibernate In Action", 55d, "Hibernate框架实战应用"));
		bookMap.put("5", new Book("5", "SpringMVC IN Action", 65d, "SpringMVC实战应用"));
		bookMap.put("6", new Book("6", "JAVA WEB实战", 70d, "JAVA WEB实战应用"));
	}
	
	public Collection<Book> getAll() {
		return bookMap.values();
	}
	
	public Book getBookById(String id) {
		return bookMap.get(id);
	}
}
