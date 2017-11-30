package training.ResourceBundle;


import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class FirstResourceBundle {
	

	@Test
	public void test() {
		Locale locale = Locale.getDefault();
		// 直接资源文件名就可以，不需要后缀
		ResourceBundle resource = ResourceBundle.getBundle("message", locale);
		String str = "读取资源文件第一个参数{0},读取资源文件第二个参数{1}";
		
		String param1 = resource.getString("CAN_NOT_FIND_PAGE");
		String param2 = resource.getString("UNKNOW_ERROR");
		
		str = MessageFormat.format(str, param1, param2);
		System.out.println("本次默认资源文件： " + locale.getLanguage() + ", " + str);
		
		locale = Locale.US;
		resource = ResourceBundle.getBundle("message", locale);
		str = "读取资源文件第一个参数{0},读取资源文件第二个参数{1}";
		
		param1 = resource.getString("CAN_NOT_FIND_PAGE");
		param2 = resource.getString("UNKNOW_ERROR");
		
		str = MessageFormat.format(str, param1, param2);
		System.out.println("本次默认资源文件： " + locale.getLanguage() + ", " + str);
		
	}
	
}
