package com.member.services.front;

import java.util.List;

import com.member.entity.Information;

public interface ApplyQualificationService {

	List<Information> getApplyQualification(String leaderServiceNumber);

}
