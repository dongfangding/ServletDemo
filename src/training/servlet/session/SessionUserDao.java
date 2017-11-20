package training.servlet.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SessionUserDao {
	private static Map<String, SessionUser> userMap = new HashMap<String, SessionUser>();
	static {
		userMap.put("ddf", new SessionUser("ddf", "123456"));
		userMap.put("yichen", new SessionUser("yichen", "234567"));
	}
	
	public Collection<SessionUser> getAll() {
		return userMap.values();
	}
	
	public void addSessionUser(SessionUser user) {
		for(SessionUser suser : this.getAll()) {
			if(suser.getUsername().equals(user.getUsername())) {
				throw new RuntimeException("用户名已存在!");
			}
		}
		userMap.put(user.getUsername(), user);
	}
}
