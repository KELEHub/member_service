package com.member.dao;

public class HqlNoticeManager {

	public final static String getNotice=
		"from Notice";
	public final static String getLatestNewsList=
		"from Notice where category='公告'";
	public final static String getFAQ=
		"from Notice where category='常见问题'";
	public final static String getNoticeInfo=
		"from Notice where id=?";
	public final static String getProtocolList=
		"from Notice where category='服务协议'";
}
