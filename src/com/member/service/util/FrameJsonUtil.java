package com.member.service.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

 public class FrameJsonUtil {
private static String  dateformat="yyyy-MM-dd HH:mm:ss";
	/**
	 * 获取json操作对象
	 * @return
	 */
	public static ObjectMapper getObjectMapper(){
		
		ObjectMapper om=new ObjectMapper();
		om.setDateFormat(new SimpleDateFormat(dateformat));
		return om;

	}

}
