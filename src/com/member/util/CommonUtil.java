package com.member.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.member.beans.back.enumData.BatchNoEnum;

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
	
	
	/**
	 * 日期类别统计
	 * @return
	 */
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
	
	
	/**
	 * 流水号获取
	 * @return
	 */
	public static Integer getCountNumber(){
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);
		String no = nowDateStr.substring(0,6);
		return Integer.valueOf(no);
	}
	
	
	/**
	 * 批次号batchNo获取
	 * @return
	 */
	public static BatchNoEnum getBatchNo(){
		Calendar d1 = Calendar.getInstance();
		int day = d1.get(Calendar.DAY_OF_MONTH);
		if(day<=10){
			return BatchNoEnum.FIRST;
		}else if(day<=20){
			return BatchNoEnum.SECOND;
		}else{
			return BatchNoEnum.THREE;
		}
	}
	
	
	
	/**
	 * 金额格式化
	 * @param s 金额
	 * @param len 小数位数
	 * @return 格式后的金额
	 */
	public static String insertComma(String s, int len) {
	    if (s == null || s.length() < 1) {
	        return "";
	    }
	    NumberFormat formater = null;
	    double num = Double.parseDouble(s);
	    if (len == 0) {
	        formater = new DecimalFormat("###,###");
	 
	    } else {
	        StringBuffer buff = new StringBuffer();
	        buff.append("###,###.");
	        for (int i = 0; i < len; i++) {
	            buff.append("#");
	        }
	        formater = new DecimalFormat(buff.toString());
	    }
	    return formater.format(num);
	}

}
