package com.member.util;

import java.io.UnsupportedEncodingException;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;

public class FramePushletUtil {

	public static void pushEvent(String eventName,String jsonStringData)
	{
			Event event = Event.createDataEvent(eventName);
			try {
				jsonStringData = new String(jsonStringData.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			event.setField("data", jsonStringData);
			Dispatcher dis = Dispatcher.getInstance();
			if(event==null||dis==null)
			{
			}
			else{
				dis.multicast(event); // 向所有和eventS名称匹配的事件推�?
			}

		}
}

