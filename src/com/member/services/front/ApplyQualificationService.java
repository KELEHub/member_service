package com.member.services.front;

import java.util.List;

import com.member.entity.ApplyService;
import com.member.entity.Information;

public interface ApplyQualificationService {

	List<ApplyService> getApplyQualification(String submitNumber);

	void deleteApplyQualification(Integer id);

	List<Information> queryMemberInfoByNumber(String number);

	void submitApply(Object obj);

}
