package com.member.services.back;

import java.util.List;

import com.member.entity.Withdrawals;
import com.member.helper.BaseResult;

public interface WithdrawalsService {
	public List<Withdrawals> getWithdrawalsRecordByMemberNumber(String memeberNumber,Integer currentUserId,String currentUserNm);
	public List<Withdrawals> getNotDealWithdrawalsRecord(String memeberNumber);
	public Withdrawals getWithdrawalsDetailById(Integer id);
	public BaseResult<Void> agreewithdrawals(Integer id);
}
