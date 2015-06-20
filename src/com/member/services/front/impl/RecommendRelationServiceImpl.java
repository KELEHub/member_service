package com.member.services.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlServiceManager;
import com.member.dao.RecommendRelationDao;
import com.member.entity.Information;
import com.member.services.front.RecommendRelationService;

@Service("RecommendRelationServiceImpl")
public class RecommendRelationServiceImpl implements RecommendRelationService {

	@Resource(name = "RecommendRelationDaoImpl")
    RecommendRelationDao recommendRelationDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Information> getRecommendRelation(String recommendNumber) {
		return (List<Information>) recommendRelationDao.queryByHql(
				HqlServiceManager.getRecommendRelation,recommendNumber);
	}
}
