package com.member.services.back;

import java.util.List;

import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.back.IntegralHistoryForm;
import com.member.form.back.RangeIssueForm;

public interface IntegralManagerService {

	List<AccountDetails> getIntegralHistory();

	List<AccountDetails> getIntegralHistoryByUserName(String userName);

	List<RangeIssueForm> getAvailableRangeIntegral(int serialNumber);
	
	List<IntegralHistoryForm> getIntegralHistoryPoints(String number,int pageSize,int pageNumber);
	
	public int countIntegralHistoryPoints(String number);
	
	List<AccountDetails> getIntegralHistoryPointsByNumber(String number);

	Information getInformationByNumber(String number);
	
	public List<IntegralHistoryForm> getFromgiftsHistoryPoints(int pageSize,int pageNumber,String number);
	
	public int countFromgiftsHistoryPoints(String number);
	
	public int countXfMoneyHistory(String number);
	
	public List<IntegralHistoryForm> getXfMoneyHistory(String number,int pageSize,int pageNumber);

	void saveOrUpdateRelation(Information info, AccountDetails ad,
			String BenefitNumber, Integer serialNumber);

	List<AccountDetails> getMemberBycountNumberAndUserNumber(
			Integer countNumber, String userNumber);

}
