package module.user.formBean;

import java.util.HashMap;
import java.util.Map;

public class UserForm {
	private String username;
	private String password;
	private String password2;
	private String nickname;
	private String code;
	// 记录验证出错信息
	private Map<String, String> errors = new HashMap<String, String>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	/**
	 * 提供验证的方法（防止客户端绕过js验证）
	 */
	public boolean validate() {
		boolean isValid = true;
		if(username == null || "".equals(username)) {
			isValid = false;
			errors.put("username", "⭐用户名不能为空！⭐");
		} else if(!username.matches("[a-zA-Z0-9]{3,12}")){
			isValid = false;
			errors.put("username", "⭐用户名必须为3到8位字母！⭐");
		}
		if(password == null || "".equals(password)) {
			isValid = false;
			errors.put("password", "⭐密码不能为空！⭐");
		} else {
			if(!password.matches("[a-zA-Z0-9]{6,14}")) {
				isValid = false;
				errors.put("password", "⭐密码必须为6到14位并且不能包含特殊字符⭐");
			}
		}
		
		if(password2 == null || "".equals(password2)) {
			isValid = false;
			errors.put("password2", "⭐确认密码不能为空！⭐");
		} else {
			if(password != null && !"".equals(password)) {
				if(!password2.equals(password)) {
					isValid = false;
					errors.put("password", "⭐两次输入的密码不一致⭐");
				}
			}
		}
		
		if(nickname != null && !"".equals(nickname)) {
			if(nickname.length() < 2 || nickname.length() > 12) {
				isValid = false;
				errors.put("nickname", "⭐昵称需要位于2和12个字符之间！⭐");
			}	
		}
		
		if(code == null || "".equals(code)) {
			isValid = false;
			errors.put("code", "⭐验证码不能为空！⭐");
		}
		return isValid;
 	}
	@Override
	public String toString() {
		return "UserForm [username=" + username + ", password=" + password + ", password2=" + password2 + ", nickname="
				+ nickname + ", errors=" + errors + "]";
	}
}
