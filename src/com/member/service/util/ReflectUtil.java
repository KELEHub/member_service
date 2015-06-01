package com.member.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


 public class ReflectUtil {
	private final static Log log = LogFactory
	.getLog(ReflectUtil.class);
	public static String getter(String field) {
		return "get" + FrameStringUtil.upper(field);
	}
	
	public static Method getter(Class<?> clazz, String field)
	throws SecurityException, NoSuchMethodException {
		return clazz.getMethod(getter(field));
	}
	
	public static Method getter(Object instance, String field)
	throws SecurityException, NoSuchMethodException {
		return getter(instance.getClass(), field);
	}
	
	public static String setter(String field) {
		return "set" +FrameStringUtil.upper(field);
	}
	
	public static Method setter(Class<?> clazz, String field)
	throws SecurityException, NoSuchFieldException, NoSuchMethodException {
		return setter(clazz, field, null);
	}
	
	public static Method setter(Class<?> clazz, String field, Class<?> type)
	throws SecurityException, NoSuchFieldException, NoSuchMethodException {
		if (type == null) {
			Field f = clazz.getField(field);
			type = f == null ? null : f.getType();
		}
		return clazz.getMethod(setter(field), type);
	}
	
	public static Method setter(Object instance, String field)
	throws SecurityException, NoSuchFieldException, NoSuchMethodException {
		return setter(instance.getClass(), field, null);
	}
	
	public static Method setter(Object instance, String field, Class<?> type)
	throws SecurityException, NoSuchFieldException, NoSuchMethodException {
		return setter(instance.getClass(), field, type);
	}
	
	public static Object get(Class<?> clazz, String name) {
		return get(clazz, null, name);
	}
	
	public static Object getFieldValue(Object instance, String name) {
		return get(null, instance, name);
	}
	
	private static Object get(Class<?> clazz, Object instance, String name) {
		Object value = null;
		if (clazz == null && instance != null) {
			clazz = instance.getClass();
		}
		try {
			Method m = getter(clazz, name);
			m.setAccessible(true);
			value = m.invoke(instance);//调用Method对象m代表的方法，返回值为m代表的方法的返回值（�?��为getIp(),getMac等方法的返回值）
		} catch (Exception e1) {
			e1.printStackTrace();
			LogUtil.logError(log, e1);
			try {
				//Get方法不存在则，直接取属�?的�?.这种取�?方式必须�?属�?的访问级别设为True
				Field f = clazz.getDeclaredField(name);
				boolean accessible = f.isAccessible();
				f.setAccessible(true);
		
				value = f.get(instance);
				f.setAccessible(accessible);
			} catch (Exception e2) {
				e2.printStackTrace();
				LogUtil.logError(log, e2);
			}
		}
		return value;
	}
	
	public static void set(Class<?> clazz, String name, Object value) {
		set(clazz, null, name, value);
	}
	
	public static void setFieldValue(Object instance, String name, Object value) {
		set(null, instance, name, value);
	}
	
	private static void set(Class<?> clazz, Object instance, String name, Object value) {
		if (clazz == null && instance != null) {
			clazz = instance.getClass();
		}
		try {
			Method m = setter(clazz, name,
					value != null ? value.getClass() : null);
			m.setAccessible(true);
			m.invoke(instance, value);
		} catch (Exception e1) {
			try {
				Field f = clazz.getDeclaredField(name);
				boolean accessible = f.isAccessible();
				f.setAccessible(true);
				f.set(instance, value);
				f.setAccessible(accessible);
			} catch (Exception e2) {
				e2.printStackTrace();
				LogUtil.logError(log, e2);
			}
		}
	}
	
	public static Field[] fields(Class<?> clazz, Class<?> type) {
		ArrayList<Field> fields = new ArrayList<Field>();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getType() == type) {
				fields.add(field);
			}
		}
		return fields.toArray(new Field[fields.size()]);
	}
	
	public static boolean was(Class<?> clazz0, Class<?> clazz1) {
		if (clazz0 == null) {
			return clazz1 == null;
		}
		return clazz0.equals(clazz1) || clazz0.isAssignableFrom(clazz1);
	}
}
