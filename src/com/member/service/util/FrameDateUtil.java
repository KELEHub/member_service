package com.member.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 public class FrameDateUtil {
	/**
	 * 将字符串转换为时间，默认采用"yyyy-MM-dd HH:mm:ss"
	 * @param stringDate 时间字符�?
	 * @return
	 */
	public static Date changeStringToDate(String stringDate) 
	{
		return changeStringToDate(stringDate,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 将字符串转换为时�?
	 * @param stringDate 时间字符�?
	 * @param format 格式
	 * @return
	 */
	public static Date changeStringToDate(String stringDate, String format) {
		if (stringDate == null || format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date resultDate = null;
		try {
			resultDate = sdf.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
			 
			    
		}
		return resultDate;
	}



	
	/**
	 * 将时间转换为字符串，默认采用"yyyy-MM-dd HH:mm:ss"
	 * @param date 时间
	 * @return 字符�?
	 */
	public static String changeDateToString(Date date) 
	{
		return changeDateToString(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 将时间转换为字符�?
	 * @param date 时间
	 * @param format 格式
	 * @return 字符�?
	 */
	public static String changeDateToString(Date date, String format) {
		if (date == null || format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		String resultString = null;
		try {
			resultString = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * 毫秒转化为时间字符串
	 * @param date 时间毫秒
	 * @return 字符�?
	 */
	public static String changeLongToDateString(Long dateLong) 
	{
		return changeLongToDateString(dateLong,"yyyy-MM-dd HH:mm:ss");
	}
	

	/**
	 * 毫秒转化为时间字符串
	 * @param date 时间毫秒
	 * @return 字符�?
	 */
	public static String changeLongToDateString(Long dateLong,String format) 
	{
		if (dateLong == null || format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String resultString = null;
		try {
			resultString = sdf.format(dateLong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}
	
	
	
}
