package com.member.services.back;

import java.util.List;

import com.member.entity.AccountDetails;
import com.member.entity.RepeatedMoneyStatistics;

public interface IntegralManagerService {

	List<AccountDetails> getIntegralHistory();

	List<AccountDetails> getIntegralHistoryByUserName(String userName);

	List<RepeatedMoneyStatistics> getAvailableRangeIntegral();

}
