package com.member.helper.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;



 public class BaseController {
  protected void  updateObjcetFieldValue(Object value,String fieldName,Object[] obs)
  {
		try {
			for(Object ob:obs)
		    {
			Field field =ob.getClass().getDeclaredField(fieldName);
			boolean acc = field.isAccessible();
			field.setAccessible(true);
			field.set(ob,value);
			field.setAccessible(acc);
			}
		} catch (Exception e) {
			// baseController create
			e.printStackTrace();
		} 
  }
  
  protected void  updateObjcetFieldValue(Object value,String fieldName,List<Object> obs)
  {
		try {
			for(Object ob:obs)
		    {
			Field field =ob.getClass().getDeclaredField(fieldName);
			boolean acc = field.isAccessible();
			field.setAccessible(true);
			field.set(ob,value);
			field.setAccessible(acc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
  }
	public  void handleException(Map<String,Object> returnMap, Exception e) {
	    if(e.getMessage()==null)
	    {
	    	return;
	    }
		// 框架异常，只有框架自定义的异常才展示给用户�?不要把没有定义的异常暴露给用户了 
		if (e.getMessage().contains("FME_")) {
			returnMap.put("returnMsg", e.getMessage());
			returnMap.put("exceptionCode","");

		}
		
	
}
}
