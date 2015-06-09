package com.member.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
	
	/**
	 * 星期日---1  
	 * 星期一---2  
	 * 星期二---3   
	 * 星期三---4  
	 * 星期四---5  
	 * 星期五---6  
	 * 星期六---7  
	 * @return
	 */
	public static int getWeek(){
		  Calendar c = Calendar.getInstance();
		  c.setTime(new Date(System.currentTimeMillis()));
		  int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		  return dayOfWeek;
	}
	
	
	
	public static String getDateNumber(){
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);
		
		int day = d1.get(Calendar.DAY_OF_MONTH);
		String dateNumber="";
		if(day<=10){
			dateNumber="01";
		}else if(day<=20){
			dateNumber="02";
		}else{
			dateNumber="03";
		}
		
		return nowDateStr.substring(0,6)+dateNumber;
	}
	
	

}
