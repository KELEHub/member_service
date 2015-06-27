package com.member.services.front;

import java.util.List;

import com.member.entity.Charge;
import com.member.entity.Withdrawals;
import com.member.form.front.MemberChargeApplyForm;
import com.member.form.front.MemberWithdrawalsApplyForm;
import com.member.helper.BaseResult;

public interface AccountService {

	public List<Charge> getMemberChargeInfoByUserName(String userName);
	
	public BaseResult<Void> doAccCharge(MemberChargeApplyForm form);
	
	public Charge getChargeDetailById(Integer id);
	
	public List<Withdrawals> getMemberWithdrawalsInfoByUserName(String userName);
	
	public BaseResult<Void> doAccWithdrawals(MemberWithdrawalsApplyForm form);
	
	public Withdrawals getWithdrawalsDetailById(Integer id);

}
