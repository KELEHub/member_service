package com.member.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.fasterxml.jackson.databind.ObjectMapper;

 @SuppressWarnings("unchecked") public class FrameObjectUtil {
	/**
	 * 比较两个对象字符串�?是否相等异常
	 * @param oldValue
	 * @param newValue
	 * @return
	 */
	public  static boolean valueEqual(Object oldValue, Object newValue) {
    
		boolean equal = false;
		try {
			if (oldValue == null) {
				if (newValue == null) {
					equal = true;
				}
			} else {
				if (newValue != null) {

					if (oldValue.toString().equalsIgnoreCase(
							newValue.toString())) {
						equal = true;
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return equal;

	}
	
    /**
     * 深度克隆
     * @param src
     * @return
     */
	public static Object deepClone(Object src) {
		Object o = null;
		try {
			if (src != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(src);
				oos.close();
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bais);
				o = ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return o;
	}
	 /**
     * 计算对象转换成json字符串后的大�?
     * @param data
     * @return
     */
	public static double getObjectJsonStingDoubleSize(Object data) {
		try {
			if (data == null) {
				return 0;
			}
			ObjectMapper om = new ObjectMapper();
			int count = om.writeValueAsBytes(data).length;
			System.gc();
			return count / (1024 * 1024 * 1.0);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
    /**
     * 计算对象转换成json字符串后的大�?
     * @param data
     * @return
     */
	public static int getObjectJsonStingIntSize(Object data) {
		try {
			if (data == null) {
				return 0;
			}
			ObjectMapper om = new ObjectMapper();
			int count = om.writeValueAsBytes(data).length;
			System.gc();
			return count / (1024 * 1024);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
  /**
   *  将字节数组转换成对象
   * @param bytes 字节数组
   * @return 对象
   */
	public static Object ByteToObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);
			obj = oi.readObject();
			bi.close();
			oi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
    /**
     * 将对象转换成字节数组
     * @param obj 要转换的对象
     * @return 字节数组
     */
	public static byte[] ObjectToByte(Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			bytes = bo.toByteArray();
			bo.close();
			oo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}


	/**
	 * 遍历对象的所有属性，将MAP中对应的值赋给对应的属�?; 
	 * 1.不管属�?是否可访问，是否有get set 方法�?都进行遍�?
	 * 2.静�?属�?也会遍历
	 * @param clazz  要转化的对象的class对象
	 * @param map 包含属�?值的map
	 * @return 转换结果对象
	 */
	public static Object convertMapToObject(Class clazz, Map<String,Object> map) {
		try {
			if(map==null||clazz==null)
			{
				return null;
			}
			Object obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				try {
					String name = field.getName();
					Object value = map.get(name);
					if (value != null) {
						boolean accessible = field.isAccessible();
						field.setAccessible(true);
						field.set(obj, value);
						field.setAccessible(accessible);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 遍历对象的所有属性，赋给�?��map;
     * 1.不管属�?是否可访问，是否有get set 方法�?都进行遍�?
     * 2.静�?的属性也会遍历�?
	 * @param obj �?��转化 的对�?
	 * @return 转换的结果MAP
	 */
	public static Map<String,Object> convertObjctToMap(Object obj) {
		try {
			if(obj==null)
			{
				return null;
			}
			Class clazz = obj.getClass();
			Field[] fields = clazz.getDeclaredFields();
			if (fields == null || fields.length == 0) {
				return null;
			}
			Map<String,Object> returnMap = new  HashMap<String,Object>();
			for (Field field : fields) {
				try {
                    String name=field.getName();
					boolean accessible = field.isAccessible();
					field.setAccessible(true);
					Object value = field.get(obj);
					field.setAccessible(accessible);
					returnMap.put(name, value);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将一�?Map<String,Object> 对象转化为一�?JavaBean。有get set 方法的属性就可以参与转换,与属性的访问级别无关。静态的属�? 即使有get set方法 也不会转�?
	 * 
	 * @param type 要转化的JavaBean的class对象
	 *            
	 * @param map  包含属�?值的 map
	 *           
	 * @return 转化出来�?JavaBean 对象
	 */
	public static Object convertMapToBean(Class type, Map<String,Object> map) {
		try {
			if(map==null||type==null)
			{
				return null;
			}
			BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属�?
			Object obj = type.newInstance(); // 创建 JavaBean 对象
			// �?JavaBean 对象的属性赋�?
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				try {
					PropertyDescriptor descriptor = propertyDescriptors[i];
					String propertyName = descriptor.getName();
					if (map.containsKey(propertyName)) {
						// 下面�?��可以 try 起来，这样当�?��属�?赋�?失败的时候就不会影响其他属�?赋�?�?
						Object value = map.get(propertyName);
						descriptor.getWriteMethod().invoke(obj, value);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return obj;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将一�?JavaBean 对象转化为一�?Map。有get set 方法的属性就可以参与转换,与属性的访问级别无关。静态的属�? 即使有get set方法 也不会转�?
	 * 
	 * @param bean  要转化的JavaBean 对象
	 *           
	 * @return 转化出来�?Map<String,Object> 对象
	 */
	public static Map<String,Object> convertBeanToMap(Object bean) {
		try {
			if(bean==null)
			{
				return null;
			}
			Class type = bean.getClass();
			Map<String,Object> returnMap = new  HashMap<String,Object>();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				try {
					PropertyDescriptor descriptor = propertyDescriptors[i];
					String propertyName = descriptor.getName();
					if (!propertyName.equals("class")) {
						Method readMethod = descriptor.getReadMethod();
						if (readMethod != null) {
							Object result = readMethod.invoke(bean);
							returnMap.put(propertyName, result);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 设置对象属�?�?
	 * @param value
	 * @param fieldName
	 * @param obs
	 */
	  public  static void  setObjcetFieldValue(Object value,String fieldName,Object obs)
	  {
		  Object [] obss={obs};
		  setObjcetFieldValue(value,fieldName,obss);
	  }
	/**
	 * 设置对象属�?�?
	 * @param value
	 * @param fieldName
	 * @param obs
	 */
	  public  static void  setObjcetFieldValue(Object value,String fieldName,Object[] obs)
	  {
			try {
				for(Object ob:obs)
			    {
				Field field =ob.getClass().getDeclaredField(fieldName);
				boolean acc = field.isAccessible();
				field.setAccessible(true);
				value=convertValueByFieldType(field.getType(),value);
				field.set(ob,value);
				field.setAccessible(acc);
				}
			} catch (Exception e) {
				// baseController create
				e.printStackTrace();
			} 
	  }
	  
	  public static Object convertValueByFieldType(Class<?> fieldType, Object value) {
			if (value == null) {
				return null;
			}
			if ("".equals(value.toString().trim()))
				return null;
			try {
				if ((value instanceof List)) {
					List valueList = (List) value;
					List backList = new ArrayList();
					for (Iterator localIterator = valueList.iterator(); localIterator
							.hasNext();) {
						Object it = localIterator.next();
						backList.add(convertValueByFieldType(fieldType, it));
					}
					return backList;
				}
				if ((value instanceof Object[])) {
					Object[] valueList = (Object[]) value;
					List backList = new ArrayList();
					for (Object it : valueList) {
						backList.add(convertValueByFieldType(fieldType, it));
					}
					return backList;
				}
				if ((Integer.class.equals(fieldType)) || (Integer.TYPE.equals(fieldType)))
					return Integer.valueOf(Integer.parseInt(value.toString()));
				if ((Double.class.equals(fieldType)) || (Double.TYPE.equals(fieldType)))
					return Double.valueOf(Double.parseDouble(value.toString()));
				if ((Float.class.equals(fieldType)) || (Float.TYPE.equals(fieldType)))
					return Float.valueOf(Float.parseFloat(value.toString()));
				if (Date.class.equals(fieldType))
					 return FrameDateUtil.changeStringToDate(value.toString());
				if (BigDecimal.class.equals(fieldType))
					return BigDecimal.valueOf(Double.parseDouble(value.toString()));
				if ((Short.class.equals(fieldType)) || (Short.TYPE.equals(fieldType)))
					return Short.valueOf(Short.parseShort(value.toString()));
				if ((Long.class.equals(fieldType)) || (Long.TYPE.equals(fieldType)))
					return Long.valueOf(Long.parseLong(value.toString()));
				if ((Boolean.class.equals(fieldType)) || (Boolean.TYPE.equals(fieldType)))
					return Boolean.valueOf(Boolean.parseBoolean(value.toString()));
				if (String.class.equals(fieldType))
					return value.toString();
				if (DetachedCriteria.class.equals(fieldType)) {
					return (DetachedCriteria) value;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return value;
		}
	  
	 


}
