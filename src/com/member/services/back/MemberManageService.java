package com.member.services.back;

import java.util.List;

import com.member.entity.Information;
import com.member.form.back.MemberSaveForm;
import com.member.form.back.MemberSearchForm;

public interface MemberManageService {
	public List<Information> getActiveMembers(MemberSearchForm form);
	public List<Information> getNotActiveMembers(MemberSearchForm form);
	public void updateMemberLockFlag(Integer id);
	public void updateMemberPassword(Integer id);
	public Information getMemberById(Integer id);
	public void updateMemberDetails(MemberSaveForm form);
}
