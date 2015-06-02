package com.member.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 public class FrameStringUtil {
	public static String upper(String s) {
		return upper(s, 1);
	}
	public static boolean isEmptyString(String value)
	{
		if(value==null)
		{
			return false;
		}
		if(value.replaceAll(" ", "").equals(""))
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	public static String upper(String s, int len) {
		if (len < 0) {
			len += s.length();
		}
		if (len < 0) {
			return "";
		}
		return s.substring(0, len).toUpperCase() + s.substring(len);//将首字母大写 其他的不�?
	}
	
	public static String lower(String s) {
		return lower(s, 1);
	}
	
	public static String lower(String s, int len) {
		if (len < 0) {
			len += s.length();
		}
		if (len < 0) {
			return "";
		}
		return s.substring(0, len).toLowerCase() + s.substring(len);
	}
	
	public static String format(String s, Object...args) {
		int len = args.length;
		for (int i = 0; i < len; i++) {
			String value = args[i] != null ? args[i].toString() : "";
			s = s.replaceAll("\\{" + i + "\\}", value);
		}
		return s;
	}
	
	public static String getOidString(String oidString) {
		if (oidString == null) {
			return null;
		}
		int len = oidString.length();
		int index = oidString.indexOf("@");
		index = index != -1 ? index : len;
		oidString = oidString.substring(0, index);
		len = oidString.length();
		index = oidString.indexOf(":");
		index = index != -1 ? index : len;
		return oidString.substring(0, index);
	}
	
	public static String encode(String s) {
		if (s == null) {
			return null;
		}
		String[][] map = new String[][]{{"\\", "\\\\"}, {"\n", "\\n"}};
		for (String[] item : map) {
			s = s.replace(item[0], item[1]);
		}
		return null;
	}

	public static boolean isIP(String s) {
		if (s == null) {
			return false;
		}
		s = s.replaceAll("\\s", "");
		return s.matches("^[0-9]{1,3}(\\.[0-9]{1,3}){3}$");
	}
	
	public static boolean isMac(String s) {
		if (s == null) {
			return false;
		}
		s = s.replaceAll("\\s", "");
		return s.matches("^[\\da-fA-F]{2}(:[\\da-fA-F]{2}){5}$");
	}
	/**
	 * 判断字符中是否有中文
	 * @param s
	 * @return
	 */
	public static boolean isUTF8(String s){
		boolean returnData=false;
		if(s==null){
			return false;
		}
		s=s.replaceAll("\\s", "");
		for(int i=0;i<s.length();i++){
			if(String.valueOf(s.charAt(i)).matches("[\u4e00-\u9fa5]")){
				returnData=true;
				break;
			}
		}
		return returnData;
	}
	/**
	 * 16进制的mac转换�?0进制的现实mac
	 * @param mac
	 * @return
	 */
	public static String getMacIndex(String mac){
		String macIndex="";
		String macs[]=mac.split(":");
		for(int id=0;id<macs.length;id++){
			int index=Integer.parseInt(macs[id],16);
			if(id==macs.length-1){
				macIndex+=index;
			}else{
				macIndex+=index+".";
			}
			
		}
		return macIndex;
	}
	
    /*** 
     * 判断 String 是否�?int 
     *  
     * @param input 
     * @return 
     */  
    public static boolean isInteger(String input){ 
    	if(input==null)
    	{
    		return false;
    	}
        Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);  
        return mer.find();  
    }  
}
