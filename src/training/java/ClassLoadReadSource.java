package training.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class ClassLoadReadSource {
	/**
	 * (有问题)
	 * 此方法当web资源文件发生改变的时候，并不能读取到最新的数据。
	 * 原因是类加载器加载资源文件的时候发现在此前已经获取到该流对象，
	 * 并不会再次获取而是使用以前的，所以数据不会发生变化。
	 * @param pw
	 */
	public void readResource(PrintWriter pw) {
		InputStream in = null;
		try {
			pw.println("-------ClassLoad readResource------");
			in = ClassLoadReadSource.class.getClassLoader()
					.getResourceAsStream("/WEB-INF/resource/db.properties");
			Properties prop = new Properties();
			prop.load(in);
			Iterator<Entry<Object, Object>> iter = prop.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<Object, Object> entry = iter.next();
				String param = entry.getKey().toString();
				String value = entry.getValue().toString();
				pw.println(param + ": " + value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * (有问题)
	 * @param pw
	 */
	public void readResourceUpdate(PrintWriter pw) {
		InputStream in = null;
		try {
			pw.println("-------ClassLoad readResourceUpdate------");
			String path = ClassLoadReadSource.class.getClassLoader().getResource
					("/WEB-INF/resource/db.properties").getPath();
			in = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(in);
			Iterator<Entry<Object, Object>> iter = prop.entrySet().iterator();
			while(iter.hasNext()) {
				Entry<Object, Object> entry = iter.next();
				String param = entry.getKey().toString();
				String value = entry.getValue().toString();
				pw.println(param + ": " + value);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
