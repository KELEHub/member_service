package com.member.util;

import org.apache.commons.logging.Log;
import org.apache.log4j.MDC;

 public class LogUtil {
	/**
	 * 记录用户异常日志、系统异常日志�?用户异常日志 即会存到用户异常日志表中 也会存到系统异常日志表中
	 * @param log
	 * @param e
	 */
	public  static void logError(Log log,Exception e) {
		
		logError(null,log,e);
	}
	/**
	 * 记录用户异常日志，也记录系统异常日志
	 * @param errorRemark
	 * @param log
	 * @param e
	 */
	public  static void logError(String errorRemark,Log log,Exception e) {
		//自定义异�?，则�?��插入用户异常日志表中
		if(e.getMessage()!=null&&e.getMessage().startsWith("FME_"))
		{
			logErrorForUser(log,e);
		}
		StringBuffer errorBuffer = new StringBuffer();
		if(errorRemark!=null)
		{
		  errorBuffer.append(errorRemark + ":");
		}
		errorBuffer.append(	e.getClass().getName()+":"+e.getMessage());
		StackTraceElement[] errors = e.getStackTrace();
		if (errors != null && errors.length != 0) {
			errorBuffer.append("\n"+"-FrameExceptionHappenAt->"+"\n");
			int happenLine = 0;
			String happenMethd = null;
			String happenClass = null;
			boolean findHappen = false;
			StringBuffer atBuffer= new StringBuffer();
			for (StackTraceElement error : errors) {
				String className = error.getClassName();
				int errorLime = error.getLineNumber();
				String methodName = error.getMethodName();
				if (className.startsWith("com.web.jz.")) {
					if (!findHappen) 
					{
						happenClass=className;
						happenLine = errorLime;
						happenMethd = methodName;
						findHappen=true;
					}
				}
				atBuffer.append("at-"+className+"/"+methodName+"/"+errorLime+";"+"\n");
			}
		
		      MDC.put("happenClass", happenClass);
		      MDC.put("happenLine", happenLine);
		      MDC.put("happenMethd", happenMethd);
			atBuffer.insert(0, "happen-"+happenClass+"/"+happenMethd+"/"+happenLine+";"+"\n");
			String atString = atBuffer.toString();
			if(atString.endsWith(";"))
			{
				atString=atString.substring(0,atString.length()-1);
			}
			errorBuffer.append(atString);
		    
			
		}
		String errorString =errorBuffer.toString();
        MDC.put("detailMsg",errorString);
        MDC.put("handled",false);
		log.error(errorRemark,e);
	}
	//记录自定义异常�?记录  异常ID 异常CODE 异常NAME（国际化�?异常 触发人ID   异常发生时间
	private static void logErrorForUser(Log log, Exception e) {
	
   	System.out.println("保存用户异常日志"+e.getMessage());
	}


}
