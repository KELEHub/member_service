package com.member.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.member.beans.back.enumData.BatchNoEnum;
import com.member.beans.back.enumData.GiftEnum;

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
		}else if(day<=30){
			dateNumber="03";
		}
		if(day==31){
			dateNumber="01";
		}
		return nowDateStr.substring(0,6)+dateNumber;
	}
	
	
	/**
	 * 礼包类型获取
	 * @return
	 */
	public static GiftEnum getGiftEnum(){
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);
		
		int day = d1.get(Calendar.DAY_OF_MONTH);
		String dayStr= String.valueOf(day);
		if(dayStr.length()<2){
			dayStr="0"+dayStr;
		}
		int dayNumber = Integer.valueOf(nowDateStr.substring(4,6)+dayStr);
		if(dayNumber>410){
			return GiftEnum.TEN;
		}else{
			return GiftEnum.FIVE;
		}
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
	 * 服务积分专用
	 * @return
	 */
	public static Integer getServerCountNumber(){
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);
		return Integer.valueOf(nowDateStr);
	}
	
	
	
	/**
	 * 获取当前年
	 * @return
	 */
	public static Integer getYearNumber(){
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);
		String no = nowDateStr.substring(0,4);
		return Integer.valueOf(no);
	}
	
	/**
	 * 获取当前月
	 * @return
	 */
	public static Integer getMounthNumber(){
		Calendar d1 = Calendar.getInstance();
		Date nowDate = d1.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDateStr = format.format(nowDate);
		String no = nowDateStr.substring(4,6);
		return Integer.valueOf(no);
	}
	
	/**
	 * 获取当前日
	 * @return
	 */
	public static Integer getDay(){
		Calendar d1 = Calendar.getInstance();
		int day = d1.get(Calendar.DAY_OF_MONTH);
		return day;
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
		}else if(day<=30){
			return BatchNoEnum.THREE;
		}
		if(day==31){
			return BatchNoEnum.FIRST;
		}
		return null;
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
