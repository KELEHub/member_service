package com.member.dao;

public class HqlServiceManager {

	public final static String getService=
		"from Information where isService=? order by activeDate desc";
	public final static String getServiceById=
		"from Information where id=?";
	public final static String getApproveService=
		"from ApplyService order by applyDate desc";
	public final static String getUserByName=
		"from NmsUser t where t.userName=?  and t.status>=0";
	public final static String getRecommendRelation=
		"from Information where recommendNumber=? and isActivate=1  order by registerDate desc";
	public final static String getApplyQualification=
		"from ApplyService where submitNumber=?  order by applyDate desc";
	public final static String getServiceByNumber=
		"from Information where number=?";
	public final static String getForbidForm=
		"from ForbidForm";
}
