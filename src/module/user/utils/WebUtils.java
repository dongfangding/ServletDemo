package module.user.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class WebUtils {
	
	/**
	 * 对象全属性拷贝
	 * @param src
	 * @param dist
	 */
	public static void copyBean(Object src, Object dist) throws Exception {
		try {
			// 如果不在这里getClass，会报Object is not instance
			Class<?> srcClazz = src.getClass();
			Class<?> distClazz = dist.getClass();
			Field []fields = srcClazz.getDeclaredFields();
			for(Field field : fields) {
				String upCaseField = field.getName().substring(0, 1).toUpperCase()
					+ field.getName().substring(1);
				Class<?> fieldClazz = field.getType();
				Method setMethod = distClazz.getDeclaredMethod("set" + upCaseField, fieldClazz);
				Method getMethod = srcClazz.getDeclaredMethod("get" + upCaseField);
				Object value = getMethod.invoke(src);
				if(value != null) {
					setMethod.invoke(dist, value);
				}
			}	
		} catch(Exception e) {
			throw new Exception("copy error", e);
		}
	}
	
	/**
	 * 忽略属性拷贝对象
	 * @param src
	 * @param dist
	 * @param ignor
	 * @throws Exception
	 */
	public static void copyBean(Object src, Object dist, String []ignor) throws Exception{
		try {
			// 如果不在这里getClass，会报Object is not instance
			Class<?> srcClazz = src.getClass();
			Class<?> distClazz = dist.getClass();
			Field []fields = srcClazz.getDeclaredFields();
			List<String> ignorList = Arrays.asList(ignor);
			for(Field field : fields) {
				boolean isBreak = false;;
				for(int i = 0; i < ignorList.size(); i ++) {
					if(field.getName().equals(ignorList.get(i))) {
						// ignorList.remove(i);
						isBreak = true;
						break;
					}
				}
				if(!isBreak) {
					String upCaseField = field.getName().substring(0, 1).toUpperCase()
							+ field.getName().substring(1);
					Class<?> fieldClazz = field.getType();
					Method setMethod = distClazz.getDeclaredMethod("set" + upCaseField, fieldClazz);
					Method getMethod = srcClazz.getDeclaredMethod("get" + upCaseField);
					Object value = getMethod.invoke(src);
					if(value != null) {
						setMethod.invoke(dist, value);
					}
				}
			}	
		} catch(Exception e) {
			throw new Exception("copy error");
		}
	}
	
	/**
	 * 对象指定字段进行拷贝
	 * @param source
	 * @param taget
	 * @param copyProperties
	 */
	public static void entityCopy(Object source, Object taget, String[] copyProperties) throws
			Exception {
		try {
			Class<?> sourceClazz = source.getClass();
			Class<?> targetClazz = taget.getClass();
			if(copyProperties != null && copyProperties.length > 0) {
				String updaCaseField = "";
				for (String prop : copyProperties) {
					Field field = sourceClazz.getDeclaredField(prop);
					Class<?> fieldClazz = field.getType();
					updaCaseField = prop.substring(0, 1).toUpperCase() + prop.substring(1);
					Method getMethod = sourceClazz.getDeclaredMethod("get" + updaCaseField);
					Object value = getMethod.invoke(source);
					Method setMethod = targetClazz.getDeclaredMethod("set" + updaCaseField, fieldClazz);
					if (value != null) {
						setMethod.invoke(taget, value);
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("copy error", e);
		}
	}
	
	/**
	 * 设置对象的属性
	 * @param src
	 * @param property
	 * @param value
	 */
	public static void setProperty(Object src, String property, Object value) throws Exception{
		try {
			Class<?> clazz = src.getClass();
			Field field = clazz.getDeclaredField(property);
			Class<?> fieldType = field.getType();
			String upCase = property.substring(0, 1).toUpperCase() + property.substring(1);
			value = fieldType.cast(value);
			Method setMethod = clazz.getDeclaredMethod("set" + upCase, fieldType);
			if(value != null) {
				setMethod.invoke(src, value);
			}
		} catch(Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * MD5编码
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buf = md.digest(str.getBytes());
			BASE64Encoder base64 = new BASE64Encoder();
			return base64.encode(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 生成随机的UUID
	 * @return
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 生成随机令牌
	 * @return
	 */
	public static String generateToken() {
		try {
			String str = System.currentTimeMillis() + "";
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buf = md.digest(str.getBytes());
			BASE64Encoder base64 = new BASE64Encoder();
			return base64.encode(buf);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
