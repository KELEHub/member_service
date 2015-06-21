package com.member.dao;

public class HqlServiceManager {

	public final static String getService=
		"from Information where isService=?";
	public final static String getServiceById=
		"from Information where id=?";
	public final static String getApproveService=
		"from ApplyService";
	public final static String getUserByName=
		"from NmsUser t where t.userName=?  and t.status>=0";
	public final static String getRecommendRelation=
		"from Information where recommendNumber=?";
	public final static String getApplyQualification=
		"from ApplyService where submitNumber=?";
	public final static String getServiceByNumber=
		"from Information where number=?";
}
