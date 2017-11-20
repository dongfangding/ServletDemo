package training.servlet.request;

import java.util.Arrays;

public class RequestCharsetUser {
	private String userName;
	private String password;
	private String password1;
	private String sex;
	private String []hobby;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	@Override
	public String toString() {
		return "RequestCharsetUser [userName=" + userName + ", password=" + password + ", password1=" + password1
				+ ", sex=" + sex + ", hobby=" + Arrays.toString(hobby) + "]";
	}
}
