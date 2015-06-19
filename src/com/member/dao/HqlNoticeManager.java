package com.member.dao;

public class HqlNoticeManager {

	public final static String getNotice=
		"from Notice";
	public final static String getLatestNewsList=
		"from Notice where category='公告'";
}
