package com.member.dao;

public class HqlTicklingManager {

	public final static String getTickling=
		"from Tickling where state=?";
	public final static String getTicklingByMemberNumber=
		"from Tickling where memberNumber=?";
}
