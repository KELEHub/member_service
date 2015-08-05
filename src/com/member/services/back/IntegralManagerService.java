package com.member.services.back;

import java.util.List;

import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.back.RangeIssueForm;

public interface IntegralManagerService {

	List<AccountDetails> getIntegralHistory();

	List<AccountDetails> getIntegralHistoryByUserName(String userName);

	List<RangeIssueForm> getAvailableRangeIntegral(int serialNumber);
	
	List<AccountDetails> getIntegralHistoryPoints(String number,int pageSize,int pageNumber);
	
	public int countIntegralHistoryPoints(String number);
	
	List<AccountDetails> getIntegralHistoryPointsByNumber(String number);

	Information getInformationByNumber(String number);
	
	public List<AccountDetails> getFromgiftsHistoryPoints(int pageSize,int pageNumber);
	
	public int countFromgiftsHistoryPoints();

	void saveOrUpdateRelation(Information info, AccountDetails ad,
			String BenefitNumber, Integer serialNumber);

	List<AccountDetails> getMemberBycountNumberAndUserNumber(
			Integer countNumber, String userNumber);

}
