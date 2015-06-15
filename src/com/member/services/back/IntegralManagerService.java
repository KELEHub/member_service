package com.member.services.back;

import java.util.List;

import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.back.RangeIssueForm;

public interface IntegralManagerService {

	List<AccountDetails> getIntegralHistory();

	List<AccountDetails> getIntegralHistoryByUserName(String userName);

	List<RangeIssueForm> getAvailableRangeIntegral(int serialNumber);
	
	List<AccountDetails> getIntegralHistoryPoints();
	
	List<AccountDetails> getIntegralHistoryPointsByNumber(String number);

	Information getInformationById(Integer id);

	void saveOrUpdateRelation(Information info, AccountDetails ad,
			Integer BenefitId, Integer serialNumber);

}
