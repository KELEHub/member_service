package com.member.services.back;

import java.math.BigDecimal;
import java.util.List;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.entity.AccountDetails;
import com.member.entity.Information;
import com.member.form.back.MemberSaveForm;
import com.member.form.back.MemberSearchForm;

public interface MemberManageService {
	
	public List<Information> getActiveMembers(MemberSearchForm form,String number,String recommendNumber, String serviceNumber,
			int iDisplayLength,int pageNumber);
	
	public List<Information> getNotActiveMembers(MemberSearchForm form,String customerPar,
			int pageSize,int pageNumber);
	
	public void updateMemberLockFlag(Integer id);
	
	public void updateMemberPassword(Integer id);
	
	public Information getMemberById(Integer id);
	
	public void updateMemberDetails(MemberSaveForm form);
	
	public void deleteMember(MemberSaveForm form);
	
	List<AccountDetails> getAccountDetailsByProjectAndUserId(
			ProjectEnum project,Integer childId);

	void deleteActiveMember(Integer id, String number, Integer isService, Integer recommendId,
			Integer leaderServiceId,AccountDetails serviceAD,AccountDetails memberAD,BigDecimal sum,
			BigDecimal shoppingMoneySurplus,BigDecimal repeatedMoneySurplus);

	List<Information> getMemberInfoByNumber(String number);

	int countActiveMembersData(MemberSearchForm form,String number,String recommendNumber, String serviceNumber,Integer isActivate);

	int countNotActiveMembersData(MemberSearchForm form);
}
