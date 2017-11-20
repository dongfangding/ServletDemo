package module.user.service;

import module.user.dao.UserDao;
import module.user.entity.User;

public class UserService {
	private UserDao userDao = UserDao.getInstance();
	public User registerUser(User user) throws Exception {
		return userDao.addUser(user);
	}
}
