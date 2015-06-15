package com.member.dao;

public class HqlIntegralManager {

	public final static String getIntegralHistory=
		"from AccountDetails";
	public final static String getIntegralHistoryByUserName=
		"from AccountDetails where userNumber=?";
	public final static String getRangeIssueBySerialNumber=
		"select new map(declarationBenefitId as declarationBenefitId,declarationBenefitNumber as declarationBenefitNumber,count(id) as countNumber) from RepeatedMoneyStatistics where state=0 and serialNumber<? group by declarationBenefitId,declarationBenefitNumber";
}
