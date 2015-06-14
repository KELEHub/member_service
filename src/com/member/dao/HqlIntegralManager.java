package com.member.dao;

public class HqlIntegralManager {

	public final static String getIntegralHistory=
		"from AccountDetails";
	public final static String getIntegralHistoryByUserName=
		"from AccountDetails where userNumber=?";
}
