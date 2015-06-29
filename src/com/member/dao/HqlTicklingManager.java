package com.member.dao;

public class HqlTicklingManager {

	public final static String getTickling=
		"from Tickling where state=?";
	public final static String getTicklingByMemberNumber=
		"from Tickling where memberNumber=?";
	public final static String getInformationIdByMemberNumber=
		"select id from Information where number=?";
	public final static String getTicklingById=
		"from Tickling where id=?";
}
