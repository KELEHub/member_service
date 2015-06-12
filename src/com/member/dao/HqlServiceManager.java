package com.member.dao;

public class HqlServiceManager {

	public final static String getService=
		"from Information where isService=?";
	public final static String getServiceById=
		"from Information where id=?";
	public final static String getApproveService=
		"from ApplyService";
}
