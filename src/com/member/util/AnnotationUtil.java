package com.member.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
 * AnnotationUtil:注解工具
 * 
 */
 @SuppressWarnings("unchecked") public class AnnotationUtil {
	public static <T extends Annotation> T getAnnotation(Object object,
			Class<T> annotation) {
		Class<?> clazz = null;
		if (object instanceof Class) {
			clazz = (Class<?>) object;
			return clazz.getAnnotation(annotation);
		}
		if (object != null) {
			clazz = object.getClass();
			if (!clazz.isEnum()) {
				return getAnnotation(clazz, annotation);
			}
			Field field = null;
			try {
				field = clazz.getDeclaredField(object.toString());
			} catch (Exception e) {

				e.printStackTrace();
			}
			if (field == null) {
				return null;
			}

			return field.getAnnotation(annotation);
		}
		return null;
	}
	public static Object getPointParamValue(Class clazz,String fieldName,Class annoClass,String paramName){
		
		try {
		
			Field field = clazz.getDeclaredField(fieldName);
			Annotation anno = field.getAnnotation(annoClass);
			Method method = anno.getClass().getDeclaredMethod(paramName);
			return method.invoke(anno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
}
