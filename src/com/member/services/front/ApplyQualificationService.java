package com.member.services.front;

import java.util.List;

import com.member.entity.ApplyService;

public interface ApplyQualificationService {

	List<ApplyService> getApplyQualification(String submitNumber);

	void deleteApplyQualification(Integer id);

}
