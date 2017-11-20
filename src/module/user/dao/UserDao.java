package module.user.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import module.user.entity.User;
import module.user.utils.WebUtils;

public class UserDao {
	private static Map<String, User> userMap = new LinkedHashMap<>();
	private static final UserDao userDao = new UserDao();
	private UserDao() {}
	
	public static UserDao getInstance() {
		return userDao;
	}
	/**
	 * 初始化一条用户记录
	 */
	static {
		userMap.put("1", new User("1", "admin", WebUtils.md5("123456"), "管理员"));
	}
	
	public Collection<User> getAll() {
		return userMap.values();
	}
	
	public User getUserById(String id) {
		return userMap.get(id);
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User addUser(User user) throws Exception {
		User newUser = new User();
		WebUtils.copyBean(user, newUser);
		newUser.setPassword(WebUtils.md5(newUser.getPassword()));
		newUser.setId(WebUtils.generateUUID());
		userMap.put(newUser.getId(), newUser);
		return newUser;
	}
	
	/**
	 * 根据用户名和密码获得用户
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password) {
		Collection<User> userList = getAll();
		for(User us : userList) {
			if(us.getUsername().equals(username) && us.getPassword().equals(password)) {
				return us;
			}
		}
		return null;
	}
	
	/**
	 * 验证是否存在相同的用户名
	 * @param username
	 * @return
	 */
	public boolean userIsExist(String username) {
		Collection<User> userList = getAll();
		for(User us : userList) {
			if(us.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
