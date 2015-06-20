package com.member.services.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.ApplyQualificationDao;
import com.member.dao.HqlServiceManager;
import com.member.entity.Information;
import com.member.services.front.ApplyQualificationService;

@Service("ApplyQualificationServiceImpl")
public class ApplyQualificationServiceImpl implements ApplyQualificationService {

	@Resource(name = "ApplyQualificationDaoImpl")
    private ApplyQualificationDao applyQualificationDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getApplyQualification(String leaderServiceNumber) {
		return (List<Information>) applyQualificationDao.queryByHql(
				HqlServiceManager.getApplyQualification,leaderServiceNumber);
	}
}
