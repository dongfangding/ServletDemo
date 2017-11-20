package module.user.exception;

public class UserExistException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UserExistException() {
		super();
	}

	public UserExistException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
	
}
