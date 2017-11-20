package module.user.test;


import org.junit.Test;

import module.user.dao.UserDao;
import module.user.entity.User;
import module.user.exception.UserExistException;
import module.user.service.UserService;

public class UserTest {
	private UserService userService = new UserService();
	private UserDao userDao = new UserDao();
	@Test
	public void testAddUser() {
		User user = new User("2", "yichen", "123456", "逸尘");
		try {
			userService.registerUser(user);
			for(User us: userDao.getAll()) {
				System.out.println(us.getUsername());
			}
		} catch (UserExistException e) {
			e.printStackTrace();
		}
	}
}
