package com.member.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;




 public class LanguageUtil {
	//用户登录系统�?设置的语�?
	public static String	systemLanguage	= null;
	//系统后台默认语言
	public static String	defaultLanguage	= "zh_cn";
	public static Map<String,String> languageMap=new HashMap<String, String>();
	static{
		initLanguageConfig();
	}
	/**
	 * 获取根据系统语言环境和nameCode,获取对应的符合语�?��境的nameValue
	 * @param obj enum
	 * @return
	 */
	public static String getLangValue(Object nameCode) {
		Object nameValue=languageMap.get(nameCode.toString());
		if(nameValue==null)
		{
			return nameCode.toString();
		}
		else{
	
			return nameValue.toString() ;
		}
		
	}
	
	public static String getSystemLangName()
	{
		return systemLanguage==null?defaultLanguage:systemLanguage;
		
	}
	
	private static void initLanguageConfig() {
		String langUrl="/config/language/"+getSystemLangName()+".properties";
		Properties p = new Properties();
		try {
			InputStream in = LanguageUtil.class
					.getResourceAsStream(langUrl);
			p.load(in);
		
			Iterator<Entry<Object, Object>> it = p.entrySet().iterator();
			while(it.hasNext())
			{
				Entry<Object, Object> entry=it.next();
				String key= entry.getKey().toString().replaceAll(" ", "");
				String value= entry.getValue().toString();
				languageMap.put(key, value);
			}
		
		
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
