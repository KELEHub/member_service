package com.member.util;

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

}
