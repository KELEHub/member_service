package com.member.services.back;

import java.io.InputStream;
import java.util.List;

import com.member.entity.Withdrawals;
import com.member.helper.BaseResult;

public interface WithdrawalsService {
	public List<Withdrawals> getWithdrawalsRecordByMemberNumber(String memeberNumber,Integer currentUserId,String currentUserNm,String customerPar,
			int pageSize,int pageNumber);
	public List<Withdrawals> getNotDealWithdrawalsRecord(String memeberNumber,String customerPar,
			int pageSize,int pageNumber);
	public Withdrawals getWithdrawalsDetailById(Integer id);
	public BaseResult<Void> agreewithdrawals(Integer id,String dealUserName);
	public BaseResult<Void> disAgreewithdrawals(Integer id,String dealUserName,String refuseReason);
	public InputStream getExportRecords(String memeberNumber) throws Exception;
	int countWithdrawalsRecordByMemberNumberData(String memeberNumber,
			Integer currentUserId, String currentUserNm);
	int countNotDealWithdrawalsRecordData(String memeberNumber);
}
